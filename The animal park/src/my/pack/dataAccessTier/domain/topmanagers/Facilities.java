package my.pack.dataAccessTier.domain.topmanagers;

import javax.persistence.Cacheable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import my.pack.dataAccessTier.domain.subfacilities.Economic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This class need to have 2 parts: economic and logistic 
 */

@Entity
@Qualifier("the_park_facilities")
public class Facilities {
	
	@Id
	@GeneratedValue
	private int db_id;
	
	@Embedded
	private Economic_facilities eco_fac;
	
	@Embedded
	private Logistic_facilities log_fac;

	public Facilities() {}
	
	public Facilities(Logistic_facilities log_fac,Economic_facilities eco_fac) {
		super();
		this.log_fac = log_fac;
		this.eco_fac = eco_fac;
	}
	
	
	
	public int getDb_id() {
		return db_id;
	}

	public Logistic_facilities getLog_fac() {
		return log_fac;
	}

	public void setLog_fac(Logistic_facilities log_fac) {
		this.log_fac = log_fac;
	}

	public Economic_facilities getEco_fac() {
		return eco_fac;
	}

	public void setEco_fac(Economic_facilities eco_fac) {
		this.eco_fac = eco_fac;
	}





	
	
	
	
}
