package my.pack.serviceTier.services.subManagers;

import my.pack.serviceTier.services.topManagers.ManagerGeneralService;
import my.pack.serviceTier.services.topManagers.ManagersExtraFeaturesService;

public interface TL_casheer_Service 
extends ManagerGeneralService,ManagersExtraFeaturesService {

	public int Get_Number_Of_Tickets_Sold();
}
