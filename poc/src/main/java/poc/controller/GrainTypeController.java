package poc.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import poc.model.GrainType;
import poc.service.GrainTypeService;

@RestController
@RequestMapping("/wms/grain-types")
@Tag(name = "GrainType", description = "Grain type management APIs")
public class GrainTypeController {

    @Autowired
    private GrainTypeService grainTypeService;

    @PostMapping
    @Operation(summary = "Add a new grain type", description = "Add a new grain type to the system.")
    public ResponseEntity<GrainType> addGrainType(@RequestBody GrainType grainType) {
        return ResponseEntity.ok(grainTypeService.addGrainType(grainType));
    }

    @GetMapping
    @Operation(summary = "List all grain types", description = "Retrieve a list of all grain types.")
    public ResponseEntity<List<GrainType>> listGrainTypes() {
        return ResponseEntity.ok(grainTypeService.listGrainTypes());
    }
}
