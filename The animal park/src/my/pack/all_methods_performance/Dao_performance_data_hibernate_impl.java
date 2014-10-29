package my.pack.all_methods_performance;

import java.util.Set;

import my.pack.utils.Config_File;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

//@Repository(value="Dao_performance_data_hibernate_impl")
//@Repository(value="Dao_performance_data")
public class Dao_performance_data_hibernate_impl 
implements Dao_performance_data{

	public Dao_performance_data_hibernate_impl() {
		System.out.println("Hi! i'm in Dao_performance_data_hibernate_impl constructor");
	}

	@Override
	public void create(Performance_data pd) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(pd);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public Set<Method_performance> get_all_methods_data() {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Query q=session.createQuery
				("select pd.table_of_methods_performance from Performance_data as pd");

		Set<Method_performance> returned_methods=(Set<Method_performance>) q.list();

		session.getTransaction().commit();
		session.close();		
		return returned_methods;
	}

	@Override
	public void add_measure_to_method_data(String method_name,double time) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();


		Query q=session.createQuery
				("select from Method_performance as mp where mp.name_of_method = :name").
				setParameter("name", method_name);

		Method_performance mp=(Method_performance) q.list().get(0);

		mp.add_measure(time);

		session.getTransaction().commit();
		session.close();		
	}

	@Override
	public void reset_method_data(String method_name) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery
				("select from Method_performance as mp where mp.name_of_method = :name").
				setParameter("name", method_name);

		Method_performance mp=(Method_performance) q.list().get(0);
		
		mp.reset_data();
		
		session.getTransaction().commit();
		session.close();		
	}

}
