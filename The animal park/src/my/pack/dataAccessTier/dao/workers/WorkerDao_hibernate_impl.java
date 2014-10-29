package my.pack.dataAccessTier.dao.workers;

import java.util.List;

import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.utils.Config_File;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository(value="WorkerDao_hibernate_impl")
public class WorkerDao_hibernate_impl implements WorkerDao {

	public WorkerDao_hibernate_impl() {
		System.out.println("Hi! i'm in WorkerDao_hibernate_impl constructor");
		}
	
	@Override
	public void create(Worker worker) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(worker);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Worker read(Worker worker) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery("From Worker as worker where worker.id = :ID");
		q.setParameter("ID", worker.getId());
		List<Worker> results=q.list();
		Worker worker_returned=results.get(0);
		
		session.getTransaction().commit();
		session.close();
		return worker_returned;
	}


	
	@Override
	public void delete(Worker worker) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		//Before deleting, we need to make the deleted
		//object persisted!
		Worker worker_to_delete=read(worker);
		
		//Now we can delete it
		session.delete(worker);
		
		session.getTransaction().commit();
		session.close();
	}



}
