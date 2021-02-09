import java.util.List;
//租赁公司所要实现的方法
public interface Rental {
	public int availableBikes(BikeType type);
	public List<Bike> getRentedBikes();
	public Bike getBike(CustomerRecord customerRecord);
	public void issueBike(CustomerRecord customerRecord, BikeType type);
	public void terminateRental(CustomerRecord customerRecord);
}
