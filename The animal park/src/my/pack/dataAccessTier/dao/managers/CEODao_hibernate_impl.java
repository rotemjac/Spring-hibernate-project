package my.pack.dataAccessTier.dao.managers;

import java.util.ArrayList;
import java.util.List;

import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.topmanagers.CEO;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;
import my.pack.utils.Config_File;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository(value="CEODao_hibernate_impl")
public class CEODao_hibernate_impl extends  ManagerDao_hibernate_impl
								   implements CEODao {

	
	public CEODao_hibernate_impl() {
		System.out.println("Hi! i'm in CEODao_hibernate_impl constructor");
	}


	@Override
	public void createCeo(CEO ceo, List<Manager> mng_list) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		for (Manager man:mng_list) {
			man.getWorkers_list().add(man);
			
			ceo.setNumber_of_workers( 
			ceo.getNumber_of_workers()    //Add to the current number of workers
			+1+man.getNumber_of_workers() //For the (new manager)+(his workers)	
							);
		}
		
		session.persist(ceo);
		
		session.getTransaction().commit();
		session.close();	
	}
	

	@Override
	public void addSubManager(Manager mng) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery("From CEO");
		List<CEO> results=q.list();
		CEO ceo=results.get(0);
		
		q=session.createQuery("From Manager as mng where mng.id = :ID");
		q.setParameter("ID", mng.getId());
		List<Manager> results2=q.list();
		mng=results2.get(0);
		
		ceo.getWorkers_list().add(mng);
		
	
		ceo.setNumber_of_workers( 
		ceo.getNumber_of_workers() //Add to the current number of workers
		+1+mng.getNumber_of_workers()//For the (new manager)+(his workers)	
				);
		
		session.getTransaction().commit();
		session.close();			
	}
	
	
	@Override
	public List<Worker> getAllWorkers() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Query q=session.createQuery("From Worker");
		List<Worker> results=q.list();
		session.getTransaction().commit();
		session.close();	
		return results;
	}
	
	@Override
	public List<Manager> getAllManagers() {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Query q=session.createQuery("From Manager");
		List<Manager> results=q.list();
		session.getTransaction().commit();
		session.close();
		return results;
	}
	
	@Override
	public List<String> getAllPositions() {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createSQLQuery
				("select worker.Worker_Type From Worker as worker");
		List<Object> workers_list=q.list();
		
		List<String> postionsList=new ArrayList<>();
		
		for(Object worker:workers_list) {
			
			if (!postionsList.contains( worker.toString()  )  ) {
				postionsList.add(worker.toString());
			}
		}
	
		session.getTransaction().commit();
		session.close();
		
		return postionsList;	
	}
	
	@Override
	public void setFacilities(Facilities fac) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery("From CEO ");
		List<CEO> results=q.list();
		CEO ceo=results.get(0);
		
		ceo.setFacilities(fac);
		
		//session.update(ceo); --I think no need to update because its persist
		session.getTransaction().commit();
		session.close();		
	}
	
	@Override
	public Facilities getFacilities() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery("From Facilities");
		List<Facilities> results=q.list();
		Facilities fac=results.get(0);
		
		session.getTransaction().commit();
		session.close();	
		
		return fac;
	}



	@Override
	public void delete_worker(String id) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery
				("From Worker as worker where worker.id= :ID")
				.setParameter("ID", Long.parseLong(id));
		
		Worker worker_to_fire=(Worker) q.list().get(0);
		
		//Before deleting the worker you need to remove it from the 
		//in the  corresponding manager's workers list
		q=session.createQuery
			("from Manager as man where :worker member of man.workers_list")
			.setParameter("worker", worker_to_fire);
		
		Manager manager_of_the_fired_worker=(Manager) q.list().get(0);
		
		manager_of_the_fired_worker.getWorkers_list().remove(worker_to_fire);
		
		session.delete(worker_to_fire);
		
		session.getTransaction().commit();
		session.close();		
	}




	
}
