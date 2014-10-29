package my.pack.serviceTier.services.subManagers;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.serviceTier.services.topManagers.ManagersExtraFeaturesService;
import my.pack.serviceTier.services.topManagers.ManagerGeneralService;

public interface Security_chief_Service 

extends ManagerGeneralService,ManagersExtraFeaturesService {
	
	public int Number_Of_visitors_In_Park();
	public List<Camera> Get_All_Cameras();
	public List<Gate> Get_All_Gates();
	public List<Fence> Get_All_Fences();

}
