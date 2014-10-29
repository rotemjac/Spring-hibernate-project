package my.pack.serviceTier.services.workers;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Animal;

public interface VetService extends WorkersService {

	public List<Animal> ReadByVet(String first_name, String last_name); 

}
