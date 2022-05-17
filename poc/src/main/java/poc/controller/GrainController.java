package poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poc.model.Grain;
import poc.repository.GrainRepository;

@RestController
@RequestMapping("/wms")
public class GrainController {

	@Autowired
	GrainRepository grainRepository;
	
	@PostMapping("/grain")
	public ResponseEntity<Grain> createFarmer(@RequestBody Grain grain) {
		grainRepository.save(grain);
		return new ResponseEntity<>(grain, HttpStatus.CREATED);
	}
	
	
	
}
