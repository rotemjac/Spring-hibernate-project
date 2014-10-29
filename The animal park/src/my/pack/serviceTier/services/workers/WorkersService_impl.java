package my.pack.serviceTier.services.workers;

import my.pack.dataAccessTier.dao.workers.WorkerDao;
import my.pack.dataAccessTier.domain.superclasses.Worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service(value="WorkersService")
public class WorkersService_impl implements WorkersService {
	
	@Autowired
	//@Qualifier("${DataAccess.WorkerDao}")
	@Qualifier("WorkerDao")
	private WorkerDao workerDao;
		
	@Override
	public void CreateWorker(Worker worker) {
		workerDao.create(worker);
	}

	@Override
	@Cacheable(value="workers_cache", key="#worker.id")
	public Worker GetWorker(Worker worker) {
		return workerDao.read(worker);
	}

	@Override
	public void DeleteWorker(Worker worker) {
		workerDao.delete(worker);
		
	}

}
