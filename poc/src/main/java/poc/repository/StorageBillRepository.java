package poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poc.model.StorageBill;

public interface StorageBillRepository extends JpaRepository<StorageBill, String> {

}
