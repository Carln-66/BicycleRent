import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalCompany implements Rental{
	private static final int roadBikeNum = 50;
	private static final int electricBikeNum = 10;     //�常数，公司有的车的数量
	private static ArrayList<CustomerRecord> recordCustomers = new ArrayList<CustomerRecord>(); //用于存放注册用户的集合
	private ArrayList<Bike> roadBikes = new ArrayList<Bike>();		//存放租出去的普通自行车
	private ArrayList<Bike> electricBikes = new ArrayList<Bike>();  //�存放租出去的电瓶车
	private Map<CustomerRecord,Bike> rentMap = new HashMap<CustomerRecord,Bike>();   //用于将用户与租出去的车绑定
	
	//可用车的数量
	@Override
	public int availableBikes(BikeType type) {
		if(type.equals(BikeType.ROAD)) {
			return roadBikeNum - roadBikes.size();
		}else {
			return electricBikeNum - electricBikes.size();
		}
	}
	//得到所有租出去的车
	@Override
	public List<Bike> getRentedBikes() {
		List<Bike> allRentedBike = new ArrayList<>();
		allRentedBike.addAll(roadBikes);
		allRentedBike.addAll(electricBikes);
		return allRentedBike;
	}
	//根据用户得到他租到的车
	@Override
	public Bike getBike(CustomerRecord customerRecord) {
		return rentMap.get(customerRecord);
	}
	//用户租车
	@Override
	public void issueBike(CustomerRecord customerRecord, BikeType type) {
		//首先判断当前用户是否有租车
		if(rentMap.containsKey(customerRecord)) {
			System.out.println("一人只能租一个车！");
			return;
		}
		if(type.equals(BikeType.ELECTRIC)) {
			//判断当前车是否租满
			if(electricBikes.size() >= electricBikeNum) {
				System.out.println("租借失败");
			}
			Calendar now = Calendar.getInstance();
			now.add(Calendar.YEAR, -21);
			//如果小于21岁或者不是goldclass
			if(now.before(customerRecord.getBirthday()) || !customerRecord.isGoldClass()) {
				System.out.println("租借失败");
				return;
			}
			//租借成功  将车放到集合中,车的编号为第几辆租出去的车
			Bike electricBike = BikeFactory.getBike(type, electricBikes.size());
			electricBikes.add(electricBike);
			rentMap.put(customerRecord, electricBike);
			
		}
		//借的是普通车
		else {
			//判断当前车是否租满
			if(roadBikes.size() >= roadBikeNum) {
				System.out.println("租借失败");
				return;
			}
			Bike roadBike = BikeFactory.getBike(type, roadBikes.size());
			roadBikes.add(roadBike);
			rentMap.put(customerRecord, roadBike);
		}
	}
	//终止租车
	@Override
	public void terminateRental(CustomerRecord customerRecord) {
		//得到客户租的那辆车
		Bike bike = getBike(customerRecord);
		//从对应集合中移除Bike
		if(bike instanceof RoadBike) {
			roadBikes.remove(bike);
			//车子设置为没被租借
			bike.setRented(false);
		}
		//如果是电车
		else {
			electricBikes.remove(bike);
			//车子设置为没被租借
			bike.setRented(false);
			((ElectricBike)bike).recharge();
		}
		//解除用户与车的关系
		rentMap.remove(customerRecord);
	}
	
	
	public static void main(String[] args) {
		//先新建几个注册客户对象
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
			
			//租车
			RentalCompany company = new RentalCompany();
			company.issueBike(c1, BikeType.ROAD);
			company.issueBike(c2, BikeType.ELECTRIC);
			//客户三因为不是gold class所以会租借电车失败
			company.issueBike(c3, BikeType.ELECTRIC);
			//客户4未满21，会租借电车失败
			company.issueBike(c4, BikeType.ELECTRIC);
			company.issueBike(c5, BikeType.ROAD);
			//客户5已经租了一辆车，再租车会导致失败
			company.issueBike(c5, BikeType.ELECTRIC);
			
			//查看当前可用普通车数量
			System.out.println("普通自行车可租数量："+company.availableBikes(BikeType.ROAD));
			System.out.println("电瓶车可租数量："+company.availableBikes(BikeType.ELECTRIC));
			//得到当前租出去的自行车
			List<Bike> rentedBikes = company.getRentedBikes();
			for(Bike bike : rentedBikes) {
				System.out.println(bike);
			}
			
			//得到客户c5租到的车
			Bike c5Bike = company.getBike(c5);
			System.out.println("客户c5的车是："+c5Bike);
			//将客户5的租车协议终止
			company.terminateRental(c5);
			System.out.println("客户c5的车是："+company.getBike(c5));
			//再次查看当前可用车数量
			System.out.println("普通自行车可租数量："+company.availableBikes(BikeType.ROAD));
			System.out.println("电瓶车可租数量："+company.availableBikes(BikeType.ELECTRIC));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
