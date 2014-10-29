package my.pack.dataAccessTier.dao.facilities;

import java.util.List;
import java.util.Map;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;

public interface Land_PortionDao {
	
	public void create(Land_Portion land_portion);
	public Land_Portion read(String id);
	public String read_land_portion_by_animal(Animal an);
	public void update(Land_Portion land_portion);
	public void delete(Land_Portion land_portion);
	public List<Land_Portion> getAll();
	public List<Land_Portion> get_all_lp_with_problems_and_areas();
	public List<Land_Portion> get_all_lp_with_animals_id();
	public void set_animals_land_portion(String lp_id,String an_id,char clean_lp_flag);
	public void set_problem_to_land_portion(String problem,String area_num, String lp_num);
	public void remove_problems_for_land_portion(String lp_id);
}
