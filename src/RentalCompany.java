import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalCompany implements Rental{
	private static final int roadBikeNum = 50;
	private static final int electricBikeNum = 10;     // constant: amount of bikes
	private static ArrayList<CustomerRecord> recordCustomers = new ArrayList<CustomerRecord>(); //collections which is store customers' information
	private ArrayList<Bike> roadBikes = new ArrayList<Bike>();		//store road bikes which is already rented
	private ArrayList<Bike> electricBikes = new ArrayList<Bike>();  //store electric bikes which is already rented
	private Map<CustomerRecord,Bike> rentMap = new HashMap<CustomerRecord,Bike>();   //bounds customer and their rent bikes
	
	/**
	* @Author: Carl
	* @Date: 2021/2/8 14:10
	* @Description: amount of rentable bikes
	*/
	@Override
	public int availableBikes(BikeType type) {
		if(type.equals(BikeType.ROAD)) {
			return roadBikeNum - roadBikes.size();
		}else {
			return electricBikeNum - electricBikes.size();
		}
	}

	/**
	* @Author: Carl
	* @Date: 2021/2/9 9:10
	* @Description: return all bikes which is rented
	*/
	@Override
	public List<Bike> getRentedBikes() {
		List<Bike> allRentedBike = new ArrayList<>();
		allRentedBike.addAll(roadBikes);
		allRentedBike.addAll(electricBikes);
		return allRentedBike;
	}

	/**
	* @Author: Carl
	* @Date: 2021/2/9 9:16
	* @Description: insert customer and return their bikes
	*/
	@Override
	public Bike getBike(CustomerRecord customerRecord) {
		return rentMap.get(customerRecord);
	}


	/**
	* @Author: Carl
	* @Date: 2021/2/9 9:19
	* @Description: rent electric bikes
	*/
	@Override
	public void issueBike(CustomerRecord customerRecord, BikeType type) {
		//determine whether the customer currently is renting a bike
		if(rentMap.containsKey(customerRecord)) {
			System.out.println("You cannot rent more than one bike.");
			return;
		}
		if(type.equals(BikeType.ELECTRIC)) {
			//Determine if there are any bikes left
			if(electricBikes.size() >= electricBikeNum) {
				System.out.println("Sorry, there's no rentable bikes now.");
			}
			Calendar now = Calendar.getInstance();
			now.add(Calendar.YEAR, -21);
			//under 21 or not golden class
			if(now.before(customerRecord.getBirthday()) || !customerRecord.isGoldClass()) {
				System.out.println("You are not eligible to rent a bike");
				return;
			}
			//successfully rented  put the bikes into the collection, the serial number is the order of rent
			Bike electricBike = BikeFactory.getBike(type, electricBikes.size());
			electricBikes.add(electricBike);
			rentMap.put(customerRecord, electricBike);
		}

		/**
		* @Author: Carl
		* @Date: 2021/2/9 10:12
		* @Description: rent road bikes
		*/
		else {
			//determine if a bike is currently available
			if(roadBikes.size() >= roadBikeNum) {
				System.out.println("Sorry, there's no rentable bikes now.");
				return;
			}
			Bike roadBike = BikeFactory.getBike(type, roadBikes.size());
			roadBikes.add(roadBike);
			rentMap.put(customerRecord, roadBike);
		}
	}

	/**
	* @Author: Carl
	* @Date: 2021/2/9 10:35
	* @Description: terminate rental
	*/
	@Override
	public void terminateRental(CustomerRecord customerRecord) {
		//get the bike
		Bike bike = getBike(customerRecord);
		//remove the bike from collection
		if(bike instanceof RoadBike) {
			roadBikes.remove(bike);
			//set status of the bike is available
			bike.setRented(false);
		}
		else {
			electricBikes.remove(bike);
			//set status of the bike is available
			bike.setRented(false);
			((ElectricBike)bike).recharge();
		}
		rentMap.remove(customerRecord);
	}
	
	
	public static void main(String[] args) {
		/**
		* @Author: Carl
		* @Date: 2021/2/9 11:22
		* @Description: Creat some new customers
		*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			CustomerRecord c1 = new CustomerRecord(new Customer("Ann Bnn",sdf.parse("1997-2-4")), false);
			CustomerRecord c2 = new CustomerRecord(new Customer("Anc Bnc",sdf.parse("1999-1-13")), true);
			CustomerRecord c3 = new CustomerRecord(new Customer("Bnn Cnn",sdf.parse("2000-5-4")), false);
			CustomerRecord c4 = new CustomerRecord(new Customer("Cnn Dnn",sdf.parse("2008-10-25")), false);
			CustomerRecord c5 = new CustomerRecord(new Customer("Dnn Enn",sdf.parse("1985-11-11")), true);
			recordCustomers.add(c1);
			recordCustomers.add(c2);
			recordCustomers.add(c3);
			recordCustomers.add(c4);
			recordCustomers.add(c5);
			
			//rent bike
			RentalCompany company = new RentalCompany();
			company.issueBike(c1, BikeType.ROAD);
			company.issueBike(c2, BikeType.ELECTRIC);
			//c3 is fail the rent a bike because of not a gold class member
			company.issueBike(c3, BikeType.ELECTRIC);
			//c4 is fail to rent a bike because of age under 21
			company.issueBike(c4, BikeType.ELECTRIC);
			company.issueBike(c5, BikeType.ROAD);
			//c5 is fail to rent a bike because of already own a bike
			company.issueBike(c5, BikeType.ELECTRIC);
			
			//View the number of bikes currently available
			System.out.println("Rentable road bike number: "+company.availableBikes(BikeType.ROAD));
			System.out.println("Rentable Electric bike number: "+company.availableBikes(BikeType.ELECTRIC));

			//view the list of renting bikes
			List<Bike> rentedBikes = company.getRentedBikes();
			for(Bike bike : rentedBikes) {
				System.out.println(bike);
			}
			
			//return the bike which is c5 rent
			Bike c5Bike = company.getBike(c5);
			System.out.println("Customer c5 is renting: "+c5Bike);
			//terminate the contract of c5
			company.terminateRental(c5);
			System.out.println("the bike of customer c5 renting is: "+company.getBike(c5));
			//review the current rentable bikes
			System.out.println("Amount of rentable road bike is: "+company.availableBikes(BikeType.ROAD));
			System.out.println("Amount of rentable electric bike is: "+company.availableBikes(BikeType.ELECTRIC));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
