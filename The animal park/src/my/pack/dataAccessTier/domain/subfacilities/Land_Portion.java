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
import javax.persistence.ManyToOne;


@Entity
public class Land_Portion {
	
	@Id
	@GeneratedValue
	@Column(name="Land_Portion_DB_id")
	private int land_portion_db_id;
	
	@Column(name="Land_Portion_Id")
	private String land_portion_id;
	
	@Column(name="Need_To_Fix")
	private char need_to_fix;
	
	/**
	 * TODO:Think about the mapping here. 
	 * do you want @OneToMany (and reference to Animals)
	 * or @ElementCollection (and just reference to Animals' id's)? 
	 */
	@ElementCollection
	@Column(name="Animals_Ids")
	private List<String> animals_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="The_corresponding_Area")
	private Area area;

	@ElementCollection
	@Column(name="List_Of_Problems")
	private List<String> list_of_problems;

	
	/**
	 * Constructors
	 */
	
	public Land_Portion() {}
	
	public Land_Portion(String land_portion_id, List<String> animals_id,
			List<String> list_of_problems, Area area) {
		
		this.land_portion_id = land_portion_id;
		this.animals_id=new LinkedList<String>();
		this.animals_id = animals_id;
		this.need_to_fix='N';
		this.list_of_problems=new LinkedList<String>();
		this.list_of_problems = list_of_problems;
		this.area = area;
	}

	/**
	 * Getters and Setters
	 */

	
	public int getLand_portion_db_id() {
		return land_portion_db_id;
	}
	
	public String getLand_portion_id() {
		return land_portion_id;
	}


	public void setLand_portion_id(String land_portion_id) {
		this.land_portion_id = land_portion_id;
	}

	
	public char getNeed_to_fix() {
		return need_to_fix;
	}

	public void setNeed_to_fix(char need_to_fix) {
		this.need_to_fix = need_to_fix;
	}

	public List<String> getAnimals_id() {
		return animals_id;
	}
	
	public void setAnimals_id(List<String> animals_id) {
		this.animals_id = animals_id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<String> getList_of_problems() {
		return list_of_problems;
	}
	
	
	
	

}
