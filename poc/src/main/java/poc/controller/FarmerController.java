package poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/farmer/{farmerId}")
	public ResponseEntity<Farmer> getFarmerById(@PathVariable("farmerId") String farmerId) {
		Farmer farmer=farmerRepository.findById(farmerId).get();
		return new ResponseEntity<>(farmer, HttpStatus.FOUND);
	}
	
	@GetMapping("/farmer/aadharNo/{aadharNo}")
	public ResponseEntity<Farmer> getFarmerByAadharNo(@PathVariable("aadharNo") String aadharNo) {
		Farmer farmer=farmerRepository.findByAadharNo(aadharNo);
		return new ResponseEntity<>(farmer, HttpStatus.FOUND);
	}
	
	@GetMapping("/farmer/farmername/{farmername}")
	public ResponseEntity<List<Farmer>> getFarmerByFarmerName(@PathVariable("farmername") String farmername) {
		List<Farmer> farmer=farmerRepository.findByFarmerName(farmername);
		return new ResponseEntity<>(farmer, HttpStatus.FOUND);
	}
}
