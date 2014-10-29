package my.pack.serviceTier.services.subManagers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.serviceTier.services.facilities.FilterService;
import my.pack.serviceTier.services.topManagers.ManagerService_impl;

@Service(value="TL_janitor_Service")
public class TL_janitor_Service_impl extends ManagerService_impl implements
		TL_janitor_Service {
	
	@Autowired
	@Qualifier("FilterTo_FacilitiesService")
	private FilterService filter_facService;

	@Override
	public List<Land_Portion> GetAllLPWithProblemsAndAreas() {
		return filter_facService.GetAllLPWithProblemsAndAreas();
	}

	@Override
	public void RemoveProblemsForLandPortion(String lp_id) {
		filter_facService.RemoveProblemsForLandPortion(lp_id);
	}

}
