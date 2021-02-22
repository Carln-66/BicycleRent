import java.util.Calendar;
import java.util.Date;

/**
* @Author: Carl
* @Date: 2021/2/8 9:20
* @Description:
*/
public class Customer {
	private String name;
	private Date birthday;
	public Customer(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
	}
	public String getName() {
		return name;
	}
	public Date getBirthday() {
		return birthday;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", birthday=" + birthday + "]";
	}
}
