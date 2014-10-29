package my.pack.dataAccessTier.domain.subfacilities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Value;


@Entity(name="Animal")
public class Animal {
	
	@Id
	@GeneratedValue
	@Column(name="Animal_DB_Id")
	private int db_id;
	
	@Column(name="Animal_Id")
	private String animal_id;
	
	@Column(name="Department")
	private String department;
	
	@Column(name="Series")
	private String series;
	
	@Column(name="Genus")
	private String genus;
	
		
	@Column(name="Species")
	private String species;
	
	@Column(name="Gender")
	private char gender;
	
	@Column(name="Age")
	private int age;
	
	@Column(name="Weight")
	private int weight;
	
	@Column(name="Food")
	private String food;
	
	@Column(name="Need_To_See_Vet")
	private char need_a_vet_check;
	
	@Column(name="Area_Num")
	//TODO: I failed to use the SPeL feature-it doesn't work for me
	//@Value("10")
	//@Value(value = "#{AreaDao.get_area_num_by_animal_id(animal_id)}")
	private String area_num;

	
	/**
	 * Constructors
	 */
	
	public Animal() {}
	

	public Animal(String animal_id, 
				  String department,String series, String genus,String species, 
				  char gender, int age, int weight,
				  String food, char need_a_vet_check) {
		
		this.animal_id = animal_id;
		this.department = department;
		this.series = series;
		this.genus = genus;
		this.species=species;
		this.gender = gender;
		this.age = age;
		this.weight = weight;
		this.food = food;
		this.need_a_vet_check = need_a_vet_check;

	}


	/**
	 * Getters and Setters
	 */

	
	
	public String getAnimal_id() {
		return animal_id;
	}

	public int getDb_id() {
		return db_id;
	}


	public void setAnimal_id(String animal_id) {
		this.animal_id = animal_id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	public String getSeries() {
		return series;
	}


	public void setSeries(String series) {
		this.series = series;
	}


	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public String getSpecies() {
		return species;
	}


	public void setSpecies(String species) {
		this.species = species;
	}


	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public char getNeed_a_vet_check() {
		return need_a_vet_check;
	}

	public void setNeed_a_vet_check(char need_a_vet_check) {
		this.need_a_vet_check = need_a_vet_check;
	}

	public String getArea_num() {
		return area_num;
	}


	public void setArea_num(String area_num) {
		this.area_num = area_num;
	}
	

}
