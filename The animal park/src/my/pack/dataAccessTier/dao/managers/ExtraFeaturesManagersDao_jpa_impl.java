package my.pack.dataAccessTier.dao.managers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import my.pack.dataAccessTier.domain.workers.Guard;
import my.pack.dataAccessTier.domain.workers.Guard_;

@Repository(value="ExtraFeaturesManagersDao_jpa_impl")
public class ExtraFeaturesManagersDao_jpa_impl implements
		ExtraFeaturesManagersDao {

	private EntityManagerFactory emf;
	
	//@PersistenceContext(name="myDatabaseConfig")
	private EntityManager em;
	
	public ExtraFeaturesManagersDao_jpa_impl() {
		System.out.println("Hi! i'm in ExtraFeaturesManagersDao_jpa_impl constructor");
		emf=Persistence.createEntityManagerFactory("myDatabaseConfig");
	}
	
	@Override
	public List<Object[]> view_and_setup_weapon_license_num() {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query query=em.createQuery
		("select guard.id,guard.first_name,guard.last_name,guard.weapon_num from Guard as guard");
		
		List<Object[]> guards_list=query.getResultList();
		
		tx.commit();
		em.close();
		
		return guards_list;
	}

	//There was no need to pull all the guard's object
	//from the DB here.
	@Override
	public void set_weapon_num(String guard_id, long value) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Guard> criteria_query=builder.createQuery(Guard.class);
		Root<Guard> root_of_query=criteria_query.from(Guard.class);
		
		ParameterExpression<Long> id=builder.parameter(Long.class);
		criteria_query.where(builder.equal(  root_of_query.get(Guard_.id) ,id)   );
		TypedQuery<Guard> final_query=em.createQuery(criteria_query);
		final_query.setParameter(id, Long.parseLong(guard_id));
		
		Guard guard=final_query.getSingleResult();
		guard.setWeapon_num(value);
		
		tx.commit();
		em.close();
	}

}
