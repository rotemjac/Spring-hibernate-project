package my.pack.dataAccessTier.dao.managers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import my.pack.cache_performance.Cache_2LC_data;
import my.pack.dataAccessTier.dao.workers.WorkerDao_hibernate_impl;
import my.pack.dataAccessTier.domain.submanagers.*;
import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.workers.Vet;
import my.pack.utils.Config_File;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Repository;

@Repository(value="ManagerDao_hibernate_impl")
public class ManagerDao_hibernate_impl 
extends WorkerDao_hibernate_impl
implements ManagerDao {


	public ManagerDao_hibernate_impl() {		
		System.out.println("Hi! i'm in ManagersDao_hibernate_impl constructor");
	}

	@Override
	public void create_manager(Manager man, List<Worker> workers_list) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		for (Worker worker:workers_list) {
			man.getWorkers_list().add(worker);
			session.save(worker);
		}
		
		man.setNumber_of_workers(man.getWorkers_list().size());

		session.persist(man);
		
		session.getTransaction().commit();
		session.close();		
	}

	@Override
	public void addWorker(String man_position, Worker worker) {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		//Just to check something in the polymorphism
		System.out.println(worker.getClass().toString());
		
		//Get the right manager
		Query q=session.createSQLQuery
				("Select * From Worker where Worker_Type = :pos")
				.addEntity(Worker.class)
				.setParameter("pos", man_position);

		Manager man=(Manager) q.list().get(0);
		
		//If the worker wasn't added before
		session.saveOrUpdate(worker);
		
		//I have seperated one line into the next two because of debugging issues
		//Its not mendatory
		List<Worker> worker_list=man.getWorkers_list();
		worker_list.add(worker);

		
/*		
		//TODO: 
		//		Notice 1:
		//	   All the lines with remarks are because
		//     I didn't succeed to cast from the worker object
		//	   into the sub-class objects (i got an java lang classcastexception)
		//     i defined all the refrences before the switch for example:
		//     Casheer new_csr=new Casheer();  and did this to all the others as well
		//    Only when it didn't work i transfered all after the switch
		
		switch(man_position) {

		case("TL_casheer"):
			//new_casheer=(Casheer) worker; (Notice 1)
			Casheer new_casheer = new Casheer
			(worker.getFirst_name(),worker.getLast_name(),worker.getAge(),worker.getGender(),
					worker.getId(),worker.getAddress(),worker.getSalary(),worker.getCell_number());
		
			//Notice 2:
			//It is very important to save to worker object first,
			//If it's already exits in the DB - it will be 
			//Updated if its neccessery
			//session.saveOrUpdate(new_casheer);
			man.getWorkers_list().add(new_casheer);
		break;

		case("TL_cleaner"):
			//new_cleaner=(Cleaner)worker;(Notice 1)
			Cleaner new_cleaner = new Cleaner(worker.getFirst_name(),worker.getLast_name(),worker.getAge(),worker.getGender(),
			worker.getId(),worker.getAddress(),worker.getSalary(),worker.getCell_number());
			
			//See Notice 2
			//session.saveOrUpdate(new_cleaner);
			man.getWorkers_list().add(new_cleaner);
		break;

		case("TL_janitor"):
			//new_janitor=(Janitor)worker;(Notice 1)
			Janitor new_janitor = new Janitor(worker.getFirst_name(),worker.getLast_name(),worker.getAge(),worker.getGender(),
			worker.getId(),worker.getAddress(),worker.getSalary(),worker.getCell_number());
			
			//See Notice 2
			//session.saveOrUpdate(new_janitor);
			man.getWorkers_list().add(new_janitor);
		break;

		case("TL_Caregiver"):
			//new_caregiver=(Caregiver)worker;(Notice 1)
			Caregiver new_caregiver = new Caregiver(worker.getFirst_name(),worker.getLast_name(),worker.getAge(),worker.getGender(),
			worker.getId(),worker.getAddress(),worker.getSalary(),worker.getCell_number());
			
			//See Notice 2
			//session.saveOrUpdate(new_caregiver);
			
			man.getWorkers_list().add(new_caregiver);
		break;

		case("Security_chief"):	
			//new_guard=(Guard)worker;(Notice 1)
			Guard new_guard = new Guard(worker.getFirst_name(),worker.getLast_name(),worker.getAge(),worker.getGender(),
			worker.getId(),worker.getAddress(),worker.getSalary(),worker.getCell_number());

			//See Notice 2
			//session.saveOrUpdate(new_guard);
			
			man.getWorkers_list().add(new_guard);
		break;

		case("Vet_chief"):
			//new_vet=(Vet) worker;(Notice 1)
			Vet new_vet = new Vet(worker.getFirst_name(),worker.getLast_name(),worker.getAge(),worker.getGender(),
			worker.getId(),worker.getAddress(),worker.getSalary(),worker.getCell_number());
			
			//See Notice 2
			//session.saveOrUpdate(new_vet);
			man.getWorkers_list().add(new_vet);
		break;

		}*/
		
		man.setNumber_of_workers(worker_list.size());
		//man.setNumber_of_workers(man.getWorkers_list().size());

		session.getTransaction().commit();
		session.close();		
	}


	@Override
	public List<Worker> getYourStaff(String position) {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
				
		Criteria criteria=getPositionClass(session,position);
		Manager man=(Manager) criteria.list().get(0);
		
		Query q=session.createQuery
				("select man.workers_list From Manager as man where man.id = :ID");
		q.setParameter("ID", man.getId() );
		List<Worker> results=q.list();

		List<Worker> unmodifiable_copy=
				Collections.unmodifiableList(results);
	
		session.getTransaction().commit();
		session.close();
		
		return unmodifiable_copy;

	}

	//TODO: This method is not good, i didn't succeed in implementing it properly
	@Override
	public List<Object[]> getYourStaff_partly(String position) {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria criteria=getPositionClass(session,position);

		Manager man=(Manager) criteria.list().get(0);		

		Query q=session.createSQLQuery
				("Select From Manager as man left join fetch man.workers where man.id = :ID");

		q.setParameter("ID", man.getId() );

		List<Worker> results=q.list();

		/*		List<Object[]> partial_results=new ArrayList();
		int i=0;

		for (Worker res:results) {
			partial_results.set(i, null)=res.getFirst_name();
		}*/

		/*		List<String> unmodifiable_copy=
				Collections.unmodifiableList(results);*/

		session.getTransaction().commit();
		session.close();

		return null;
		//return unmodifiable_copy;
	}

	//TODO: This is not a good practice, so i need to make 
	//		a clever query instead this method!!
	public Criteria getPositionClass (Session session,String position) {

		Criteria criteria = null;

		switch (position) {
		case "TL_casheer": 
			criteria=session.createCriteria(TL_casheer.class);
			break;
		case "TL_cleaner": 
			criteria=session.createCriteria(TL_cleaner.class);
			break;
		case "TL_janitor": 
			criteria=session.createCriteria(TL_janitor.class);
			break;
		case "TL_caregiver": 
			criteria=session.createCriteria(TL_caregiver.class);
			break;
		case "Security_chief": 
			criteria=session.createCriteria(Security_chief.class);
			break;
		case "Vet_chief": 
			criteria=session.createCriteria(Vet_chief.class);
			break;
		}

		return criteria;

	}



}
