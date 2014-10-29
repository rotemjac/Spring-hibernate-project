package my.pack.dataAccessTier.domain.subfacilities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Gate  {
	
	@Column(name="Gate_Id")
	private String id;
	
	@Column(name="Is_Close")
	private char is_close;

	public Gate() {}
	
	public Gate(String gate_id, char is_close) {
		this.id=gate_id;
		this.is_close = is_close;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char getIs_close() {
		return is_close;
	}

	public void setIs_close(char is_close) {
		this.is_close = is_close;
	}
	
	

}
