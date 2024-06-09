package poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poc.model.Farmer;
import poc.model.GrainStorage;
import poc.model.GrainType;
import poc.repository.FarmerRepository;
import poc.repository.GrainStorageRepository;
import poc.repository.GrainTypeRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class GrainStorageService {

    @Autowired
    private GrainStorageRepository storageRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private WarehouseService warehouseService;
    
    @Autowired
    private GrainTypeRepository grainTypeRepository;

    private static final double DAILY_RATE_PER_BAG = 5.0;

    public GrainStorage storeGrain(GrainStorage storage) {
        if (warehouseService.hasCapacity(storage.getQuantity())) {
            storage.setStorageDate(LocalDate.now());
            warehouseService.updateCapacity(storage.getQuantity(), true);
            return storageRepository.save(storage);
        } else {
            throw new RuntimeException("Not enough capacity in the warehouse");
        }
    }

    public GrainStorage checkout(Long storageId) {
        GrainStorage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new RuntimeException("Storage not found"));
        storage.setCheckoutDate(LocalDate.now());
        warehouseService.updateCapacity(storage.getQuantity(), false);
        storageRepository.save(storage);
        return storage;
    }

    public double calculateCharge(GrainStorage storage) {
        long daysStored = ChronoUnit.DAYS.between(storage.getStorageDate(), storage.getCheckoutDate());
        return storage.getQuantity() * daysStored * DAILY_RATE_PER_BAG;
    }

    public List<GrainStorage> findStorageByFarmerAndGrainType(Long farmerId, Long grainTypeId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));
        GrainType grainType = grainTypeRepository.findById(grainTypeId)
                .orElseThrow(() -> new RuntimeException("Grain type not found"));
        return storageRepository.findAllByFarmer(farmer);
    }

    public GrainStorage getStorageById(Long id) {
        return storageRepository.findById(id).orElseThrow(() -> new RuntimeException("Storage not found"));
    }

    public List<GrainStorage> listAllStorage() {
        return storageRepository.findAll();
    }

    public double calculateTotalValueByFarmer(Long farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));
        List<GrainStorage> storageList = storageRepository.findAllByFarmer(farmer);
        return storageList.stream()
                .mapToDouble(storage -> storage.getQuantity() * getGrainPrice(storage.getGrainType()))
                .sum();
    }

    private double getGrainPrice(GrainType grainType) {
        // Assume we have a method to get current grain prices
        // This could be a static value or fetched from another service
        // For simplicity, returning a fixed value here
        switch (grainType.getName().toLowerCase()) {
            case "wheat":
                return 20.0;  // Example price per bag
            case "rice":
                return 25.0;  // Example price per bag
            default:
                return 15.0;  // Default price per bag
        }
    }
}