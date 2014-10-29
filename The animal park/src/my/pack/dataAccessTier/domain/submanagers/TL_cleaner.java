package my.pack.dataAccessTier.domain.submanagers;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.superclasses.Manager;

@Entity
@DiscriminatorValue("TL_cleaner")
public class TL_cleaner extends Manager {


	public TL_cleaner() {}
	
	public TL_cleaner(String first_name, String last_name, int age,
			char gender, long id, Address address, int salary,
			long cell_number,long office_number
			) {

		super(first_name, last_name, age, gender, id, 
				address, salary, cell_number,office_number);
	}
	
	@Override
	public String toString() {
		return "TL_cleaner";
	}

}
