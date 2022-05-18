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
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private String farmerMobileNo;
	@Column(name = "aadharnumber", unique = true)
	private String aadharNo;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String zipcode;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer", fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<Grain> grains;
	
	// new enhancement for billing system
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer", fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<StorageBill> bills;
	
	
	
}
