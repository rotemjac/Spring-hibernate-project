package my.pack.serviceTier.services.workers;

import my.pack.serviceTier.services.facilities.FilterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="CaregiverService")
public class CaregiverService_impl extends WorkersService_impl implements
		CaregiverService {
	
	@Autowired
	@Qualifier("FilterTo_FacilitiesService")
	private FilterService filter_facService;

	@Override
	public void SetProblemToLandPortion(String problem, String area_num,
			String lp_num) {
		
		filter_facService.SetProblemToLandPortion(problem, area_num, lp_num);
	}

	@Override
	public void MarkAnimalThatNeedVetCheck(String id, char need_check) {
		filter_facService.MarkAnimalThatNeedVetCheck(id, need_check);		
	}
	
	

}
