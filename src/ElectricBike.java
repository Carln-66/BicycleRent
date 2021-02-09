
public class ElectricBike extends Bike implements Charger{
	private boolean battery;
	public ElectricBike(String serialNumber, boolean isRented) {
		super(serialNumber, isRented);
	}
	@Override
	public boolean isBatteryFull() {
		return battery;
	}
	//充电
	@Override
	public void recharge() {
		this.battery = true;
	}
	@Override
	public String toString() {
		return "ElectricBike [serialNumber=" + serialNumber + ", isRented=" + isRented + ",battery=" + battery +"]";
	}
}
