package my.pack.serviceTier.services.workers;

import my.pack.dataAccessTier.domain.superclasses.Worker;

public interface WorkersService {

	public void CreateWorker(Worker worker);
	public Worker GetWorker(Worker worker);
	public void DeleteWorker(Worker worker);
}
