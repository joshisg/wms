package poc.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poc.model.Farmer;
import poc.model.Grain;
import poc.model.StorageBill;
import poc.repository.FarmerRepository;
import poc.repository.StorageBillRepository;

@Service
public class StorageBillServiceImpl implements StorageBillService {

	final int perBagRate = 6;

	@Autowired
	StorageBillRepository storageBillRepository;

	@Autowired
	FarmerRepository farmerRepository;

	@Override
	public ResponseEntity<StorageBill> createStorageBill(String farmerId, int grainBagQuantity,
			StorageBill storageBill) {
		Optional<Farmer> farmerData = farmerRepository.findById(farmerId);
		if (farmerData.isPresent()) {
			Farmer farmer1 = farmerData.get();

			List<Grain> grainsList = farmer1.getGrains();
			for (Grain grain : grainsList) {

				if (grain.getGrainId().equals(storageBill.getGrain().getGrainId())) {

					LocalDate inwardDate = grain.getGrainInwardDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate();
					LocalDate outwardDate = storageBill.getGrainOutwardDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate();
						long bill=storgeBillCalculator(grainBagQuantity, inwardDate, outwardDate);
						storageBill.setStorageBillAmount(bill);
				}
			}
		}
		return new ResponseEntity<>(storageBillRepository.save(storageBill), HttpStatus.OK);
	}
	/* utility for bill calculator
	 * businss logic to calculate the bill for the storage for the specific grain
	 * first calulated the one day bill per bag 6 rs and then duration will be find
	 * out based on the grain Inward date and grain outward date and then final bill
	 * will be calulated as per duation multiplied by one day bill
	 */
	
	public long storgeBillCalculator(int grainBagQuantity, LocalDate inwardDate, LocalDate outwardDate) {
		int oneDayBill = perBagRate * grainBagQuantity;
		long duration = ChronoUnit.DAYS.between(inwardDate, outwardDate);
		System.out.println("Duration calculated is : " + duration);
		if (duration > 0 && duration <= 30)
			return oneDayBill * 30;
		else
			return oneDayBill * duration;

	}

}
