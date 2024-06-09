package poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poc.model.GrainType;
import poc.repository.GrainTypeRepository;

import java.util.List;

@Service
public class GrainTypeService {

    @Autowired
    private GrainTypeRepository grainTypeRepository;

    public GrainType addGrainType(GrainType grainType) {
        return grainTypeRepository.save(grainType);
    }

    public List<GrainType> listGrainTypes() {
        return grainTypeRepository.findAll();
    }
}