package my.pack.dataAccessTier.domain.subfacilities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TODO:Should use ENUM in order to make angle between -75 to 75.
 */

@Embeddable
public class Camera  {
	
	@Column(name="Camera_Id")
	private String id;
	
	@Column(name="Angle")
	private int angle;
	
	@Column(name="Are_People_In_Area")
	private char rec_people;
	
	public Camera() {}
	
	public Camera(String camera_id) {
		this.id=camera_id;
		this.angle=0;
		this.rec_people='N';
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public char getRec_people() {
		return rec_people;
	}
	public void setRec_people(char rec_people) {
		this.rec_people = rec_people;
	}
	
	

}
