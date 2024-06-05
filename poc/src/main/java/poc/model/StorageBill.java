package poc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	public String getStorageBillId() {
		return storageBillId;
	}

	public void setStorageBillId(String storageBillId) {
		this.storageBillId = storageBillId;
	}

	public Date getGrainOutwardDate() {
		return grainOutwardDate;
	}

	public void setGrainOutwardDate(Date grainOutwardDate) {
		this.grainOutwardDate = grainOutwardDate;
	}

	public long getStorageBillAmount() {
		return storageBillAmount;
	}

	public void setStorageBillAmount(long storageBillAmount) {
		this.storageBillAmount = storageBillAmount;
	}

	public Grain getGrain() {
		return grain;
	}

	public void setGrain(Grain grain) {
		this.grain = grain;
	}

	@Column(name = "grainoutwarddate")
	private Date grainOutwardDate;
	
	@Column(name = "billamount")
	private long storageBillAmount;
			
	// new enhacement for grain bill tracking
	@ManyToOne
	@JoinColumn(name="grain_Id")
	@JsonBackReference
	private Grain grain;

}
