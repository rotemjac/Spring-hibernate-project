package my.pack.serviceTier.services.facilities;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Sub_Facility;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;

public interface FacilitiesService {

	/**
	 * General Facilities part
	 */
	public void CreateFacilities(Facilities fac);
	public Facilities GetFacilities();
	public void UpdateFacilities (Facilities fac);
	public void DeleteFacilities (Facilities fac);
	
	/**
	 * Logistic Facilities part
	 */
	
	/////Moved to here From the DAO layer
	public Logistic_facilities Create_Log_Fac(int gate_num,int camera_num, int fence_num);
	public void Create_Many_Sub_Facilities
									(Logistic_facilities log_fac,int gate_num,int fence_num,int camera_num);
	////
	
	public Logistic_facilities Get_Log_fac_with_cameras();
	public Logistic_facilities Get_Log_fac_with_gates();
	public Logistic_facilities Get_Log_fac_with_fences();
	
	/**
	 * Areas part in Logistic Facilities
	 */
	public void Add_Areas_To_Log_Fac(List<Area> areas);
	
	public AreaService getAreaService();
	public void Add_Bathroom_To_Area(String area_num,int number_of_bathrooms);
	
	/**
	 * Economic Facilities part
	 */
	
	public void Add_Or_Sub_Ticket(char oper);
	public int Get_Number_of_Tickets_Sold();
	public void AddOrSubPeopleOut(char oper);
	public int GetPeopleInside();
	
	/**
	 * Sub_Facility part
	 */
/*	public void Create_SubFacility(Sub_Facility sub_facility);
	public void Creating_Many_SubFacilities(String which_sub_fac,int num);
	public Sub_Facility Read_SubFacility(String id);
	public void Update_SubFacility(Sub_Facility sub_facility);
	public void Delete_SubFacility(Sub_Facility sub_facility);
	public List<Sub_Facility> GetAll_SubFacility(String the_chosen_sub_fac);*/
}
