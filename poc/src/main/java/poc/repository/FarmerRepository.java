package poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poc.model.Farmer;
@Repository
public interface FarmerRepository extends JpaRepository<Farmer, String> {

	public Farmer findByAadharNo(String aadharNo);
	public List<Farmer> findByFarmerName(String farmername);
}
