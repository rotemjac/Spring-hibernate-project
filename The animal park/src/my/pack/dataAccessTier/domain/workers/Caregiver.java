package my.pack.dataAccessTier.domain.workers;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.superclasses.Worker;

@Entity
public class Caregiver extends Worker {

	public Caregiver() {}
	
	public Caregiver(String first_name, String last_name, int age,
		      char gender, long id, Address address,int salary, long cell_number) {
	
	super(first_name, last_name, age, gender, cell_number, address, salary, cell_number);
	}
	
	//This is in order to fix the n+1 problem here
	@OneToOne(fetch=FetchType.LAZY)
	private Area area_of_responsibility;


	public void setArea_of_responsibility(Area area_of_responsibility) {
		this.area_of_responsibility = area_of_responsibility;
	}

	public Area getAreas_of_responsibility() {
		return area_of_responsibility;
	}

	@Override
	public String toString() {
		return "Caregiver";
	}
	
	
}
