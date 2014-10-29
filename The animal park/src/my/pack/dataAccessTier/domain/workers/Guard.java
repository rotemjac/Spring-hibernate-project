package my.pack.dataAccessTier.domain.workers;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.superclasses.Worker;

@Entity
public class Guard extends Worker {
	
	@Column(name="Weapon_Number")
	private long weapon_num;
	
	public Guard() {}
	
	public Guard(String first_name, String last_name, int age,
		      char gender, long id, Address address,int salary, long cell_number,
		      long weapon_num) {
	
	super(first_name, last_name, age, gender, cell_number, address, salary, cell_number);
	this.weapon_num=weapon_num;
	}
	
	
	public long getWeapon_num() {
		return weapon_num;
	}

	public void setWeapon_num(long weapon_num) {
		this.weapon_num = weapon_num;
	}

	@Override
	public String toString() {
		return "Guard";
	}

}
