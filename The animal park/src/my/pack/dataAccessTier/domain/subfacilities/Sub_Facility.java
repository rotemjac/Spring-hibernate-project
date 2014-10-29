package my.pack.dataAccessTier.domain.subfacilities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Sub_Facility {
	
/*	@Id
	@GeneratedValue
	@Column(name="Facility_Db_Id")
	private String db_id;*/
	
	@Column(name="Facility_Id")
	private String id;

	/**
	 * Constructor
	 */
	public Sub_Facility() {}
	
	public Sub_Facility(String id) {
		this.id=id;
	}
	
	/**
	 * Getters and Setters
	 */
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
