package my.pack.serviceTier.services.facilities;

import java.util.List;
import java.util.Set;
import java.util.Stack;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;

public interface AreaService {
	
	/**
	 * Area part
	 */
	public void CreateArea(Area area);
	public Area GetAreaByNumber(String num);
	public Area GetAreaByNumber_With_Animals(String num);
	public Area GetAreaByNumber_With_LandPortions(String num);
	public Area GetAreaByNumber_With_Bathrooms(String num);
	public List<Area> GetAllAreas();
	public List<Area> GetAllAreasWithVets();
	public void UpdateArea (Area area);
	public void DeleteArea (Area area);
	
	public void PopulateAnimalByCategory(String category, Area area);
	public void PutAnimals_In_LandPortion(String area_num,Stack<String> animal_categories);
	public void SetVetOfArea(int area_num, String first_name, String last_name);
	public void SetCaregiverOfArea(int area_num, String first_name, String last_name);

	
	/**
	 * Animals part
	 */
	public void CreateAnimal(Animal animal);
	public Animal ReadAnimalById(String id);
	public List<Animal> GetAllAnimals();
	public List<String> GetOnlyAnimalsIds();
	public List<Animal> GetUncheckedAnimals();
	public void DeleteAnimal(Animal animal);
	
	public List<Animal> ReadAnimalByCategory(String category);
	public List<String> ReadOnlyGroupsByCategory(String category);
	public List<Animal> ReadByVet(String first_name, String last_name);
	public void UpdateAnimalAreaNum(String category,String area_num);
	public void UpdateAnimalFood(String category,String food);
	public void MarkAnimalThatNeedVetCheck(String id,char need_check);
	/**
	 * Land_Portion part
	 */	
	public void Create_Land_Portion(Land_Portion land_portion);
	public Land_Portion Read_Land_Portion(String id);
	public String Read_Land_Portion_By_Animal(Animal an);
	public void Update_Land_Portion(Land_Portion land_portion);
	public void Delete_Land_Portion(Land_Portion land_portion);
	public List<Land_Portion> GetAll_Land_Portion();
	public List<Land_Portion> getAllLPWithProblemsAndAreas();
	public List<Land_Portion> getAllLPWithAnimalsId();
	public void PopulateLandPortions(String area_num, List<String> land_portions_temp_id);
	public void SetProblemToLandPortion(String problem,String area_num, String lp_num);
	public void RemoveProblemsForLandPortion(String lp_id);
	public void SetAnimalsLandPortion(String lp_id, String an_id,char clean_lp_flag);
}
