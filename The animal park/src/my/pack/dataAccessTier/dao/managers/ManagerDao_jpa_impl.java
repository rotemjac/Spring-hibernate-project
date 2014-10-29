package my.pack.dataAccessTier.dao.managers;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.sf.ehcache.Cache;

import org.hibernate.Session;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Repository;

import my.pack.dataAccessTier.dao.workers.WorkerDao_hibernate_impl;
import my.pack.dataAccessTier.dao.workers.WorkerDao_jpa_impl;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.submanagers.Security_chief;
import my.pack.dataAccessTier.domain.submanagers.TL_caregiver;
import my.pack.dataAccessTier.domain.submanagers.TL_casheer;
import my.pack.dataAccessTier.domain.submanagers.TL_cleaner;
import my.pack.dataAccessTier.domain.submanagers.TL_janitor;
import my.pack.dataAccessTier.domain.submanagers.Vet_chief;
import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.workers.Vet;
import my.pack.utils.Config_File;

@Repository(value="ManagerDao_jpa_impl")
public class ManagerDao_jpa_impl 
extends WorkerDao_jpa_impl
implements ManagerDao {
	
	private EntityManagerFactory emf;
	
	//@PersistenceContext(name="myDatabaseConfig")
	private EntityManager em;
	
	public ManagerDao_jpa_impl() {
		System.out.println("Hi! i'm in ManagerDao_jpa_impl constructor");
		emf=Persistence.createEntityManagerFactory("myDatabaseConfig");
	}
	
	
	@Override
	public void create_manager(Manager man, List<Worker> workers_list) {
		
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		for (Worker worker:workers_list) {
			man.getWorkers_list().add(worker);
		}
		man.setNumber_of_workers(man.getWorkers_list().size());

		em.persist(man);
		
		tx.commit();
		em.close();		
	}

	@Override
	public void addWorker(String man_position, Worker worker) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		//Just to check something in the polymorphism
		System.out.println(worker.getClass().toString());
		
		//Get the right manager
		Query q=em.createNativeQuery
				("Select * From Worker where Worker_Type = :pos",Manager.class)
				.setParameter("pos", man_position);

		Manager man=(Manager) q.getSingleResult();
		
		//If the worker wasn't added before
		if (worker.getWorker_db_id()==0) {
			em.persist(worker);
		}
		else {
			em.merge(worker);
		}

		//I have seperated one line into the next two because of debugging issues
		//Its not mendatory
		List<Worker> worker_list=man.getWorkers_list();
		worker_list.add(worker);
		
		man.setNumber_of_workers(worker_list.size());
		//man.setNumber_of_workers(man.getWorkers_list().size());

		tx.commit();
		em.close();				
	}


	@Override
	public List<Worker> getYourStaff(String position) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		CriteriaBuilder builder=emf.getCriteriaBuilder();
		//CriteriaQuery<?> criteria_query = null;
		//Root<?> root_of_criteria=null;
		TypedQuery<?> final_query=null;
		
		//////switch///////////
		switch (position) {
		case "TL_casheer": 
			CriteriaQuery<TL_casheer> criteria_query=builder.createQuery(TL_casheer.class);
			 Root<TL_casheer> root_of_criteria=criteria_query.from(TL_casheer.class);
			 criteria_query.select(root_of_criteria);
			 final_query=em.createQuery(criteria_query);
			break;
		case "TL_cleaner":
			CriteriaQuery<TL_cleaner> criteria_query2=builder.createQuery(TL_cleaner.class);
			Root<TL_cleaner> root_of_criteria2=criteria_query2.from(TL_cleaner.class);
			criteria_query2.select(root_of_criteria2);
			final_query=em.createQuery(criteria_query2);
			break;
		case "TL_janitor":
			CriteriaQuery<TL_janitor> criteria_query3=builder.createQuery(TL_janitor.class);
			Root<TL_janitor> root_of_criteria3=criteria_query3.from(TL_janitor.class);
			criteria_query3.select(root_of_criteria3);
			final_query=em.createQuery(criteria_query3);
			break;
		case "TL_caregiver":
			CriteriaQuery<TL_caregiver> criteria_query4=builder.createQuery(TL_caregiver.class);
			Root<TL_caregiver> root_of_criteria4=criteria_query4.from(TL_caregiver.class);
			criteria_query4.select(root_of_criteria4);
			final_query=em.createQuery(criteria_query4);
			break;
		case "Security_chief":
			CriteriaQuery<Security_chief> criteria_query5=builder.createQuery(Security_chief.class);
			Root<Security_chief> root_of_criteria5=criteria_query5.from(Security_chief.class);
			criteria_query5.select(root_of_criteria5);
			final_query=em.createQuery(criteria_query5);
			break;
		case "Vet_chief":
			CriteriaQuery<Vet_chief> criteria_query6=builder.createQuery(Vet_chief.class);
			Root<Vet_chief> root_of_criteria6=criteria_query6.from(Vet_chief.class);
			criteria_query6.select(root_of_criteria6);
			final_query=em.createQuery(criteria_query6);
			break;
		}
		///////End of switch//////////
		
		Manager man=(Manager) final_query.getSingleResult();	
				
		Query q=em.createQuery
				("select man.workers_list From Manager as man where man.id = :ID");
		q.setParameter("ID", man.getId() );
		List<Worker> results=q.getResultList();

		List<Worker> unmodifiable_copy=
				Collections.unmodifiableList(results);
			
		tx.commit();
		em.close();	

		return unmodifiable_copy;
	}

	//TODO: This method is not good, i didn't succeed in implementing it properly
	@Override
	public List<Object[]> getYourStaff_partly(String position) {
		return null;
	
	}

	//I don't use this mehod because I moved the switch inside the getYourStaff method
	public Object getPositionClass(EntityManager em,String position) {

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		
		//I will insert a CriteriaQuery object in here
		Object criteria_query= null;

		switch (position) {
		case "TL_casheer": 
			criteria_query=builder.createQuery(TL_casheer.class);
			break;
		case "TL_cleaner":
			criteria_query=builder.createQuery(TL_cleaner.class);
			break;
		case "TL_janitor":
			criteria_query=builder.createQuery(TL_janitor.class);
			break;
		case "TL_caregiver":
			criteria_query=builder.createQuery(TL_caregiver.class);
			break;
		case "Security_chief":
			criteria_query=builder.createQuery(Security_chief.class);
			break;
		case "Vet_chief":
			criteria_query=builder.createQuery(Vet_chief.class);
			break;
		}

		return criteria_query;

	}

}
