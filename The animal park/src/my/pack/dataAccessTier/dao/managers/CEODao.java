package my.pack.dataAccessTier.dao.managers;

import java.util.List;

import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.topmanagers.CEO;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;

public interface CEODao extends ManagerDao {
	

	public void createCeo(CEO ceo,List<Manager> mng_list);
	public void addSubManager(Manager mng);
	public List<Worker> getAllWorkers();
	public List<Manager> getAllManagers();
	public List<String> getAllPositions();
	public void setFacilities(Facilities fac);
	public Facilities getFacilities();
	public void delete_worker(String id);

}
