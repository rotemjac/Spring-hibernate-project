package my.pack.serviceTier.services.subManagers;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.serviceTier.services.topManagers.ManagerGeneralService;
import my.pack.serviceTier.services.topManagers.ManagersExtraFeaturesService;

public interface TL_caregiver_Service 
extends ManagerGeneralService,ManagersExtraFeaturesService {
	
	public void SetCaregiverOfArea(int area_num, String first_name, String last_name);
	public List<Land_Portion> GetAllLPWithAnimalsId();
	public List<String> GetOnlyAnimalsIds();
	public void SetAnimalsLandPortion(String lp_id, String an_id,char clean_lp_flag);

}
