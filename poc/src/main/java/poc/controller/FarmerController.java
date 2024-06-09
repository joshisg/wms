package poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import poc.model.Farmer;
import poc.service.FarmerService;

import java.util.List;

@RestController
@RequestMapping("/wms/farmers")
@Tag(name = "Farmer", description = "Farmer management APIs")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping
    @Operation(summary = "Register a new farmer", description = "Register a new farmer with the provided details.")
    public ResponseEntity<Farmer> registerFarmer(
    		@RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerService.registerFarmer(farmer));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get farmer by ID", description = "Retrieve a farmer's details by their ID.")
    public ResponseEntity<Farmer> getFarmer(@PathVariable Long id) {
        return ResponseEntity.ok(farmerService.getFarmer(id));
    }

    @GetMapping
    @Operation(summary = "List all farmers", description = "Retrieve a list of all registered farmers.")
    public ResponseEntity<List<Farmer>> listFarmers() {
        return ResponseEntity.ok(farmerService.listFarmers());
    }
}

