package my.pack.serviceTier.services.topManagers;

import java.util.List;

import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.topmanagers.CEO;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;

public interface CEOService extends ManagerGeneralService {
	
	public void CreateCEO(CEO ceo,List<Manager> mng_list);
	public void AddSubManager (CEO ceo,Manager mng);
	public List<Worker> GetAllWorkers();
	public List<Manager> GetAllManagers();
	public List<String> GetAllPositions();
	public void CreateFacilities(Facilities fac);
	public void SetFacilities(Facilities fac);
	public Facilities GetFacilities();
	public void FireWorker (String id);


}
