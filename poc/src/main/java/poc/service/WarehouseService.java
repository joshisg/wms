package poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poc.model.Warehouse;
import poc.repository.WarehouseRepository;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public boolean hasCapacity(int quantity) {
        Warehouse warehouse = warehouseRepository.findAll().get(0);
        return warehouse.getCurrentCapacity() + quantity <= warehouse.getTotalCapacity();
    }

    public void updateCapacity(int quantity, boolean isStoring) {
        Warehouse warehouse = warehouseRepository.findAll().get(0);
        if (isStoring) {
            warehouse.setCurrentCapacity(warehouse.getCurrentCapacity() + quantity);
        } else {
            warehouse.setCurrentCapacity(warehouse.getCurrentCapacity() - quantity);
        }
        warehouseRepository.save(warehouse);
    }
}
