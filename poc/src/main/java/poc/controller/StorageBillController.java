package poc.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poc.model.Farmer;
import poc.model.Grain;
import poc.model.StorageBill;
import poc.repository.FarmerRepository;
import poc.repository.StorageBillRepository;

@RestController
@RequestMapping("/wms")
@CrossOrigin(origins = "http://localhost:3000")	
public class StorageBillController {
	final int perBagRate=6;
	@Autowired
	StorageBillRepository storageBillRepository;
	
	@Autowired
	FarmerRepository farmerRepository;
	
	
	@PostMapping("/storagecalculator/{farmerId}/{grainBagQuantity}")
	public ResponseEntity<StorageBill> createStorageBilling(@PathVariable("farmerId") String farmerId, 
			@PathVariable("grainBagQuantity") int grainBagQuantity, @RequestBody StorageBill storageBill) {
		
			
		Optional<Farmer> farmerData = farmerRepository.findById(farmerId);
		if (farmerData.isPresent()) {
			Farmer farmer1 = farmerData.get();
			
				List<Grain> grainsList=farmer1.getGrains();
				for(Grain grain: grainsList) {
					
					if(grain.getGrainId().equals(storageBill.getGrain().getGrainId())) {
						int oneDayBill= perBagRate*grainBagQuantity;
						
						LocalDate inwardDate =grain.getGrainInwardDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						LocalDate outwardDate=storageBill.getGrainOutwardDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						long duration=ChronoUnit.DAYS.between(inwardDate, outwardDate);
						System.out.println("duration time is" +duration);
						long storagebillAmount = oneDayBill*duration;
						if(duration>=1) {
							storageBill.setStorageBillAmount(storagebillAmount);
							}
						storageBill.setStorageBillAmount(oneDayBill);
					}
				}
		}
			
			return new ResponseEntity<>(storageBillRepository.save(storageBill), HttpStatus.OK);
		
	}
	
}
