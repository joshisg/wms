package poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poc.model.Farmer;
import poc.repository.FarmerRepository;

import java.util.List;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    public Farmer registerFarmer(Farmer farmer) {
        if (farmerRepository.existsByNameAndContact(farmer.getName(), farmer.getContact())) {
            throw new RuntimeException("Farmer with this name and contact number already exists.");
        }
        return farmerRepository.save(farmer);
    }

    public Farmer getFarmer(Long id) {
        return farmerRepository.findById(id).orElseThrow(() -> new RuntimeException("Farmer not found"));
    }

    public List<Farmer> listFarmers() {
        return farmerRepository.findAll();
    }
}
