package my.pack.dataAccessTier.domain.superclasses;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import my.pack.dataAccessTier.domain.subfacilities.Address;



@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Worker_Type")
public abstract class Worker  {
	
	@Id
	@GeneratedValue
	private int worker_db_id;
	
	private String first_name;
	private String last_name;
	private int age;
	private char gender;
	private long id;
	
	@Embedded
	private Address address;
	
	private int salary;
	private long cell_number;
	
	public Worker(){};
	
	public Worker(String first_name, String last_name, int age,
			      char gender, long id, Address address,int salary, long cell_number) {
		
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.gender = gender;
		this.id = id;
		this.address = address;
		this.salary = salary;
		this.cell_number = cell_number;
	}
	
	
	
	public int getWorker_db_id() {
		return worker_db_id;
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
	
	
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	public long getCell_number() {
		return cell_number;
	}
	public void setCell_number(long cell_number) {
		this.cell_number = cell_number;
	}
	
}
