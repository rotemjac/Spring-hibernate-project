package my.pack.dataAccessTier.dao.facilities;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Animal_;

@Repository(value="AnimalDao_jpa_impl")
public class AnimalDao_jpa_impl implements AnimalDao {

	private EntityManagerFactory emf;

	//@PersistenceContext(name="myDatabaseConfig")
	private EntityManager em;

	public AnimalDao_jpa_impl() {
		emf=Persistence.createEntityManagerFactory("myDatabaseConfig");
		System.out.println("Hi! i'm in AnimalDao_jpa constructor");
	}
	
	@Override
	public void create(Animal animal) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		this.em.persist(animal); 
		tx.commit();
		em.close();	
	}

	@Override
	public Animal readById(String id) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Query q= this.em.createQuery
		("From Animal as animal where animal.animal_id = :ID")
		.setParameter("ID", id);
		
		Animal result=(Animal) q.getSingleResult();
		tx.commit();
		em.close();	
		return result;
	}
	
	@Override
	public List<Animal> getAllAnimals() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Query q=this.em.createQuery("From Animal");	
		List<Animal> results=q.getResultList();
		List<Animal> unmodifiable_copy=
				Collections.unmodifiableList(results);
		
		tx.commit();
		em.close();	
		return unmodifiable_copy;
	}
	
	@Override
	public List<Animal> get_unchecked_animals() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Query q=em.createQuery
				("From Animal as an where an.need_a_vet_check='Y'");
		
		List<Animal> results=q.getResultList();
		List<Animal> unmodifiable_copy=
				Collections.unmodifiableList(results);
	
		tx.commit();
		em.close();	
		return unmodifiable_copy;
	}
	
	@Override
	public void update(Animal animal) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		if (animal.getDb_id()==0) {
			em.persist(animal);
		}
		
		Animal an=em.merge(animal);
		
		tx.commit();
		em.close();	
	}
	
	@Override
	public void delete(Animal animal) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		//Make the pbject persist and then you can delete
		Query q= this.em.createQuery
		("From Animal as animal where animal.animal_id = :ID")
		.setParameter("ID", animal.getAnimal_id());
		
		Animal animal_to_delete=(Animal) q.getResultList().get(0);
		em.remove(animal_to_delete);
		
		tx.commit();
		em.close();	
	}
	


	/**
	 * Using Criteria to build methods
	 */
	
	@Override
	public List<Animal> readByCategory(String category) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		CriteriaBuilder builder=em.getCriteriaBuilder();
		
		ParameterExpression<String> cat=builder.parameter(String.class);
		
		CriteriaQuery<Animal> criteria_query=getCriteriaFromCategory(builder,cat);
		TypedQuery<Animal> final_query=em.createQuery(criteria_query);
		final_query.setParameter(cat, category);
		
		List<Animal> results=final_query.getResultList();
		
		tx.commit();
		em.close();	
		return results;
	}
	
	@Override
	public List<Animal> readByVet(String first_name, String last_name) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		Query query=em.createQuery
				("select area.animals_in_area from Area as area where " +
						"area.the_vet.first_name= :First_Name and "     +
						"area.the_vet.last_name=  :Last_Name")
				.setParameter("First_Name", first_name)
				.setParameter("Last_Name", last_name);
				
		List<Animal> animals=query.getResultList();	
		tx.commit();
		em.close();	
		
		return animals;
	}


	@Override
	public void updateAreaNum(String category, String area_num) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		//The variable "rows_affected" tells us how many rows where affected because of the query
		int rows_affected=em.createQuery
		("update Animal as an set an.area_num= :AREA_NUM where an.department= :CATEGORY or" +
				" an.series= :CATEGORY or an.genus= :CATEGORY or an.species= :CATEGORY").
				setParameter("AREA_NUM", area_num).
				setParameter("CATEGORY", category).
				executeUpdate();
		
		tx.commit();
		em.close();	
	}

	//Here we'll use the readByCategory method
	@Override
	public void updateFood(String category, String food) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		//The variable "rows_affected" tells us how many rows where affected because of the query
		int rows_affected=em.createQuery
		("update Animal as an set an.food= :FOOD where an.department= :CATEGORY or" +
				" an.series= :CATEGORY or an.genus= :CATEGORY or an.species= :CATEGORY").
				setParameter("FOOD", food).
				setParameter("CATEGORY", category).
				executeUpdate();
		
		tx.commit();
		em.close();	
	}

	
	@Override
	public void mark_animal_that_need_vet_check(String id,char need_check) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<Animal> criteria_query=builder.createQuery(Animal.class);
		Root<Animal> root=criteria_query.from(Animal.class);

		criteria_query.where(builder.equal(root.get("animal_id"), id));
		TypedQuery<Animal> final_query=em.createQuery(criteria_query);
		
		Animal animal_checked=(Animal) final_query.getSingleResult();
		
		animal_checked.setNeed_a_vet_check(need_check);	
		
		tx.commit();
		em.close();	
	}
	
	//Helper class-we give a criteria from "outside" the method because
	// we want to save opening a em.
	public CriteriaQuery<Animal> getCriteriaFromCategory(CriteriaBuilder builder,ParameterExpression<String> cat) {
		
		
		CriteriaQuery<Animal> criteria_query=builder.createQuery(Animal.class);
		Root<Animal> root_of_query=criteria_query.from(Animal.class);
		
		Predicate predicate=builder.or(		
		builder.like(root_of_query.get(Animal_.department)  ,cat),
		builder.like(root_of_query.get(Animal_.series)  ,cat),
		builder.like(root_of_query.get(Animal_.genus)   ,cat),
		builder.like(root_of_query.get(Animal_.species) ,cat)	
		);
		
		
		criteria_query.select(root_of_query);
		criteria_query.where(predicate);
		criteria_query.distinct(true);
		
		return criteria_query;
		
	}
	
	/**
	 * Using SQL to build methods
	 */
	
	//TODO:Think maybe i can do shirur of string instead of the 4 defenitions
	@Override
	public List<String> read_only_groups_by_category(String category) {	
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		String department_query="Select distinct an.department from Animal as an";
		String series_query="Select distinct an.series from Animal as an";
		String genus_query="Select distinct an.genus from Animal as an";
		String species_query="Select distinct an.species from Animal as an";
		
		Query query = null;

		
		switch(category) {
		case("Department"):query=em.createNativeQuery(department_query);
		break;
		case("Series"):query=em.createNativeQuery(series_query);
		break;
		case("Genus"): query=em.createNativeQuery(genus_query);
		break;
		case("Species"):query=em.createNativeQuery(species_query);
		break;
		}
		
		List<String> results=query.getResultList();
		
		tx.commit();
		em.close();	
			
		return results;
	}

	@Override
	public List<String> getOnlyAnimalsIds() {
		
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		CriteriaBuilder builder=em.getCriteriaBuilder();
		
		//The root class is Animal but we'll want only a string field from it
		CriteriaQuery<String> criteria_query=builder.createQuery(String.class);
		
		Root<Animal> root=criteria_query.from(Animal.class);
		criteria_query.select(root.get(Animal_.animal_id) );
		
		TypedQuery<String> final_query=em.createQuery(criteria_query);
		List<String> results=final_query.getResultList();

		tx.commit();
		em.close();	
		
		return results;	
		
	}

}
