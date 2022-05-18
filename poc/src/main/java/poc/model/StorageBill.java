package poc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "storagebill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class StorageBill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String storageBillId;
	
	@Column(name = "grainoutwarddate")
	private Date grainOutwardDate;
	
	@Column(name = "billamount")
	private long storageBillAmount;
	
		
	@ManyToOne
	@JoinColumn(name="farmer_Id")
	@JsonBackReference
	private Farmer farmer;

}
