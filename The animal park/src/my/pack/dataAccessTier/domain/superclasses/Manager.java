package my.pack.dataAccessTier.domain.superclasses;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import my.pack.dataAccessTier.domain.subfacilities.Address;

@Entity
@DiscriminatorValue("Manager")
public abstract class Manager extends Worker {
	

	
	private int number_of_workers;
	private long office_number;
	@OneToMany(cascade=CascadeType.PERSIST)
	 @JoinTable(
	            name="Manager_Workers_Mapping",
	            joinColumns = @JoinColumn( name="Manager_id"),
	            inverseJoinColumns = @JoinColumn( name="Worker_id")
	        )
	private List<Worker> workers_list;
	
	/**
	TODO: change the shallow copy of the workers_list field in the constructor
	 */
	

	public Manager(String first_name, String last_name, int age,
			       char gender, long id, Address address, int salary, long cell_number,
			       long office_number) 
	{
	
	super(first_name, last_name, age, gender, id, address, salary,cell_number);
	
	this.number_of_workers=0;
	this.office_number=office_number;	
	this.workers_list=new ArrayList<>();
	
	}
	
	public Manager() {}
	
	public int getNumber_of_workers() {
		return number_of_workers;
	}
	
	public void setNumber_of_workers(int number_of_workers) {
		this.number_of_workers = number_of_workers;
	}
	
	public long getOffice_number() {
		return office_number;
	}
	
	public void setOffice_number(long office_number) {
		this.office_number = office_number;
	}
	
	public List<Worker> getWorkers_list() {
		return workers_list;
	}
	
	public void setWorkers_list(List<Worker> workers_list) {
		this.workers_list = workers_list;
	}
	
	
	
	

}
