package my.pack.webTier.clients.creation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import my.pack.all_methods_performance.Performance_data;
import my.pack.all_methods_performance.Service_performance_data;
import my.pack.serviceTier.services.facilities.FacilitiesService;
import my.pack.serviceTier.services.topManagers.CEOService;
import my.pack.serviceTier.services.topManagers.ManagerGeneralService;
import my.pack.serviceTier.services.workers.CasheerService;
import my.pack.serviceTier.services.workers.WorkersService;
import my.pack.utils.Config_File;


public class Creating_Park  {

	public Creating_Park() {}
	

	private Client_Creating_Animals client_creating_animals;
	
	private CEOService ceoService;
	private ManagerGeneralService managerService;
	private WorkersService workerService;
	private FacilitiesService facService;
	private Service_performance_data pdService;

		public Creating_Park(
					Client_Creating_Animals client_creating_animals,
					CEOService ceoService,
					ManagerGeneralService managerService,
					WorkersService workerService,
					FacilitiesService facService,
					Service_performance_data pdService) {
			
			this.client_creating_animals=client_creating_animals;
			this.ceoService=ceoService;
			this.managerService=managerService;
			this.workerService=workerService;
			this.facService=facService;
			this.pdService=pdService;
		}
		
		public void Create() {	
			
			/*		Client_Create_CEO_and_Casheers_Team case1=
				     new Client_Create_CEO_and_Casheers_Team(
				    		 Config_File.getContainer(),workerService,managerService,ceoService);
	
		All_casheers_add_ticket_at_same_time case2= 
					new All_casheers_add_ticket_at_same_time
					(csrService,ceoService,10);
		
		case2.Casheers_order_tickets();*/
			
			
			Client_Creating_Logistic_Facilities case3=new Client_Creating_Logistic_Facilities(facService);
			
			//The creation here is done via method because i wanted to test AOP
			Client_Creating_Workers case4=new Client_Creating_Workers(workerService, managerService, ceoService);
			client_creating_animals.create_the_animals();
			
			Client_Features_Animals case6=new Client_Features_Animals(facService);
			
			Performance_data pd=new Performance_data();
			pdService.Create(pd);
		}
		


}
