package poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poc.model.Farmer;
import poc.model.GrainStorage;

public interface GrainStorageRepository extends JpaRepository<GrainStorage, Long> {
    List<GrainStorage> findAllByFarmer(Farmer farmer);
}