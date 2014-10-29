package my.pack.dataAccessTier.domain.subfacilities;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import my.pack.dataAccessTier.domain.workers.Caregiver;
import my.pack.dataAccessTier.domain.workers.Vet;
import my.pack.webTier.clients.MainGuis.The_main_execution;


@Entity(name="Area")
public class Area {
	
	@Id
	@GeneratedValue
	@Column(name="DB_Id")
	private int db_id;
	
	@Column(name="Area_Number")
	private String number;
	
	@Column(name="Indoor_Or_Outdoor")
	private String in_or_out;
	

	@OneToMany()
	@JoinColumn(name="The_Animals_Area") 
	private List<Animal> animals_in_area;
	
	@OneToMany(mappedBy="area")
	//@Column(name="Land_Portions_In_Area")
	private List<Land_Portion> land_portions_in_area;
	

	//TODO:Need to give here a 3rd table name
	@ElementCollection()
	@Column(name="Bathrooms")
	private List<Bathroom> bathrooms;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="The_vet_responsible")
	private Vet the_vet;
	
	@OneToOne(fetch=FetchType.LAZY)	 
	@JoinTable(
            name="Areas_Caregivers_Mapping",
            joinColumns = @JoinColumn( name="Area_num"),
            inverseJoinColumns = @JoinColumn( name="Caregiver_id")
        )
	//@JoinColumn(name="The_caregiver_responsible")
	private Caregiver the_caregiver;
	
	/**
	 * Constructors
	 */
	
	public Area() {}
	
	public Area(String number, String in_or_out, List<Animal> animals_In_Area,
			List<Land_Portion> portions, List<Bathroom> bathrooms
			) {
		
		this.number = number;
		this.in_or_out = in_or_out;
		this.animals_in_area =new LinkedList<Animal>();
		this.animals_in_area = animals_In_Area;
		this.land_portions_in_area=new LinkedList<Land_Portion>();
		this.land_portions_in_area = portions;
		this.bathrooms =new LinkedList<Bathroom>();
		this.bathrooms = bathrooms;
	}

	
	/**
	 * Getters and Setters
	 */
	
	
	public int getDb_id() {
		return db_id;
	}
	
	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}

	public String getIn_or_out() {
		return in_or_out;
	}

	public void setIn_or_out(String in_or_out) {
		this.in_or_out = in_or_out;
	}

	public List<Animal> getAnimals_in_area() {
		return animals_in_area;
	}

	public void setAnimals_in_area(List<Animal> animals_in_area) {
		this.animals_in_area = animals_in_area;
	}

	public List<Land_Portion> getLand_portions_in_area() {
		return land_portions_in_area;
	}

	public void setLand_portions_in_area(List<Land_Portion> land_portions_in_area) {
		this.land_portions_in_area = land_portions_in_area;
	}

	public List<Bathroom> getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(List<Bathroom> bathrooms) {
		this.bathrooms = bathrooms;
	}

	public Vet getThe_vet() {
		return the_vet;
	}

	public void setThe_vet(Vet the_vet) {
		this.the_vet = the_vet;
	}

	public Caregiver getThe_caregiver() {
		return the_caregiver;
	}

	public void setThe_caregiver(Caregiver the_caregiver) {
		this.the_caregiver = the_caregiver;
	}
	
	
}
