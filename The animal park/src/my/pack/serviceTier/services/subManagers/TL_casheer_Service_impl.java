package my.pack.serviceTier.services.subManagers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import my.pack.serviceTier.services.facilities.FilterService;
import my.pack.serviceTier.services.topManagers.ManagerService_impl;

@Service(value="TL_casheer_Service")
public class TL_casheer_Service_impl extends ManagerService_impl implements
		TL_casheer_Service {
	
	@Autowired
	@Qualifier("FilterTo_FacilitiesService")
	private FilterService filter_facService;

	@Override
	public int Get_Number_Of_Tickets_Sold() {
		return filter_facService.Get_Number_Of_Tickets_Sold();
	}

}
