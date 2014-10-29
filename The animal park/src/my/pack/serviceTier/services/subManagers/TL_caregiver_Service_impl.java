package my.pack.serviceTier.services.subManagers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.serviceTier.services.facilities.FilterService;
import my.pack.serviceTier.services.topManagers.ManagerService_impl;

@Service(value="TL_caregiver_Service")
public class TL_caregiver_Service_impl extends ManagerService_impl implements
		TL_caregiver_Service {
	
	@Autowired
	@Qualifier("FilterTo_FacilitiesService")
	private FilterService filter_facService;

	@Override
	public void SetCaregiverOfArea(int area_num, String first_name, String last_name) {
		filter_facService.SetCaregiverOfArea(area_num, first_name, last_name);
	}

	@Override
	public List<Land_Portion> GetAllLPWithAnimalsId() {
		return filter_facService.GetAllLPWithAnimalsId();
	}
	
	@Override
	public List<String> GetOnlyAnimalsIds() {
		return filter_facService.GetOnlyAnimalsIds();
	}

	@Override
	public void SetAnimalsLandPortion(String lp_id, String an_id,
			char clean_lp_flag) {
		filter_facService.SetAnimalsLandPortion
					(lp_id, an_id, clean_lp_flag);
	}



}
