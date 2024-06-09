package poc.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import poc.model.Warehouse;
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
