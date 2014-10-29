package my.pack.webTier.clients.creation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Economic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;
import my.pack.serviceTier.services.facilities.FacilitiesService;

public class Client_Creating_Logistic_Facilities {


	public Client_Creating_Logistic_Facilities(FacilitiesService facService) {

		
	
		/**
		 * Creating 6 areas
		 */
		for (int i=1;i<7;i++) {
			String num=String.valueOf(i);
			facService.getAreaService().
		    CreateArea(new Area(num,"In",null,null,null));

		}
		
		/**
		 * Creating Land_Portions and adding to areas
		 */
		List<String> lp_list1=new LinkedList<String>();
		for (int i=1;i<6;i++) {
			facService.getAreaService().Create_Land_Portion(new Land_Portion("lp"+ i,null,null,null));
			lp_list1.add("lp"+i);
		}
		facService.getAreaService().PopulateLandPortions("1", lp_list1);
		
		List<String> lp_list2=new LinkedList<String>();
		for (int i=1;i<6;i++) {
			facService.getAreaService().Create_Land_Portion(new Land_Portion("lp"+ i,null,null,null));
			lp_list2.add("lp"+i);
		}
		facService.getAreaService().PopulateLandPortions("2", lp_list2);
		
		List<String> lp_list3=new LinkedList<String>();
		for (int i=1;i<6;i++) {
			facService.getAreaService().Create_Land_Portion(new Land_Portion("lp"+ i,null,null,null));
			lp_list3.add("lp"+i);
		}
		facService.getAreaService().PopulateLandPortions("3", lp_list3);
		
		List<String> lp_list4=new LinkedList<String>();
		for (int i=1;i<6;i++) {
			facService.getAreaService().Create_Land_Portion(new Land_Portion("lp"+ i,null,null,null));
			lp_list4.add("lp"+i);
		}
		facService.getAreaService().PopulateLandPortions("4", lp_list4);

			
		/**
		 * Creating Bathroom and adding to areas
		 */		
		facService.Add_Bathroom_To_Area("1", 3);
		facService.Add_Bathroom_To_Area("6", 1);

		/**
		 * Creating Logistic_facilities and adding areas to it
		 */	
		Logistic_facilities log_fac=facService.Create_Log_Fac(9, 20, 23);
		log_fac.setAreas(facService.getAreaService().GetAllAreas());
		
		facService.getAreaService().SetProblemToLandPortion("Broken roof", "2", "3");
		facService.getAreaService().SetProblemToLandPortion("Broken gate", "4", "4");
		facService.getAreaService().SetProblemToLandPortion("Broken fence", "1", "2");
		/**
		 * Creating Economic Facilities 
		 */	
		
		Economic_facilities eco_fac=new Economic_facilities(60);
		
		/**
		 * Creating Facilities object and adding Logistic_facilities to it
		 */	
		Facilities tot_fac=new Facilities(log_fac,eco_fac);
		facService.CreateFacilities(tot_fac);
	}


}
