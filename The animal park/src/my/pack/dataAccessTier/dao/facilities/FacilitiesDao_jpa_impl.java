package my.pack.dataAccessTier.dao.facilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Camera_;
import my.pack.dataAccessTier.domain.subfacilities.Economic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Economic_facilities_;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities_;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;
import my.pack.dataAccessTier.domain.topmanagers.Facilities_;

@Repository(value="FacilitiesDao_jpa_impl")
public class FacilitiesDao_jpa_impl implements FacilitiesDao {

	private EntityManagerFactory emf;

	//@PersistenceContext(name="myDatabaseConfig")
	private EntityManager em;

	public FacilitiesDao_jpa_impl() {
		emf=Persistence.createEntityManagerFactory("myDatabaseConfig");
		System.out.println("Hi! i'm in FacilitiesDao_jpa_impl constructor");
	}
	

	@Override
	public void create(Facilities fac) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(fac);
		tx.commit();
		em.close();	
	}
	
	@Override
	public Facilities get() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Facilities> criteria_query=builder.createQuery(Facilities.class);
		
		TypedQuery<Facilities> final_query=em.createQuery(criteria_query);
		Facilities result=final_query.getSingleResult();

		tx.commit();
		em.close();		
		return result;
	}

	@Override
	public void update(Facilities fac) {	
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		if (fac.getDb_id()==0) {
			em.persist(fac);
		}
		
		Facilities fac2=em.merge(fac);
		tx.commit();
		em.close();	
		
		
	}

	@Override
	public void delete(Facilities fac) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Facilities fac_to_delete=get();
		em.remove(fac_to_delete);
		tx.commit();
		em.close();			
	}
	
	

	//////////////////////////////////////
	
	@Override
	public void add_areas_to_log_fac(List<Area> areas) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query q=em.createQuery
		("select fac.log_fac From Facilities as fac");
		
		Logistic_facilities log_fac=(Logistic_facilities) q.getSingleResult();
		log_fac.setAreas(areas);
		
		Logistic_facilities log_fac2=em.merge(log_fac);
		
		tx.commit();
		em.close();	
	}	
	
	@Override
/*	@Transactional(
			isolation=Isolation.SERIALIZABLE,
			propagation=Propagation.REQUIRES_NEW
			)*/
	public void add_or_sub_Ticket(char oper) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query q=em.createQuery("select fac From Facilities as fac ");
		
		List<Facilities> results=q.getResultList();
		
		Facilities fac=results.get(0);
		fac.getEco_fac().add_or_sub_ticket(oper);
		
		tx.commit();
		em.close();		
	}
	
	@Override
	public int get_number_of_tickets_sold() {		
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Object> criteria_query=builder.createQuery(Object.class);
		Root<Facilities> root_of_query=criteria_query.from(Facilities.class);
		
		criteria_query.select(builder.array(root_of_query.get("eco_fac").get("tickets_sold")));
		
		TypedQuery<Object> final_query=em.createQuery(criteria_query);
		int res=(int) final_query.getSingleResult();

		tx.commit();
		em.close();			
		return res;
	}
	
	@Override
	public void add_or_sub_people_out(char oper) {	
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Economic_facilities> criteria_query=builder.createQuery(Economic_facilities.class);
		Root<Facilities> root_of_query=criteria_query.from(Facilities.class);
		
		criteria_query.select(root_of_query.get(Facilities_.eco_fac));
		
		TypedQuery<Economic_facilities> final_query=em.createQuery(criteria_query);
		
		Economic_facilities eco_fac=final_query.getSingleResult();
		eco_fac.add_or_sub_people_out(oper);
		
		tx.commit();
		em.close();			
	}
	
	@Override
	public int get_people_inside() {		
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteria_query=builder.createQuery(Object[].class);
		Root<Facilities> root_of_query=criteria_query.from(Facilities.class);
		
		criteria_query.select(builder.array(
								root_of_query.get("eco_fac").get("tickets_sold"),
								root_of_query.get("eco_fac").get("people_out")));
		
		TypedQuery<Object[]> final_query=em.createQuery(criteria_query);
			
		
		//Getting the results
		Object[] results=(Object[]) final_query.getSingleResult();
		int tickets_sold=(int) results[0];
		int people_out=(int) results[1];

		tx.commit();
		em.close();	
		return (tickets_sold-people_out);
	}

	@Override
	public Logistic_facilities getLog_fac_with_cameras() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query query=em.createQuery
				("select fac from Facilities as fac inner " +
				"join fac.log_fac as log_fac left join fetch fac.log_fac.cameras");		
		
		Facilities result=(Facilities)query.getSingleResult();
		
		tx.commit();
		em.close();			
		
		return result.getLog_fac();
	}

	@Override
	public Logistic_facilities getLog_fac_with_gates() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query query=em.createQuery("select fac from Facilities as fac inner " +
		"join fac.log_fac as log_fac left join fetch fac.log_fac.gates");	

		Facilities result=(Facilities) query.getSingleResult();
		
		tx.commit();
		em.close();			
		
		return result.getLog_fac();
	}

	@Override
	public Logistic_facilities getLog_fac_with_fences() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query query=em.createQuery("select fac from Facilities as fac inner " +
		"join fac.log_fac as log_fac left join fetch fac.log_fac.fences");	

		Facilities result=(Facilities) query.getSingleResult();
		
		tx.commit();
		em.close();			
		
		return result.getLog_fac();
	}


}
