package my.pack.serviceTier.services.facilities;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;

public interface FilterService {

	
	/**
	 * Vet_chief part
	 */
	
	public List<Animal> Get_All_Animals();
	public List<Animal> Get_All_Animals_In_Area(String area_num);
	public List<Animal> Get_All_Animals_That_Need_A_Check();
	public void Set_Animal_Food(String category, String food_type);
	public void SetVetOfArea(int area_num, String first_name, String last_name); 
	public List<Object[]> Get_Vets_Areas_Mapping();
	public List<String> ReadOnlyGroupsByCategory(String category);
	
	/**
	 * Security_chief part
	 */
	
	public int Number_Of_visitors_In_Park();
	public List<Camera> Get_All_Cameras();
	public List<Gate> Get_All_Gates();
	public List<Fence> Get_All_Fences();
	
	/**
	 * TL_caregiver part
	 */
	public void SetCaregiverOfArea(int area_num, String first_name,String last_name);
	public List<Land_Portion> GetAllLPWithAnimalsId();
	public List<String> GetOnlyAnimalsIds();
	public void SetAnimalsLandPortion(String lp_id, String an_id,char clean_lp_flag);

	/**
	 * TL_janitor part
	 */
	
	public List<Land_Portion> GetAllLPWithProblemsAndAreas();
	public void RemoveProblemsForLandPortion(String lp_id);

	/**
	 * TL_casheer part
	 */
	
	public int Get_Number_Of_Tickets_Sold();
	
	
	/**
	 * Casheer part
	 */
	
	public void Add_Or_SubTicket(char oper);
	
	/**
	 * Caregiver part
	 */
	public void SetProblemToLandPortion(String problem,String area_num, String lp_num);
	//This is defined for the vet as well in order to "close" the checking
	public void MarkAnimalThatNeedVetCheck(String id,char need_check);
	
	/**
	 * Guard part
	 */
	
	public void AddOrSubPeopleOut(char oper);

	/**
	 * Vet part
	 */
	public List<Animal> ReadByVet(String first_name, String last_name);
	
}	
	