import java.util.Calendar;

public class CustomerRecord {
	private Customer customer;
	private String uniqueNumber;    //客户的编号
	private Calendar recordDate = Calendar.getInstance();
	private boolean isGoldClass;
	private static int i=0;
	public CustomerRecord(Customer customer, boolean isGoldClass) {
		this.customer = customer;
		this.isGoldClass = isGoldClass;
		String[] name = customer.getName().split(" ");
		this.uniqueNumber = name[0].substring(0,1)+name[1].substring(0,1)+"-"+recordDate.get(Calendar.YEAR)+String.format("%02d", i);
		i++;
		if(i>99) {
			i=0;
		}
	}
	public String getCustomerFullName() {
		return customer.getName();
	}
	public Calendar getBirthday() {
		Calendar birth = Calendar.getInstance();
		birth.setTime(customer.getBirthday());
		return birth;
	}
	public Calendar getRecordDate() {
		return recordDate;
	}
	public boolean isGoldClass() {
		return isGoldClass;
	}
	@Override
	public String toString() {
		return "CustomerRecord [customer=" + customer + ", uniqueNumber=" + uniqueNumber + ", recordDate=" + recordDate
				+ ", isGoldClass=" + isGoldClass + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + (isGoldClass ? 1231 : 1237);
		result = prime * result + ((recordDate == null) ? 0 : recordDate.hashCode());
		result = prime * result + ((uniqueNumber == null) ? 0 : uniqueNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerRecord other = (CustomerRecord) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (isGoldClass != other.isGoldClass)
			return false;
		if (recordDate == null) {
			if (other.recordDate != null)
				return false;
		} else if (!recordDate.equals(other.recordDate))
			return false;
		if (uniqueNumber == null) {
			if (other.uniqueNumber != null)
				return false;
		} else if (!uniqueNumber.equals(other.uniqueNumber))
			return false;
		return true;
	}
}
