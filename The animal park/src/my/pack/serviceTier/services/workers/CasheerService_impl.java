package my.pack.serviceTier.services.workers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import my.pack.dataAccessTier.dao.facilities.FacilitiesDao;
import my.pack.dataAccessTier.dao.workers.WorkerDao;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;
import my.pack.serviceTier.services.facilities.FilterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="CasheerService")
public class CasheerService_impl extends WorkersService_impl
								 implements CasheerService {

	private Lock lock=new ReentrantLock();

	@Autowired
	private FilterService filterServ;
	
	@Override
/*	@Transactional(
	isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRES_NEW
			)*/
	public void Add_Or_SubTicket(char oper) {
		
		lock.lock();
		try {
			
			/**
			 * TODO: Think about if it's a good idea to use facdao method
			 * and not implement it inside the casheer's dao 
			 */
			filterServ.Add_Or_SubTicket(oper);
			
		}
		finally { lock.unlock();}
		
	}





}
