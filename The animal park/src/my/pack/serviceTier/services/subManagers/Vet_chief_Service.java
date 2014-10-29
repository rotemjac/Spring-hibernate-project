package my.pack.serviceTier.services.subManagers;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.serviceTier.services.topManagers.ManagerGeneralService;
import my.pack.serviceTier.services.topManagers.ManagersExtraFeaturesService;

public interface Vet_chief_Service 
extends ManagerGeneralService,ManagersExtraFeaturesService {
	
	public List<Animal> Get_All_Animals();
	public List<Animal> Get_All_Animals_In_Area(String area_num);
	public List<Animal> Get_All_Animals_That_Need_A_Check();
	public void Set_Animal_Food(String category, String food_type);
	public void SetVetOfArea(int area_num,String first_name, String last_name);
	public List<Object[]> Get_Vets_Areas_Mapping();
	public List<String> ReadOnlyGroupsByCategory(String category);


}
