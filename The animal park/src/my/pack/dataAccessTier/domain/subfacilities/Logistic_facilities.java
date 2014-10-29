package my.pack.dataAccessTier.domain.subfacilities;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Logistic_facilities {
	

	@OneToMany
	private List<Area> areas;	
	
	@ElementCollection
	//@Column(name="Cameras")
	List<Camera> cameras;
	
	@ElementCollection
	//@Column(name="Fences")
	List<Fence> fences;
	
	@ElementCollection
	//@Column(name="Gate")
	List<Gate> gates;
	
	/**
	 * Constructors
	 */
	public Logistic_facilities() {
		this.areas = new LinkedList<Area>();
		this.cameras = new LinkedList<Camera>();
		this.fences = new LinkedList<Fence>();
		this.gates = new LinkedList<Gate>();
	}

	public Logistic_facilities(List<Area> areas, List<Camera> cameras,
			List<Fence> fences, List<Gate> gates) {

		this.areas = new LinkedList<Area>();
		this.areas = areas;
		this.cameras = new LinkedList<Camera>();
		this.cameras = cameras;
		this.fences = new LinkedList<Fence>();
		this.fences = fences;
		this.gates = new LinkedList<Gate>();
		this.gates = gates;
	}

	
	/**
	 * Getters and Setters
	 */

	public List<Area> getAreas() {
		return areas;
	}


	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}



	public List<Camera> getCameras() {
		return cameras;
	}

	
	public void setGates(List<Gate> gates) {
		this.gates = gates;
	}

	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}
	
	public void setFences(List<Fence> fences) {
		this.fences = fences;
	}


	public List<Fence> getFences() {
		return fences;
	}


	public List<Gate> getGates() {
		return gates;
	}






	
}
