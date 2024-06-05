package poc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "farmer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Farmer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String farmerId;
	
	@Column(name = "farmername")
	private String farmerName;
	
	@Column(name = "mobileno", unique = true)
	@NotBlank(message = "Name is mandatory")
	private String farmerMobileNo;
	
	@Column(name = "aadharnumber", unique = true)
	private String aadharNo;
	
	@Column
	private String city;
	
	public String getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getFarmerMobileNo() {
		return farmerMobileNo;
	}

	public void setFarmerMobileNo(String farmerMobileNo) {
		this.farmerMobileNo = farmerMobileNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<Grain> getGrains() {
		return grains;
	}

	public void setGrains(List<Grain> grains) {
		this.grains = grains;
	}

	@Column
	private String state;
	
	@Column
	private String zipcode;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer", fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<Grain> grains;
	
	
	
	
}
