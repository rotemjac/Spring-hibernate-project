package my.pack.serviceTier.services.subManagers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.serviceTier.services.facilities.FilterService;
import my.pack.serviceTier.services.topManagers.ManagerService_impl;

@Service(value="Security_chief_Service")
public class Security_chief_Service_impl extends ManagerService_impl
										 implements Security_chief_Service {

	@Autowired
	@Qualifier("FilterTo_FacilitiesService")
	private FilterService filter_facService;
	
	@Override
	public int Number_Of_visitors_In_Park() {
		return filter_facService.Number_Of_visitors_In_Park();
	}

	@Override
	public List<Camera> Get_All_Cameras() {
		return filter_facService.Get_All_Cameras();
	}

	@Override
	public List<Gate> Get_All_Gates() {
		return filter_facService.Get_All_Gates();
	}

	@Override
	public List<Fence> Get_All_Fences() {
		return filter_facService.Get_All_Fences();
	}

}
