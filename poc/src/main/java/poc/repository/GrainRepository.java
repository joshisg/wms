package poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poc.model.Grain;

public interface GrainRepository extends JpaRepository<Grain, String> {

}
