package my.pack.dataAccessTier.domain.subfacilities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Fence extends Sub_Facility {
	
	@Column(name="Fence_Id")
	private String id;
	
	@Column(name="Is_Fence_OK")
	private char is_ok;
	
	//Don't know how to implement this
	//private char is_touched;
	
	public Fence() {}
	
	public Fence(String fence_id) {
		this.id=fence_id;
		this.is_ok = 'Y';
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char getIs_ok() {
		return is_ok;
	}

	public void setIs_ok(char is_ok) {
		this.is_ok = is_ok;
	}
	

}
