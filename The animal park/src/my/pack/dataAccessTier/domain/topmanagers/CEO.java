package my.pack.dataAccessTier.domain.topmanagers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.superclasses.Manager;

@Entity
public class CEO extends Manager {

	@ElementCollection
	@CollectionTable(name = "All_addresses_of_CEO")
	List<Address> addresses;

	
	@OneToOne(fetch=FetchType.LAZY)
	private Facilities facilities;
	
	public CEO() {}
	
	public CEO(String first_name, String last_name, int age, char gender,
			long id, List<Address> addresses, int salary, long cell_number,
			long office_number) {
		
		super(first_name, last_name, age, gender, id, 
				null, salary, cell_number,office_number);
		
		this.addresses=new ArrayList<Address>();
		this.addresses=addresses;
		
		
	}
	
	public Facilities getFacilities() {
		return facilities;
	}

	public void setFacilities(Facilities facilities) {
		this.facilities = facilities;
	}

	@Override
	public String toString() {
		return "CEO";
	}


}
