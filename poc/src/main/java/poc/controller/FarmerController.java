package poc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poc.model.Farmer;
import poc.repository.FarmerRepository;

@RestController
@RequestMapping("/wms")
public class FarmerController {

	@Autowired
	FarmerRepository farmerRepository;

	@PostMapping("/farmer")
	public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
		farmerRepository.save(farmer);
		return new ResponseEntity<>(farmer, HttpStatus.CREATED);
	}

	@GetMapping("/farmers")
	public ResponseEntity<List<Farmer>> getAllFarmers() {
		List<Farmer> farmer = farmerRepository.findAll();
		return new ResponseEntity<>(farmer, HttpStatus.OK);
	}

	@GetMapping("/farmer/{farmerId}")
	public ResponseEntity<Farmer> getFarmerById(@PathVariable("farmerId") String farmerId) {
		Farmer farmer = farmerRepository.findById(farmerId).get();
		return new ResponseEntity<>(farmer, HttpStatus.OK);
	}

	@GetMapping("/farmer/aadharNo/{aadharNo}")
	public ResponseEntity<Farmer> getFarmerByAadharNo(@PathVariable("aadharNo") String aadharNo) {
		Farmer farmer = farmerRepository.findByAadharNo(aadharNo);
		return new ResponseEntity<>(farmer, HttpStatus.OK);
	}

	@GetMapping("/farmer/farmername/{farmername}")
	public ResponseEntity<List<Farmer>> getFarmerByFarmerName(@PathVariable("farmername") String farmername) {
		List<Farmer> farmer = farmerRepository.findByFarmerName(farmername);
		return new ResponseEntity<>(farmer, HttpStatus.OK);
	}

	@PutMapping("/farmer/{farmerId}")
	public ResponseEntity<Farmer> updateTutorial(@PathVariable("farmerId") String farmerId,
			@RequestBody Farmer farmer) {
		Optional<Farmer> farmerData = farmerRepository.findById(farmerId);
		if (farmerData.isPresent()) {
			Farmer farmer1 = farmerData.get();
			farmer1.setFarmerName(farmer.getFarmerName());
			farmer1.setFarmerMobileNo(farmer.getFarmerMobileNo());
			farmer1.setAadharNo(farmer.getAadharNo());
			farmer1.setCity(farmer.getCity());
			farmer1.setState(farmer.getState());
			farmer1.setZipcode(farmer.getZipcode());
			return new ResponseEntity<>(farmerRepository.save(farmer1), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/farmer/{farmerId}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("farmerId") String farmerId) {
		try {
			farmerRepository.deleteById(farmerId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
