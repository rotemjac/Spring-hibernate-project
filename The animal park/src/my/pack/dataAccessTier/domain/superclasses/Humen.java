package my.pack.dataAccessTier.domain.superclasses;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import my.pack.dataAccessTier.domain.subfacilities.Address;


/**
 * Notice:this class isn't used because it gave me problems 
 * in the single to one strategy while using it 
 *
 */

//@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Humen {
	
	//@Id
	private String first_name;
	private String last_name;
	private int age;
	private char gender;
	private long id;
	//@Embedded
	private Address address;
	
	public Humen() {}
	
	public Humen(String first_name, String last_name, int age,
			char gender, long id, Address address) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.gender = gender;
		this.id = id;
		this.address = address;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}



