package poc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="farmerId")
	private List<Grain> grains = new ArrayList<>();
}
