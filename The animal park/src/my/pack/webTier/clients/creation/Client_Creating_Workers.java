package my.pack.webTier.clients.creation;

import java.util.ArrayList;
import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.subfacilities.Economic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;
import my.pack.dataAccessTier.domain.submanagers.Security_chief;
import my.pack.dataAccessTier.domain.submanagers.TL_caregiver;
import my.pack.dataAccessTier.domain.submanagers.TL_casheer;
import my.pack.dataAccessTier.domain.submanagers.TL_cleaner;
import my.pack.dataAccessTier.domain.submanagers.TL_janitor;
import my.pack.dataAccessTier.domain.submanagers.Vet_chief;
import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.topmanagers.CEO;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;
import my.pack.dataAccessTier.domain.workers.Caregiver;
import my.pack.dataAccessTier.domain.workers.Casheer;
import my.pack.dataAccessTier.domain.workers.Cleaner;
import my.pack.dataAccessTier.domain.workers.Guard;
import my.pack.dataAccessTier.domain.workers.Janitor;
import my.pack.dataAccessTier.domain.workers.Vet;
import my.pack.serviceTier.services.topManagers.CEOService;
import my.pack.serviceTier.services.topManagers.ManagerGeneralService;
import my.pack.serviceTier.services.workers.WorkersService;


public class Client_Creating_Workers {
	
