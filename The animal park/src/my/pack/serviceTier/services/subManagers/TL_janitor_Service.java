package my.pack.serviceTier.services.subManagers;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.serviceTier.services.topManagers.ManagerGeneralService;
import my.pack.serviceTier.services.topManagers.ManagersExtraFeaturesService;

public interface TL_janitor_Service 
extends ManagerGeneralService,ManagersExtraFeaturesService {

	public List<Land_Portion> GetAllLPWithProblemsAndAreas();
	public void RemoveProblemsForLandPortion(String lp_id);

}
