package my.pack.dataAccessTier.domain.subfacilities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Bathroom {
	
	@Column(name="Bathroom_Id")
	private String id;
	
	@Column(name="Is_Clean")
	private char is_clean;

	public Bathroom() {}
	
	
	public Bathroom(String bathroom_id,char is_clean) {
		this.id=bathroom_id;
		this.is_clean = is_clean;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	


}
