package poc.model;
import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class GrainStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "grain_type_id")
    private GrainType grainType;

    private int quantity;
    private LocalDate storageDate;
    private LocalDate checkoutDate;
    
    public GrainStorage() {
		// TODO Auto-generated constructor stub
	}
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Farmer getFarmer() {
		return farmer;
	}
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	public GrainType getGrainType() {
		return grainType;
	}
	public void setGrainType(GrainType grainType) {
		this.grainType = grainType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDate getStorageDate() {
		return storageDate;
	}
	public void setStorageDate(LocalDate storageDate) {
		this.storageDate = storageDate;
	}
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

    // Getters and setters
    
}
