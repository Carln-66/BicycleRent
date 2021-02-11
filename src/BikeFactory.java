//class is used for create new bike
public class BikeFactory {
	//create new bike instance according to different type
	public static Bike getBike(BikeType type,int number) {
		if(type == BikeType.ROAD){
			String serialNumber = "rb"+String.format("%03d", number);
			return new RoadBike(serialNumber,true);
		}else {
			String serialNumber = "eb"+String.format("%03d", number);
			return new ElectricBike(serialNumber, true);
		}
	}
}
