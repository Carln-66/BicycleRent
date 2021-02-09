//用于新建车对象的类
public class BikeFactory {
	//根据不同车类创建车对象
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
