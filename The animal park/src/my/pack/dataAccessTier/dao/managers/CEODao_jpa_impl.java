package my.pack.dataAccessTier.dao.managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.topmanagers.CEO;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;

@Repository(value="CEODao_jpa_impl")
public class CEODao_jpa_impl extends  ManagerDao_jpa_impl
implements CEODao {
	
	private EntityManagerFactory emf;
	
	//@PersistenceContext(name="myDatabaseConfig")
	private EntityManager em;
	
	public CEODao_jpa_impl() {
		System.out.println("Hi! i'm in CEODao_jpa_impl constructor");
		emf=Persistence.createEntityManagerFactory("myDatabaseConfig");
	}
	

	@Override
	public void createCeo(CEO ceo, List<Manager> mng_list) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		for (Manager man:mng_list) {
			man.getWorkers_list().add(man);
			
			ceo.setNumber_of_workers( 
			ceo.getNumber_of_workers()    //Add to the current number of workers
			+1+man.getNumber_of_workers() //For the (new manager)+(his workers)	
							);
		}
		
		em.persist(ceo);
		
		tx.commit();
		em.close();	
	}
	
	@Override
	public void addSubManager(Manager mng) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query q=em.createQuery("From CEO");
		List<CEO> results=q.getResultList();
		CEO ceo=results.get(0);
		
		q=em.createQuery("From Manager as mng where mng.id = :ID");
		q.setParameter("ID", mng.getId());
		List<Manager> results2=q.getResultList();
		mng=results2.get(0);
		
		ceo.getWorkers_list().add(mng);
		
	
		ceo.setNumber_of_workers( 
		ceo.getNumber_of_workers() //Add to the current number of workers
		+1+mng.getNumber_of_workers()//For the (new manager)+(his workers)	
				);
		
		tx.commit();
		em.close();			
	}
		
	@Override
	public List<Worker> getAllWorkers() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Query q=em.createQuery("From Worker");
		List<Worker> results=q.getResultList();
		tx.commit();
		em.close();		
		return results;
	}
	
	@Override
	public List<Manager> getAllManagers() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Query q=em.createQuery("From Manager");
		List<Manager> results=q.getResultList();
		tx.commit();
		em.close();	
		return results;
	}
	
	@Override
	public List<String> getAllPositions() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query q=em.createNativeQuery
				("select worker.Worker_Type From Worker as worker");
		List<Object> workers_list=q.getResultList();
		
		List<String> postionsList=new ArrayList<>();
		
		for(Object worker:workers_list) {
			
			if (!postionsList.contains( worker.toString()  )  ) {
				postionsList.add(worker.toString());
			}
		}
	
		tx.commit();
		em.close();	
		
		return postionsList;	
	}
	
	@Override
	public void setFacilities(Facilities fac) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query q=em.createQuery("From CEO ");
		List<CEO> results=q.getResultList();
		CEO ceo=results.get(0);
		
		ceo.setFacilities(fac);
		
		tx.commit();
		em.close();			
	}
	
	@Override
	public Facilities getFacilities() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query q=em.createQuery("From Facilities");
		List<Facilities> results=q.getResultList();
		Facilities fac=results.get(0);
		
		tx.commit();
		em.close();	
		
		return fac;
	}

	@Override
	public void delete_worker(String id) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query q=em.createQuery
				("From Worker as worker where worker.id= :ID")
				.setParameter("ID", Long.parseLong(id));
		
		Worker worker_to_fire=(Worker) q.getResultList().get(0);
		
		//Before deleting the worker you need to remove it from the 
		//in the  corresponding manager's workers list
		q=em.createQuery
			("from Manager as man where :worker member of man.workers_list")
			.setParameter("worker", worker_to_fire);
		
		Manager manager_of_the_fired_worker=(Manager) q.getResultList().get(0);
		
		manager_of_the_fired_worker.getWorkers_list().remove(worker_to_fire);
		
		em.remove(worker_to_fire);
		
		tx.commit();
		em.close();		
	}

}
