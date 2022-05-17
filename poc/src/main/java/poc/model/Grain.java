package poc.model;

import java.util.Date;

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
	
	@Column(name = "graininwarddate")
	private Date grainInwardDate;
	
	@ManyToOne
	@JoinColumn(name="farmer_Id")
	@JsonBackReference
	private Farmer farmer;

	
}
