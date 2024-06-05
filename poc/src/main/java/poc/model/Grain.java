package poc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "grain")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Grain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String grainId;
	
	@Column(name = "grainname")
	private String grainName;
	
	@Column(name = "grainmoisture")
	private String grainMoisture;
	
	@Column(name = "grainbagquantity")
	private String grainBagQuantity;
	
	public String getGrainId() {
		return grainId;
	}

	public void setGrainId(String grainId) {
		this.grainId = grainId;
	}

	public String getGrainName() {
		return grainName;
	}

	public void setGrainName(String grainName) {
		this.grainName = grainName;
	}

	public String getGrainMoisture() {
		return grainMoisture;
	}

	public void setGrainMoisture(String grainMoisture) {
		this.grainMoisture = grainMoisture;
	}

	public String getGrainBagQuantity() {
		return grainBagQuantity;
	}

	public void setGrainBagQuantity(String grainBagQuantity) {
		this.grainBagQuantity = grainBagQuantity;
	}

	public Date getGrainInwardDate() {
		return grainInwardDate;
	}

	public void setGrainInwardDate(Date grainInwardDate) {
		this.grainInwardDate = grainInwardDate;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public List<StorageBill> getStorageBill() {
		return storageBill;
	}

	public void setStorageBill(List<StorageBill> storageBill) {
		this.storageBill = storageBill;
	}

	@Column(name = "graininwarddate")
	private Date grainInwardDate;
	
	@ManyToOne
	@JoinColumn(name="farmer_Id")
	@JsonBackReference
	private Farmer farmer;
	
// new enhancement for grain tracking
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "grain", fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<StorageBill> storageBill;
	
}
