
public class ElectricBike extends Bike implements Charger{
	private boolean battery;
	public ElectricBike(String serialNumber, boolean isRented) {
		super(serialNumber, isRented);
	}
	@Override
	public boolean isBatteryFull() {
		return battery;
	}
	/**
	* @Author: Carl
	* @Date: 2021/2/8 8:38
	* @Description: charge
	*/
	@Override
	public void recharge() {
		this.battery = true;
	}
	@Override
	public String toString() {
		return "ElectricBike [serialNumber=" + serialNumber + ", isRented=" + isRented + ",battery=" + battery +"]";
	}
}
