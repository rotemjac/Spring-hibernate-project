package my.pack.dataAccessTier.dao.workers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import my.pack.dataAccessTier.domain.superclasses.Worker;

@Repository(value="WorkerDao_jpa_impl")
public class WorkerDao_jpa_impl implements WorkerDao{

	private EntityManagerFactory emf;
	
	//@PersistenceContext(name="myDatabaseConfig")
	private EntityManager em;
	
	public WorkerDao_jpa_impl() {
		System.out.println("Hi! i'm in WorkerDao_jpa_impl constructor");
		emf=Persistence.createEntityManagerFactory("myDatabaseConfig");
	}
	
	@Override
	public void create(Worker worker) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		if (worker.getWorker_db_id()==0) {
			em.persist(worker);
		}
		else {
			em.merge(worker);
		}
		
		tx.commit();
		em.close();
	}

	@Override
	public Worker read(Worker worker) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query q=em.createQuery("select worker From Worker as worker where worker.id = :ID");
		q.setParameter("ID", worker.getId());
		Worker worker_returned=(Worker) q.getSingleResult();
		
		tx.commit();
		em.close();
		
		return worker_returned;
	}


	
	@Override
	public void delete(Worker worker) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		//Before deleting, we need to make the deleted
		//object persisted!
		Query q=em.createQuery("select worker From Worker as worker where worker.id = :ID");
		q.setParameter("ID", worker.getId());
		Worker worker_to_delete=(Worker) q.getSingleResult();
		
		//Now we can delete it
		em.remove(worker_to_delete);
		
		tx.commit();
		em.close();
		
	}

}
