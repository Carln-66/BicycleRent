
public class RoadBike extends Bike {
	public RoadBike(String serialNumber, boolean isRented) {
		super(serialNumber, isRented);
	}

	@Override
	public String toString() {
		return "RoadBike [serialNumber=" + serialNumber + ", isRented=" + isRented + "]";
	}
}
