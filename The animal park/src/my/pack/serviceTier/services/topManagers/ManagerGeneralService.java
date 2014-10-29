package my.pack.serviceTier.services.topManagers;

import java.util.List;

import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.topmanagers.CEO;
import my.pack.serviceTier.services.workers.WorkersService;


public interface ManagerGeneralService extends WorkersService {

	public void CreateManager(Manager man,List<Worker> worker_list);
	public void AddWorkerToStaff (String man_position,Worker worker);
	public List<Worker> GetYourStaff(String position);
	public List<Object[]> GetYourStaffPartly(String position);
	
}
