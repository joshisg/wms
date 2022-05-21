package poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poc.model.StorageBill;
import poc.service.StorageBillService;

@RestController
@RequestMapping("/wms")
@CrossOrigin(origins = "http://localhost:3000")
public class StorageBillController {
	@Autowired
	StorageBillService storageBillService;

	@PostMapping("/storagecalculator/{farmerId}/{grainBagQuantity}")
	public ResponseEntity<StorageBill> createStorageBilling(@PathVariable("farmerId") String farmerId,
			@PathVariable("grainBagQuantity") int grainBagQuantity, @RequestBody StorageBill storageBill) {
		return storageBillService.createStorageBill(farmerId, grainBagQuantity, storageBill);

	}

}
