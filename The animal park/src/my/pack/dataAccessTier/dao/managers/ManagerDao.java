package my.pack.dataAccessTier.dao.managers;

import java.util.List;

import my.pack.dataAccessTier.dao.workers.WorkerDao;
import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;

public interface ManagerDao extends WorkerDao {
	
	public void create_manager(Manager man,List<Worker> workers_list);
	public void addWorker(String man_position,Worker worker);
	public List<Worker> getYourStaff(String position);
	public List<Object[]> getYourStaff_partly(String position);
}
