package poc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import poc.model.GrainStorage;
import poc.service.GrainStorageService;

@RestController
@RequestMapping("/wms/storage")
@Tag(name = "GrainStorage", description = "Grain storage management APIs")
public class GrainStorageController {

	 private static final Logger logger = LoggerFactory.getLogger(GrainStorageController.class);
	
    @Autowired
    private GrainStorageService grainStorageService;

    @PostMapping("/store")
    @Operation(summary = "Store grain", description = "Store grain for a farmer in the warehouse.")
    public ResponseEntity<GrainStorage> storeGrain(@RequestBody GrainStorage storage) {
    	logger.info("Received request body: {}", storage);
        return ResponseEntity.ok(grainStorageService.storeGrain(storage));
    }

    @PutMapping("/checkout/{id}")
    @Operation(summary = "Checkout grain", description = "Checkout stored grain for a farmer and calculate charges.")
    public ResponseEntity<GrainStorage> checkoutGrain(@PathVariable Long id) {
        GrainStorage storage = grainStorageService.checkout(id);
        double charge = grainStorageService.calculateCharge(storage);
        return ResponseEntity.ok(storage);
    }

    @GetMapping("/farmer/{farmerId}/grain-type/{grainTypeId}")
    @Operation(summary = "Find storage by farmer and grain type", description = "Retrieve storage records by farmer ID and grain type ID.")
    public ResponseEntity<List<GrainStorage>> findStorageByFarmerAndGrainType(@PathVariable Long farmerId, @PathVariable Long grainTypeId) {
        return ResponseEntity.ok(grainStorageService.findStorageByFarmerAndGrainType(farmerId, grainTypeId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get storage by ID", description = "Retrieve a storage record by its ID.")
    public ResponseEntity<GrainStorage> getStorageById(@PathVariable Long id) {
        return ResponseEntity.ok(grainStorageService.getStorageById(id));
    }

    @GetMapping
    @Operation(summary = "List all storage", description = "Retrieve a list of all storage records.")
    public ResponseEntity<List<GrainStorage>> listAllStorage() {
        return ResponseEntity.ok(grainStorageService.listAllStorage());
    }
}
