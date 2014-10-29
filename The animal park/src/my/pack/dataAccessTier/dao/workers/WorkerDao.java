package my.pack.dataAccessTier.dao.workers;

import java.util.List;

import my.pack.dataAccessTier.domain.superclasses.Worker;

public interface WorkerDao {
	
	public void create(Worker worker);
	public Worker read(Worker worker);
	public void delete (Worker worker);


}
