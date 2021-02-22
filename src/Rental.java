import java.util.List;
/**
* @Author: Carl
* @Date: 2021/2/8 8:44
* @Description: list all methods rental company should include
*/
public interface Rental {
	public int availableBikes(BikeType type);
	public List<Bike> getRentedBikes();
	public Bike getBike(CustomerRecord customerRecord);
	public void issueBike(CustomerRecord customerRecord, BikeType type);
	public void terminateRental(CustomerRecord customerRecord);
}
