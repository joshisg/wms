package poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poc.model.GrainType;

public interface GrainTypeRepository extends JpaRepository<GrainType, Long> {
}
