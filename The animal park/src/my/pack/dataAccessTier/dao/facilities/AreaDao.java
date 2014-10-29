package my.pack.dataAccessTier.dao.facilities;

import java.util.List;
import java.util.Set;
import java.util.Stack;

import my.pack.dataAccessTier.domain.subfacilities.Area;

public interface AreaDao {
	
	public void create(Area area);
	public Area get(String num);
	public Area get_with_animals(String num);
	public Area get_with_land_portions(String num);
	public Area get_with_bathrooms(String num);
	public List<Area> get_all_areas();
	public List<Area> get_all_areas_with_vet();
	public void update(Area area);
	public void delete(Area area);
	
	public void put_animals_in_land_portion(String area_num,Stack<String> animal_categories);
	public void set_worker_to_area(String pos,int area_num,String first_name,String last_name);

	//I created this in order to check the SPeL feature(Look at the
	// ara_num field in the animal class)
	public int get_area_num_by_animal_id(String an_id);
}
