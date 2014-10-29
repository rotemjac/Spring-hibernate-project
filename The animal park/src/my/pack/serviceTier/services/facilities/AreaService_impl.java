package my.pack.serviceTier.services.facilities;

import java.util.List;
import java.util.Set;
import java.util.Stack;

import my.pack.dataAccessTier.dao.facilities.AnimalDao;
import my.pack.dataAccessTier.dao.facilities.AreaDao;
import my.pack.dataAccessTier.dao.facilities.Land_PortionDao;
import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="AreaService")
public class AreaService_impl implements AreaService {

	/**
	 * Fields
	 */
	
	@Autowired
	//@Qualifier("${DataAccess.AreaDao}")
	@Qualifier("AreaDao")
	private AreaDao areaDao;
	
	@Autowired
	//@Qualifier("${DataAccess.AnimalDao}")
	@Qualifier("AnimalDao")
	private AnimalDao anDao;
	
	@Autowired
	//@Qualifier("${DataAccess.Land_PortionDao}")
	@Qualifier("Land_PortionDao")
	private Land_PortionDao land_Portiondao;
	
		
	/**
	 * Constructor
	 */
	
	public AreaService_impl() {
		System.out.println("Hi! i'm in AreaService_impl constructor");
	}
	
	/**
	 * Methods - AreaDao
	 */
	//Creation methods requiers the highest isolation level
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@Override
	public void CreateArea(Area area) {
		areaDao.create(area);		
	}

