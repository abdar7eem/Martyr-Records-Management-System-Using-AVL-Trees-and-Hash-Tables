package MartyrScreen;

public class Martyr {

	private String mname;
	private String date;
	private int age;
	private String location;
	private String district;
	private String gender;

	public Martyr(String mname, String date, int age, String location, String district, String gender) {
		super();
		this.mname = mname;
		this.date = date;
		this.age = age;
		this.location = location;
		this.district = district;
		this.gender = gender;
	}

	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return mname + ","+   date +","+age+"," + location + ","+ district
				+","+gender;
	}








}
