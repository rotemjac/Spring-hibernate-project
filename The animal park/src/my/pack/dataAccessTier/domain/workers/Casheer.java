package my.pack.dataAccessTier.domain.workers;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.superclasses.Worker;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Value;

@Entity
public class Casheer extends Worker {
	
	public Casheer() {}
	public Casheer(String first_name, String last_name, int age,
			      char gender, long id, Address address,int salary, long cell_number) {
		
		super(first_name, last_name, age, gender, cell_number, address, salary, cell_number);

	}
	
/*	@Value(" #{the_park_facilities.getEco_fac().getTickets_sold()}	")
	private int num_tickets_sold;*/
	
/*	public void AddTicket() {
		this.num_tickets_sold++;
	}*/
	
	@Override
	public String toString() {
		return "Casheer";
	}

	
}
