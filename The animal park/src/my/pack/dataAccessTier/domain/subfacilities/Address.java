package my.pack.dataAccessTier.domain.subfacilities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name="city",nullable = true)
	private String city;
	
	@Column(name="street",nullable = true)
	private String street;
	
	@Column(name="house_num",nullable = true)
	private int house_num;
	
	public Address() {};
	
	public Address (String city, String  street, int house_num) {
		this.city=city;
		this.street=street;
		this.house_num=house_num;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getHouse_num() {
		return house_num;
	}
	public void setHouse_num(int house_num) {
		this.house_num = house_num;
	}
	
	

}
