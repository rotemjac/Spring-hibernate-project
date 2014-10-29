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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion_;

@Repository(value="Land_PortionDao")
public class Land_PortionDao_jpa_impl implements Land_PortionDao {

	private EntityManagerFactory emf;

	//@PersistenceContext(name="myDatabaseConfig")
	private EntityManager em;

	public Land_PortionDao_jpa_impl() {
		emf=Persistence.createEntityManagerFactory("myDatabaseConfig");
		System.out.println("Hi! i'm in Land_PortionDao_jpa_impl constructor");
	}

	@Override
	public void create(Land_Portion land_Portion) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(land_Portion); 
		tx.commit();
		em.close();	
	}


	@Override
	public Land_Portion read(String id) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		Query q=em.createQuery
				("From Land_Portion as land_portion where land_portion.land_portion_id = :ID")
				.setParameter("ID", id);

		Land_Portion result=(Land_Portion) q.getSingleResult();

		tx.commit();
		em.close();		

		return result;
	}

	@Override
	public String read_land_portion_by_animal(Animal an) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		//Working:
		Query q1=em.createQuery
				(" from Land_Portion as lp left join fetch lp.animals_id as ids where :animal in (ids)")
				.setParameter(  "animal", an.getAnimal_id()  );

		Land_Portion result=  (Land_Portion) q1.getResultList();

		tx.commit();
		em.close();			

		return result.getLand_portion_id();
	}

	@Override
	public void update(Land_Portion land_portion) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		if (land_portion.getLand_portion_db_id()==0) {
			em.persist(land_portion);
		}

		//You have to put the result back to a new refrence
		Land_Portion lp=em.merge(land_portion);

		tx.commit();
		em.close();		
	}

	@Override
	public void delete(Land_Portion land_portion) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Land_Portion lp_to_delete=em.merge(land_portion);
		em.remove(lp_to_delete);
		tx.commit();
		em.close();	
	}

	@Override
	public List<Land_Portion> getAll() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		Query q=em.createQuery("select From Land_Portion");
		List<Land_Portion> results=q.getResultList();		
		tx.commit();
		em.close();			

		return results;
	}

	@Override
	public List<Land_Portion> get_all_lp_with_problems_and_areas() {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Land_Portion> criteria_query=builder.createQuery(Land_Portion.class);
		Root<Land_Portion> root_of_query=criteria_query.from(Land_Portion.class);

		root_of_query.fetch("list_of_problems",JoinType.LEFT);
		root_of_query.fetch("area",JoinType.LEFT);
		
		criteria_query.where(builder.equal(root_of_query.get(Land_Portion_.need_to_fix), 'Y'));

		TypedQuery<Land_Portion> final_query=em.createQuery(criteria_query);
		
		List<Land_Portion> lp_list=final_query.getResultList();

		tx.commit();
		em.close();	

		return lp_list;
	}


	@Override
	public void set_problem_to_land_portion(String problem,String area_num, String lp_num) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Land_Portion> criteria_query=builder.createQuery(Land_Portion.class);
		Root<Land_Portion> root_of_query=criteria_query.from(Land_Portion.class);

		criteria_query.where(builder.like
				(root_of_query.get(Land_Portion_.land_portion_id), "area"+area_num+"_lp"+lp_num));

		TypedQuery<Land_Portion> final_query=em.createQuery(criteria_query);

		Land_Portion lp=final_query.getSingleResult();


		lp.getList_of_problems().add(problem);			

		lp.setNeed_to_fix('Y');

		tx.commit();
		em.close();	

	}

	@Override
	public void remove_problems_for_land_portion(String lp_id) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Land_Portion> criteria_query=builder.createQuery(Land_Portion.class);
		Root<Land_Portion> root_of_query=criteria_query.from(Land_Portion.class);

		root_of_query.fetch("list_of_problems",JoinType.LEFT);
		
		criteria_query.where(builder.equal
				(root_of_query.get(Land_Portion_.land_portion_id), lp_id));

		TypedQuery<Land_Portion> final_query=em.createQuery(criteria_query);

		Land_Portion lp=final_query.getSingleResult();

		while (lp.getList_of_problems().size()!=0) {

			lp.getList_of_problems().remove(0);
		}

		lp.setNeed_to_fix('N');

		tx.commit();
		em.close();	

	}


	@Override
	public List<Land_Portion> get_all_lp_with_animals_id() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Land_Portion> criteria_query=builder.createQuery(Land_Portion.class);
		Root<Land_Portion> root_of_query=criteria_query.from(Land_Portion.class);

		root_of_query.fetch("animals_id",JoinType.LEFT);
		
		criteria_query.select(root_of_query);

		criteria_query.distinct(true);

		TypedQuery<Land_Portion> final_query=em.createQuery(criteria_query);

		List<Land_Portion> results=final_query.getResultList();

		tx.commit();
		em.close();	
		return results;
	}

	@Override
	public void set_animals_land_portion(String lp_id, String an_id,char clean_lp_flag) {	
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Land_Portion> criteria_query=builder.createQuery(Land_Portion.class);
		Root<Land_Portion> root_of_query=criteria_query.from(Land_Portion.class);

		root_of_query.fetch("animals_id",JoinType.LEFT);
		
		criteria_query.select(root_of_query);
		
		TypedQuery<Land_Portion> final_query=em.createQuery(criteria_query);
		List<Land_Portion> all_land_portions=final_query.getResultList();

		Land_Portion chosen_lp = null;
		//Get the chosen lp
		for (Land_Portion lp:all_land_portions) {
			if ( lp.getLand_portion_id().equals(lp_id) ) {
				chosen_lp=lp;
				break;
			}
		}

		//If there was a request to remove the animals
		//which were there before
		if (clean_lp_flag=='Y') {
			while (chosen_lp.getAnimals_id().size()!=0) {
				chosen_lp.getAnimals_id().remove(0);
			}
		}

		//Add the animal to the chosen lp
		if (!chosen_lp.getAnimals_id().contains(an_id)) {
			chosen_lp.getAnimals_id().add(an_id);
		}

		///Update it in the DB
		Land_Portion chosen_lp2=em.merge(chosen_lp);

		//If the animal was in other land portion - remove it from there
		for (Land_Portion current_lp:all_land_portions) {
			if (current_lp!=chosen_lp && current_lp.getAnimals_id().contains(an_id)) {
				current_lp.getAnimals_id().remove(an_id);
				break;
			}
		}

		tx.commit();
		em.close();	

	}

}
