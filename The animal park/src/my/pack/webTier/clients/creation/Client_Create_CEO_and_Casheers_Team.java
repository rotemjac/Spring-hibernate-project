package my.pack.webTier.clients.creation;

import java.util.ArrayList;
import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.subfacilities.Economic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;
import my.pack.dataAccessTier.domain.submanagers.TL_casheer;
import my.pack.dataAccessTier.domain.topmanagers.CEO;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;
import my.pack.dataAccessTier.domain.workers.Casheer;
import my.pack.serviceTier.services.topManagers.CEOService;
import my.pack.serviceTier.services.topManagers.ManagerGeneralService;
import my.pack.serviceTier.services.workers.WorkersService;

import org.springframework.context.ApplicationContext;

public class Client_Create_CEO_and_Casheers_Team {
	

	public Client_Create_CEO_and_Casheers_Team(
									ApplicationContext container,
									WorkersService workersService,
									ManagerGeneralService managerService,
									CEOService ceoService) {
	
	CEO ceo;
	Facilities fac;
	TL_casheer tl_csr;
	Casheer csr1;
	Casheer csr2;
	Casheer csr3;
	
	/**
	 * Create domain objects
	 */
	
	//Create park facilities 
	//Economic_facilities eco_fac=new Economic_facilities(60);
	//Logistic_facilities log_fac=new Logistic_facilities();
	//fac=new Facilities(eco_fac, log_fac);
	
	
	//Create CEO and persist it in the DB
	Address ceo_A_address=new Address("Hertzelia","Vaitzman",33);
	Address ceo_B_address=new Address("Hertzelia","Vaitzman",35);
	List<Address> ceo_addresses=new ArrayList<Address>();
	ceo_addresses.add(ceo_A_address);
	ceo_addresses.add(ceo_B_address);

	ceo=new CEO("Meir", "Katz", 62, 'M',
			021234432, ceo_addresses, 33000, 0547523532,321);
	
	ceoService.CreateWorker(ceo);
	
	//Persist park facilities in the DB as a separate table		
	//ceoService.CreateFacilities(fac);
	//Add park facilities to CEO
	ceoService.SetFacilities(ceoService.GetFacilities());
	
	//Create casheers and casheer team leader
	Address csr1_address=new Address("Givataim","Remez",5);
	csr1=new Casheer("Yeal","Cohen",20,'F',023534566,csr1_address,4500,054342456);
	
	Address csr2_address=new Address("Rishon-lezion","Arlozorov",17);
	csr2=new Casheer("Adi","Levy",21,'F',02351165,csr2_address,4540,054712334);
	
	Address csr3_address=new Address("Rehovot","Hertzel",29);
	csr3=new Casheer("Liron","Yaakov",20,'F',0234365,csr3_address,4530,052514234);
	
	Address tl_csr_address=new Address("Tel-aviv","Iben-gvirol",37);
	tl_csr=new TL_casheer("Noa", "Abargil", 32, 'F', 03214255, tl_csr_address, 8500, 054342451, 102);
	

	
	workersService.CreateWorker(csr1);
	workersService.CreateWorker(csr2);
	workersService.CreateWorker(csr3);

	workersService.CreateWorker(tl_csr);
	//tl_csrService.CreateTL_Casheer(tl_csr);
	
	managerService.AddWorkerToStaff("TL_casheer", csr1);
	managerService.AddWorkerToStaff("TL_casheer", csr2);
	managerService.AddWorkerToStaff("TL_casheer", csr3);
	
	//Add casheers team leader as worker of ceo
	ceoService.AddSubManager(ceo,tl_csr);
	

	}

}