	public Client_Creating_Workers(WorkersService workersService,
									ManagerGeneralService managerService,
									CEOService ceoService) {
		
		/**
		 * Creating Workers and it's corresponding managers
		 */
		
		////////////////casheers//////////////////////////		
		//Create casheers 
		Address csr1_address=new Address("Givataim","Remez",5);
		Casheer csr1=new Casheer("Yeal","Cohen",20,'F',023534566,csr1_address,4500,054342456);
		
		
		Address csr2_address=new Address("Rishon-lezion","Arlozorov",17);
		Casheer csr2=new Casheer("Adi","Levy",21,'F',02351165,csr2_address,4540,054712334);


		Address csr3_address=new Address("Rehovot","Hertzel",29);
		Casheer csr3=new Casheer("Liron","Yaakov",20,'F',0234365,csr3_address,4530,052514234);
		
		//Add Casheer team leader and add casheers to it
		Address tl_csr_address=new Address("Tel-aviv","Iben-gvirol",37);
		TL_casheer tl_csr=new TL_casheer("Noha", "Abargil", 32, 'F', 03214255, tl_csr_address, 8500, 054342451, 102);
		
		//Create Casheers list
		List<Worker> casheer_list=new ArrayList<Worker>();
		casheer_list.add(csr1);
		casheer_list.add(csr2);
		casheer_list.add(csr3);
		
		//Here there's need to be cacscadeType=persist
		managerService.CreateManager(tl_csr, casheer_list);
		 	
		/*		 
  	  	 * Because of the line above
		 * and the fact that i used cacscadeType=persist 
		 * The next 6 lines were saved!!!
		workersService.CreateWorker(csr1);
		workersService.CreateWorker(csr2);
		workersService.CreateWorker(csr3);
		
		managerService.AddWorkerToStaff("TL_casheer", csr1);
		managerService.AddWorkerToStaff("TL_casheer", csr2);
		managerService.AddWorkerToStaff("TL_casheer", csr3);
		*/
		
		////////////////cleaners//////////////////////////
		//Create Cleaners 
		Address cleaner1_address=new Address("Holon","Volfson",31);
		Cleaner cleaner1=new Cleaner("Shoshi","volfson",43,'F',232244,cleaner1_address,4520,0524546632);
		
		Address cleaner2_address=new Address("Holon","Volfson",32);
		Cleaner cleaner2=new Cleaner("Alina","Tzuker",44,'F',232245,cleaner2_address,4530,0524546633);

		Address cleaner3_address=new Address("Holon","Volfson",33);
		Cleaner cleaner3=new Cleaner("Hava","bar-on",45,'F',232246,cleaner3_address,4540,0524546634);
		
		
		//Add Cleaners team leader and add cleaners to it
		Address tl_cleaner_addr=new Address("Yerushalaim","Tzivoni",19); 
		TL_cleaner tl_cleaner=new TL_cleaner("Sara", "sendik", 54, 'F', 022154311, tl_cleaner_addr, 11500, 052335714, 103); 
		
		//Create cleaners list
		List<Worker> cleaners_list=new ArrayList<Worker>();
		cleaners_list.add(cleaner1);
		cleaners_list.add(cleaner2);
		cleaners_list.add(cleaner3);
		
		//Here there's need to be cacscadeType=persist
		managerService.CreateManager(tl_cleaner, cleaners_list);
		
		/////////////////janitors////////////////////////		
		//Create Janitors 
		Address janitor1_address=new Address("Ramat-Gan","Negev",71);
		Janitor janitor1=new Janitor("Yuval","rabiner",37,'M',415272,janitor1_address,7000,05310153711);
		
		Address janitor2_address=new Address("Ramat-Gan","Negev",82);
		Janitor janitor2=new Janitor("Tal","bril",36,'M',415273,janitor2_address,6550,05310153712);
		
		Address janitor3_address=new Address("Ramat-Gan","Negev",93);
		Janitor janitor3=new Janitor("Omer","tzur",35,'M',415274,janitor3_address,6530,05310153713);
		
		//Add Janitor team leader and add janitors to it
		Address tl_janitor_addr=new Address("Raanana","Shiryon",44); 
		TL_janitor tl_janitor=new TL_janitor("Haim", "alfasi", 60, 'M', 022153422, tl_janitor_addr, 13500, 052335715, 104); 
		
		//Create janitors list
		List<Worker> janitors_list=new ArrayList<Worker>();
		janitors_list.add(janitor1);
		janitors_list.add(janitor2);
		janitors_list.add(janitor3);
		
		//Here there's need to be cacscadeType=persist
		managerService.CreateManager(tl_janitor, janitors_list);
		
		/////////////////guards////////////////////////
		//Create Guards 
		Address guard1_address=new Address("Natania","Harduf",11);
		Guard guard1=new Guard("Sasha","zlotik",33,'M',525235,guard1_address,6500,05035353154, 11112);
		
		Address guard2_address=new Address("Eilat","Galit",65);
		Guard guard2=new Guard("Aviram","kelerman",25,'M',235421,guard2_address,6550,05277353212, 11113);
		
		Address guard3_address=new Address("Haifa","Yakinton",16);
		Guard guard3=new Guard("Pini","balili",29,'M',325122,guard3_address,6530,05437734404, 11114);
		
		//Add Security chief and add guards to it
		Address sec_chief_addr=new Address("Yafo","Rambam",59); 
		Security_chief sec_cheif=new Security_chief("Shlomo", "pais", 52, 'M', 013154632, sec_chief_addr, 18500, 052335710, 105); 
		
		//Create janitors list
		List<Worker> guards_list=new ArrayList<Worker>();
		guards_list.add(guard1);
		guards_list.add(guard2);
		guards_list.add(guard3);
		
		//Here there's need to be cacscadeType=persist
		managerService.CreateManager(sec_cheif, guards_list);
		
		/////////////////Caregivers////////////////////////
		//Create Caregivers 
		Address caregiver1_address=new Address("Petah-tikva","Givati",22);
		Caregiver caregiver1=new Caregiver("Amir","breski",27,'M',415272,caregiver1_address,8000,05510153711);
		
		Address caregiver2_address=new Address("Petah-tikva","Givati",23);
		Caregiver caregiver2=new Caregiver("Amit","ben-atia",26,'M',415273,caregiver2_address,9550,05510153712);
		
		Address caregiver3_address=new Address("Petah-tikva","Givati",24);
		Caregiver caregiver3=new Caregiver("Tal","namihas",25,'M',415274,caregiver3_address,9530,05510153713);
		
		Address caregiver4_address=new Address("Petah-tikva","Givati",25);
		Caregiver caregiver4=new Caregiver("Yotam","brener",29,'M',415275,caregiver4_address,8050,05510153714);
		
		Address caregiver5_address=new Address("Petah-tikva","Givati",26);
		Caregiver caregiver5=new Caregiver("Haim","asif",30,'M',415276,caregiver5_address,9650,05510153715);
		
		Address caregiver6_address=new Address("Petah-tikva","Givati",27);
		Caregiver caregiver6=new Caregiver("Moshe","shvili",31,'M',415277,caregiver6_address,9580,05510153716);
		
		//Add TL_Caregivers team leader and add caregivers to it
		Address tl_caregiver_addr=new Address("Tzfat","Meron",10); 
		TL_caregiver tl_caregiver=new TL_caregiver("Avihay", "barak", 42, 'M', 022155323, tl_caregiver_addr, 13500, 052335726, 106); 

		//Create caregivers list
		List<Worker> caregivers_list=new ArrayList<Worker>();
		caregivers_list.add(caregiver1);
		caregivers_list.add(caregiver2);
		caregivers_list.add(caregiver3);
		caregivers_list.add(caregiver4);
		caregivers_list.add(caregiver5);
		caregivers_list.add(caregiver6);
		
		//Here there's need to be cacscadeType=persist
		managerService.CreateManager(tl_caregiver, caregivers_list);
		
		/////////////////Vets////////////////////////
		//Create Vets
		Address Vet1_address=new Address("Gadera","Kakal",43);
		Vet vet1=new Vet("Shaul","simhoni",33,'M',415272,Vet1_address,12000,05110153722);
		
		Address Vet2_address=new Address("Gadera","Kakal",44);
		Vet vet2=new Vet("Asaf","vais",34,'M',415273,Vet2_address,13550,05110153723);
		
		Address Vet3_address=new Address("Gadera","Kakal",45);
		Vet vet3=new Vet("Ariel","sofer",29,'M',415274,Vet3_address,9530,05110153724);
		
		//Add Vet chief and add vets to it
		Address tl_vet_addr=new Address("Kiryat-ata","sasonski",45); 
		Vet_chief vet_chief=new Vet_chief("Ohad", "Rabinovich", 58, 'M', 034215521, tl_vet_addr, 25500, 052335725, 107); 
		
		//Create guards list
		List<Worker> vet_list=new ArrayList<Worker>();
		vet_list.add(vet1);
		vet_list.add(vet2);
		vet_list.add(vet3);

		
		//Here there's need to be cacscadeType=persist
		managerService.CreateManager(vet_chief, vet_list);


		/**
		 * Creating CEO
		 */
		
		//Create park facilities 
		//Economic_facilities eco_fac=new Economic_facilities(60);
		//Logistic_facilities log_fac=new Logistic_facilities();
		//Facilities fac=new Facilities(eco_fac, log_fac);
		
		
		//Create CEO and persist it in the DB
		Address ceo_A_address=new Address("Hertzelia","Vaitzman",33);
		Address ceo_B_address=new Address("Hertzelia","Vaitzman",35);
		List<Address> ceo_addresses=new ArrayList<Address>();
		ceo_addresses.add(ceo_A_address);
		ceo_addresses.add(ceo_B_address);

		CEO ceo=new CEO("Meir", "Katz", 62, 'M',
				021234432, ceo_addresses, 33000, 0547523532,321);

		List<Manager> managers_list=new ArrayList<Manager>();
		
		managers_list.add(tl_csr);
		managers_list.add(tl_cleaner);
		managers_list.add(tl_janitor);
		managers_list.add(sec_cheif);
		managers_list.add(tl_caregiver);
		managers_list.add(vet_chief);
		
		ceoService.CreateCEO(ceo, managers_list);
		
		//Add park facilities to CEO
		ceoService.SetFacilities(ceoService.GetFacilities());

	}

}
