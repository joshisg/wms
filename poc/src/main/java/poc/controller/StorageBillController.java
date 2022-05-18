package poc.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poc.model.Farmer;
import poc.model.Grain;
import poc.model.StorageBill;
import poc.repository.FarmerRepository;
import poc.repository.StorageBillRepository;

@RestController
@RequestMapping("/wms")
public class StorageBillController {
	
	@Autowired
	StorageBillRepository storageBillRepository;
	
	@Autowired
	FarmerRepository farmerRepository;

	@PostMapping("/farmer/{farmerId}/{grainId}/{grainBagQuantity}")
	public ResponseEntity<StorageBill> createStorageBill(@PathVariable("farmerId") String farmerId, @PathVariable("grainId") String grainId, 
			@PathVariable("grainBagQuantity") int grainBagQuantity) {
		
		System.out.println("farmerId is : "+ farmerId);
		System.out.println("grainId is : "+ grainId);
		System.out.println("grainBagQuantity is : "+ grainBagQuantity);
		
		StorageBill storageBill = new StorageBill();
		Optional<Farmer> farmerData = farmerRepository.findById(farmerId);
		if (farmerData.isPresent()) {
			Farmer farmer1 = farmerData.get();
			
				List<Grain> grainsList=farmer1.getGrains();
				for(Grain grain: grainsList) {
					
					if(grain.getGrainId().equals(grainId)) {
						int perBagRate=6;
						int oneDayBill= perBagRate*grainBagQuantity;
						Date inDate=grain.getGrainInwardDate();
								LocalDate localInDate=inDate.toInstant()
							      .atZone(ZoneId.systemDefault())
							      .toLocalDate();
						LocalDate datee=LocalDate.now();
						
						long duration=ChronoUnit.DAYS.between(localInDate, datee);
						long storagebillAmount = oneDayBill*duration;
						storageBill.setFarmer(farmer1);
						storageBill.setStorageBillAmount(storagebillAmount);
						storageBill.setGrainOutwardDate(java.sql.Date.valueOf(datee));
					}
				}
		}
			
			return new ResponseEntity<>(storageBillRepository.save(storageBill), HttpStatus.OK);
		
	}
	
}
