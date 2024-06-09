package poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poc.model.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    boolean existsByNameAndContact(String name, String contact);
}