	@Transactional(readOnly=true)
	@Override
	public Area GetAreaByNumber(String num) {
		return areaDao.get(num);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Area GetAreaByNumber_With_Animals(String num) {
		return areaDao.get_with_animals(num);
	}

	@Transactional(readOnly=true)
	@Override
	public Area GetAreaByNumber_With_LandPortions(String num) {
		return areaDao.get_with_land_portions(num);
	}

	@Transactional(readOnly=true)
	@Override
	public Area GetAreaByNumber_With_Bathrooms(String num) {
		return areaDao.get_with_bathrooms(num);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Area> GetAllAreas() {
		return areaDao.get_all_areas();
	}

	
	@Override
	public List<Area> GetAllAreasWithVets() {
		return areaDao.get_all_areas_with_vet();
	}
	
	@Transactional()
	@Override
	public void UpdateArea(Area area) {
		areaDao.update(area);		
	}

	@Transactional()
	@Override
	public void DeleteArea(Area area) {
		areaDao.delete(area);		
	}
	
	@Transactional()
	//Total opened sessions for method:3
	@Override
	public void PopulateAnimalByCategory(String category,Area area) {
		
		//Update area-first session
		List<Animal> an_list=anDao.readByCategory(category);
		
		 area=areaDao.get_with_animals(area.getNumber());
		
		for(Animal an:an_list) {
			area.getAnimals_in_area().add(an);
		}
		//second session
		UpdateArea(area);
		
		//Update animals-third session
		UpdateAnimalAreaNum(category, area.getNumber());
		
	}
	
	//Total opened sessions for method: (number of portions) +2
	
	//TODO: Check if the line with the "i" setup is good
	//  	in the case where the area contains land portions
	//		before this method executed
	
	@Transactional()
	@Override
	public void PopulateLandPortions(String area_num,List<String> land_portions_temp_id) {
		
		//TODO: check if It's realy a bad practice to use Service methods cause they have there one 
		//Transaction configurations
		//Area area=GetAreaByNumber_With_LandPortions(area_num);//session Opened 
		Area area=areaDao.get_with_land_portions(area_num);//session Opened
		
		int i=area.getLand_portions_in_area().size();
		
		
		for (String temp_id:land_portions_temp_id) {
		
		//TODO: check if It's not good to use Service methods cause they have there one 
		//Transaction configurations
		//Land_Portion lp=Read_Land_Portion(temp_id);
		Land_Portion lp=land_Portiondao.read(temp_id);
		
		lp.setLand_portion_id("area"+area_num+"_lp"+i);
		lp.setArea(area);
		
		//TODO: check if It's realy a bad practice to use Service methods cause they have there one 
		//Transaction configurations
		//Update_Land_Portion(lp);//session Opened
		land_Portiondao.update(lp);//session Opened
		
		area.getLand_portions_in_area().add(lp);
		i++;
		}
		
		//TODO: check if It's not good to use Service methods cause they have there one 
		//Transaction configurations
		//UpdateArea(area);//session Opened 
		areaDao.update(area);//session Opened 
	}

	/**
	 * Methods - AnimalDao
	 */
	
	//Creation methods requiers the highest isolation level
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@Override
	public void CreateAnimal(Animal animal) {
		anDao.create(animal);
	}

	@Transactional(readOnly=true)
	@Override
	public Animal ReadAnimalById(String id) {
		return anDao.readById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Animal> ReadByVet(String first_name, String last_name) {
		return anDao.readByVet(first_name, last_name);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Animal> GetAllAnimals() {
		return anDao.getAllAnimals();
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<String> GetOnlyAnimalsIds() {
		return anDao.getOnlyAnimalsIds();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Animal> GetUncheckedAnimals() {
		return anDao.get_unchecked_animals();

	}

	@Transactional()
	@Override
	public void DeleteAnimal(Animal animal) {
		anDao.delete(animal);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Animal> ReadAnimalByCategory(String category) {
		return anDao.readByCategory(category);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<String> ReadOnlyGroupsByCategory(String category) {
		return anDao.read_only_groups_by_category(category);
	}

	@Transactional()
	@Override
	public void UpdateAnimalAreaNum(String category, String area_num) {
		anDao.updateAreaNum(category, area_num);
	}

	@Transactional()
	@Override
	public void UpdateAnimalFood(String category, String food) {
		anDao.updateFood(category, food);
	}

	/**
	 * Methods - Land_PortionDao
	 */
	
	//Creation methods requiers the highest isolation level
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@Override
	public void Create_Land_Portion(Land_Portion land_portion) {
		land_Portiondao.create(land_portion);		
	}

	@Transactional(readOnly=true)
	@Override
	public Land_Portion Read_Land_Portion(String id) {
		return land_Portiondao.read(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public String Read_Land_Portion_By_Animal(Animal an) {		
		return 	land_Portiondao.read_land_portion_by_animal(an);
	}

	@Transactional()
	@Override
	public void Update_Land_Portion(Land_Portion land_portion) {
		land_Portiondao.update(land_portion);
	}

	@Transactional()
	@Override
	public void Delete_Land_Portion(Land_Portion land_portion) {
		land_Portiondao.delete(land_portion);		
	}

	@Transactional(readOnly=true)
	@Override
	public List<Land_Portion> GetAll_Land_Portion() {
		return land_Portiondao.getAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Land_Portion> getAllLPWithProblemsAndAreas() {
		return land_Portiondao.get_all_lp_with_problems_and_areas();
	}

	@Transactional()
	@Override
	public void PutAnimals_In_LandPortion(String area_num,
			Stack<String> animal_categories) {
		areaDao.put_animals_in_land_portion(area_num, animal_categories);		
	}

	@Transactional()
	@Override
	public void SetProblemToLandPortion(String problem, String area_num,
														String lp_num) {

		land_Portiondao.set_problem_to_land_portion(problem, area_num, lp_num);
	}

	@Transactional()
	@Override
	public void RemoveProblemsForLandPortion(String lp_id) {
		land_Portiondao.remove_problems_for_land_portion(lp_id);		
	}

	@Transactional()
	@Override
	public void MarkAnimalThatNeedVetCheck(String id,char need_check) {
		anDao.mark_animal_that_need_vet_check(id, need_check);
	}

	@Transactional()
	@Override
	public void SetVetOfArea(int area_num, String first_name, String last_name) {
		areaDao.set_worker_to_area("Vet",area_num, first_name, last_name);	
	}

	@Transactional()
	@Override
	public void SetCaregiverOfArea(int area_num, String first_name,String last_name) {
		areaDao.set_worker_to_area("Caregiver",area_num, first_name, last_name);	
		
	}

	@Transactional(readOnly=true)
	@Override
	public List<Land_Portion> getAllLPWithAnimalsId() {
		return land_Portiondao.get_all_lp_with_animals_id();
	}

	@Transactional()
	@Override
	public void SetAnimalsLandPortion(String lp_id, String an_id,
			char clean_lp_flag) {
		land_Portiondao.set_animals_land_portion(lp_id, an_id, clean_lp_flag);		
	}



}
