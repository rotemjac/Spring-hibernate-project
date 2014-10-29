package my.pack.all_methods_performance;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


//@Repository(value="Dao_performance_data_jpa_impl")
@Repository(value="Dao_performance_data")
public class Dao_performance_data_jpa_impl 
implements Dao_performance_data {

	private EntityManagerFactory emf;
	
	//@PersistenceContext(name="myDatabaseConfig")
	private EntityManager em;
	
	public Dao_performance_data_jpa_impl() {
		System.out.println("Hi! i'm in Dao_performance_data_jpa_impl constructor");
		emf=Persistence.createEntityManagerFactory("myDatabaseConfig");
	}

	
	@Override
	public void create(Performance_data pd) {
		
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(pd);
		tx.commit();
		em.close();		

	}

	@Override
	public Set<Method_performance> get_all_methods_data() {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		Query q=em.createQuery
				("select pd.table_of_methods_performance from Performance_data as pd");

		Set<Method_performance> returned_methods=(Set<Method_performance>) q.getResultList();
		
		tx.commit();
		em.close();		
		return returned_methods;
	}

	@Override
	public void add_measure_to_method_data(String method_name,double time) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();


		Query q=em.createQuery
				("select from Method_performance as mp where mp.name_of_method = :name").
				setParameter("name", method_name);

		Method_performance mp=(Method_performance) q.getSingleResult();

		mp.add_measure(time);

		tx.commit();
		em.close();			
	}

	@Override
	public void reset_method_data(String method_name) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query q=em.createQuery
				("select from Method_performance as mp where mp.name_of_method = :name").
				setParameter("name", method_name);

		Method_performance mp=(Method_performance) q.getSingleResult();
		
		mp.reset_data();
		
		tx.commit();
		em.close();			
	}

}
