package my.pack.dataAccessTier.domain.workers;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.superclasses.Worker;

@Entity
public class Vet extends Worker {

	@OneToMany(mappedBy="the_vet")
	private List<Area> areas_of_responsibility;
	
	public Vet() {}
	
	public Vet(String first_name, String last_name, int age,
		      char gender, long id, Address address,int salary, long cell_number) {
	
	super(first_name, last_name, age, gender, cell_number, address, salary, cell_number);
	this.areas_of_responsibility=new ArrayList<>();
	}
	
	

	public List<Area> getAreas_of_responsibility() {
		return areas_of_responsibility;
	}


	@Override
	public String toString() {
		return "Vet";
	}
}
