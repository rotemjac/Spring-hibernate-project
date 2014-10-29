package my.pack.serviceTier.services.facilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;

@Service(value="FilterTo_FacilitiesService")
public class FilterService_Impl implements
		FilterService {
	
	@Autowired
	@Qualifier("FacilitiesService")
	private FacilitiesService fac_service;

	/**
	 * Constructor
	 */
	public FilterService_Impl () {
		System.out.println("I'm in FilterTo_FacilitiesService_Impl constructor!");
	}
	
	/**
	 * Vet_chief part
	 */
	
	
	@Override
	public List<Animal> Get_All_Animals() {
		return fac_service.getAreaService().GetAllAnimals();
	}

	
	@Override
	public List<Animal> Get_All_Animals_In_Area(String area_num) {
		return fac_service.getAreaService()
			   .GetAreaByNumber(area_num).getAnimals_in_area();
	}

	
	@Override
	public List<Animal> Get_All_Animals_That_Need_A_Check() {	
		return fac_service.getAreaService().GetUncheckedAnimals();
	}

	
	@Override
	public void Set_Animal_Food(String category, String food_type) {
		fac_service.getAreaService().UpdateAnimalFood(category, food_type);
	}
	
	
	@Override
	public void SetVetOfArea(int area_num, String first_name, String last_name) {
		fac_service.getAreaService().SetVetOfArea(area_num, first_name, last_name);
	}
	
	
	@Override
	public List<Object[]> Get_Vets_Areas_Mapping() {
		
		List<Area> areas_list=
				fac_service.getAreaService().GetAllAreasWithVets();
		
		List<Object[]> results=new ArrayList<>();
		int i=0;
		Object[] temp_arr; 
		
		for (Area ar:areas_list) {
			temp_arr= new Object[3];
			temp_arr[0]=ar.getNumber();
			temp_arr[1]=ar.getThe_vet().getFirst_name();
			temp_arr[2]=ar.getThe_vet().getLast_name();
			results.add(i,temp_arr);
			i++;
		}
		
		return results;
	}
	
	
	@Override
	public List<String> ReadOnlyGroupsByCategory(String category) {
		return 	fac_service.getAreaService().ReadOnlyGroupsByCategory(category);
	}



	/**
	 * Security_chief part
	 */

	
	@Override
	public int Number_Of_visitors_In_Park() {
		return fac_service.GetPeopleInside();
	}

	
	@Override
	public List<Camera> Get_All_Cameras() {
		return fac_service.Get_Log_fac_with_cameras().getCameras();
	}

	
	@Override
	public List<Gate> Get_All_Gates() {
		return fac_service.Get_Log_fac_with_gates().getGates();
	}

	
	@Override
	public List<Fence> Get_All_Fences() {
		return fac_service.Get_Log_fac_with_fences().getFences();
	}
	
	/**
	 * TL_caregiver part
	 */
	
	@Override
	public void SetCaregiverOfArea(int area_num, String first_name,String last_name) {
		fac_service.getAreaService().SetCaregiverOfArea(area_num, first_name, last_name);

	}

	
	@Override
	public List<Land_Portion> GetAllLPWithAnimalsId() {
		return fac_service.getAreaService().getAllLPWithAnimalsId();
	}
	
	
	@Override
	public List<String> GetOnlyAnimalsIds() {
		return fac_service.getAreaService().GetOnlyAnimalsIds();
	}

	
	/**
	 * TL_janitor part
	 */
	
	
	@Override
	public List<Land_Portion> GetAllLPWithProblemsAndAreas() {
		return fac_service.getAreaService().getAllLPWithProblemsAndAreas();
	}
	
	/**
	 * TL_casheer part
	 */
	
	
	@Override
	public int Get_Number_Of_Tickets_Sold() {
		return fac_service.Get_Number_of_Tickets_Sold();
	}
	
	/**
	 * Caregiver
	 */

	
	@Override
	public void SetProblemToLandPortion(String problem, String area_num,
			String lp_num) {
		fac_service.getAreaService().SetProblemToLandPortion(problem, area_num, lp_num);		
	}

	
	@Override
	public void RemoveProblemsForLandPortion(String lp_id) {
		fac_service.getAreaService().RemoveProblemsForLandPortion(lp_id);
	}

	
	@Override
	public void MarkAnimalThatNeedVetCheck(String id, char need_check) {
		fac_service.getAreaService().MarkAnimalThatNeedVetCheck(id, need_check);	
	}

	
	/**
	 * Vet
	 */
	
	
	@Override
	public List<Animal> ReadByVet(String first_name, String last_name) {
		return fac_service.getAreaService().ReadByVet(first_name, last_name);
	}

	
	@Override
	public void SetAnimalsLandPortion(String lp_id, String an_id,
			char clean_lp_flag) {
		fac_service.getAreaService().SetAnimalsLandPortion
									(lp_id, an_id, clean_lp_flag);
		
	}

	/**
	 * Casheer
	 */
	
	
	@Override
	public void Add_Or_SubTicket(char oper) {
		fac_service.Add_Or_Sub_Ticket(oper);
	}

	/**
	 * Guard 
	 */
	
	@Override
	public void AddOrSubPeopleOut(char oper) {
		fac_service.AddOrSubPeopleOut(oper);		
	}



}
