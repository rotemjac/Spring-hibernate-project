package my.pack.serviceTier.services.workers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.serviceTier.services.facilities.FilterService;

@Service(value="VetService")
public class VetService_impl extends WorkersService_impl implements VetService {

	
	@Autowired
	@Qualifier("FilterTo_FacilitiesService")
	private FilterService filter_facService;
	
	
	@Override
	public List<Animal> ReadByVet(String first_name, String last_name) {
		return filter_facService.ReadByVet(first_name, last_name);
	}

}
