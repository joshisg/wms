package poc.service;

import org.springframework.http.ResponseEntity;

import poc.model.StorageBill;

public interface StorageBillService {

	ResponseEntity<StorageBill> createStorageBill(String farmerId, int grainBagQuantity, StorageBill storageBill);
}
