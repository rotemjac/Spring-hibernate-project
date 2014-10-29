package my.pack.all_methods_performance;


import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;

@Entity(name="Performance_data")
public class Performance_data {

	@Id
	@GeneratedValue
	private int Performance_data_db_id;

	//private static final int  NUM_OF_METHODS=37;

	//private static final String[] names_of_all_methods= 

	private  final int  NUM_OF_METHODS=37;

	private  final String[] names_of_all_methods= 		
		{

			/**
			 * Area part
			 */
			"AreaService.CreateArea","AreaService.GetAreaByNumber","AreaService.GetAreaByNumber_With_Animals",
			"AreaService.GetAreaByNumber_With_LandPortions","AreaService.GetAreaByNumber_With_Bathrooms",
			"AreaService.GetAllAreas","AreaService.GetAllAreasWithVets","AreaService.UpdateArea",
			"AreaService.DeleteArea","AreaService.PopulateAnimalByCategory",
			"AreaService.PutAnimals_In_LandPortion","AreaService.SetVetOfArea","AreaService.SetCaregiverOfArea",


			/**
			 * Animals part
			 */
			"AreaService.CreateAnimal","AreaService.ReadAnimalById","AreaService.GetAllAnimals",
			"AreaService.GetOnlyAnimalsIds","AreaService.GetUncheckedAnimals","AreaService.DeleteAnimal",
			"AreaService.ReadAnimalByCategory","AreaService.ReadOnlyGroupsByCategory","AreaService.ReadByVet",
			"AreaService.UpdateAnimalAreaNum","AreaService.UpdateAnimalFood","AreaService.MarkAnimalThatNeedVetCheck",		

			/**
			 * Land_Portion part
			 */	
			"AreaService.Create_Land_Portion","AreaService.Read_Land_Portion","AreaService.Read_Land_Portion_By_Animal",
			"AreaService.Update_Land_Portion","AreaService.Delete_Land_Portion","AreaService.GetAll_Land_Portion",
			"AreaService.getAllLPWithProblemsAndAreas","AreaService.getAllLPWithAnimalsId","PopulateLandPortions",
			"AreaService.SetProblemToLandPortion","AreaService.RemoveProblemsForLandPortion","AreaService.SetAnimalsLandPortion",
		};

	public Performance_data(){}
	
/*	@ElementCollection
	@CollectionTable(name="Performance_of_methods")
	private Set<Method_performance> table_of_methods_performance;

	//Constructor

	public Performance_data() {
		table_of_methods_performance=new TreeSet<Method_performance>();

		for(int i=0;i<NUM_OF_METHODS;i++) {

			if (i==0) {
				Method_performance mp=
						new Method_performance(names_of_all_methods[0],0,0,0,0,0);

				table_of_methods_performance.add(mp);
			}
			else {
				Method_performance mp=
						new Method_performance(names_of_all_methods[i],-i,-i,-i,-i,-i);

				table_of_methods_performance.add(mp);
			}


		}
	}*/



}
