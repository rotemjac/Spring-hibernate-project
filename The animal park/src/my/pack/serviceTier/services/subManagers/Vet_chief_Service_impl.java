package my.pack.serviceTier.services.subManagers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.serviceTier.services.facilities.FilterService;
import my.pack.serviceTier.services.topManagers.ManagerService_impl;

@Service("Vet_chief_Service")
public class Vet_chief_Service_impl extends ManagerService_impl
implements Vet_chief_Service {

	@Autowired
	@Qualifier("FilterTo_FacilitiesService")
	private FilterService filter_facService;
	
	@Override
	@Cacheable(value="all_animals_cache")
	public List<Animal> Get_All_Animals() {
		return filter_facService.Get_All_Animals();
	}

	@Override
	@Cacheable(value="animals_in_area_cache")
	public List<Animal> Get_All_Animals_In_Area(String area_num) {
		return filter_facService.Get_All_Animals_In_Area(area_num);
	}

	@Override
	@Cacheable(value="animals_need_check_cache")
	public List<Animal> Get_All_Animals_That_Need_A_Check() {
		return filter_facService.Get_All_Animals_That_Need_A_Check();
	}

	@Override
	public void Set_Animal_Food(String category, String food_type) {
		filter_facService.Set_Animal_Food(category, food_type);
	}

	@Override
	public void SetVetOfArea(int area_num, String first_name, String last_name) {
		filter_facService.SetVetOfArea(area_num, first_name, last_name);
	}

	@Override
	public List<Object[]> Get_Vets_Areas_Mapping() {
		return filter_facService.Get_Vets_Areas_Mapping();
	}

	@Override
	public List<String> ReadOnlyGroupsByCategory(String category) {
		 return filter_facService.ReadOnlyGroupsByCategory(category);
	}

}
