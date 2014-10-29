package my.pack.webTier.clients.creation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import my.pack.dataAccessTier.domain.topmanagers.Facilities;
import my.pack.serviceTier.services.topManagers.CEOService;
import my.pack.serviceTier.services.workers.CasheerService;

//@Transactional-not working good if its here
public class All_casheers_add_ticket_at_same_time {
	
	CasheerService csrService;
	Facilities fac;
	int num_of_threads;
	int i;
	
	public  All_casheers_add_ticket_at_same_time() {}
	
	/**
	 * TODO: Understand why haded we need to put final here
	 */
	public  All_casheers_add_ticket_at_same_time 
					(CasheerService csrService,
					CEOService ceoService,
					int num_of_threads
					 ) {
		
		this.csrService=csrService;
		this.fac=ceoService.GetFacilities();
		this.num_of_threads=num_of_threads;
	}
	
	
	public void Casheers_order_tickets() {
		ExecutorService executor=Executors.newFixedThreadPool(3);

		for (i=0; i<num_of_threads;i++) {
			
			executor.submit(new Runnable() {
				
/*				@Transactional(
						isolation=Isolation.SERIALIZABLE,
						propagation=Propagation.REQUIRES_NEW
						)*/
				public void run() {
					csrService.Add_Or_SubTicket('A');
				}
			});
		}
		
		executor.shutdown();
		
		System.out.println("All tasks submitted");
		try {executor.awaitTermination(1, TimeUnit.DAYS);} 
		catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("All tasks completed");
		
		
	}

	
	
}
