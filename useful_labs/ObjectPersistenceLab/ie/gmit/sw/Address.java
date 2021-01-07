package ie.gmit.sw;

public class Address {
	private String street, city;
	private County county;

	public Address(String street, String city, County county) {
		this.street = street;
		this.city = city;
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return street + ", " + city + ", " + county;
	}
}