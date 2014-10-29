package my.pack.dataAccessTier.dao.facilities;

import java.util.Collections;
import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.workers.Vet;
import my.pack.utils.Config_File;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository(value="AnimalDao_hibernate_impl")
public class AnimalDao_hibernate_impl implements AnimalDao {

	public AnimalDao_hibernate_impl() {
		System.out.println("Hi! i'm in AnimalDao_hibernate_impl");
	}
	
	/**
	 * Using HQL to build methods
	 */
	
	@Override
	public void create(Animal animal) {	
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(animal); 
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Animal readById(String id) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery
		("From Animal as animal where animal.animal_id = :ID")
		.setParameter("ID", id);
		
		Animal result=(Animal) q.list().get(0);
		
		session.getTransaction().commit();
		session.close();		
		
		return result;
	}
	
	@Override
	public List<Animal> getAllAnimals() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Query q=session.createQuery("From Animal");
		
		List<Animal> results=q.list();
		List<Animal> unmodifiable_copy=
				Collections.unmodifiableList(results);
		
		session.getTransaction().commit();
		session.close();		
		return unmodifiable_copy;
	}
	
	@Override
	public List<Animal> get_unchecked_animals() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Query q=session.createQuery
				("From Animal as an where an.need_a_vet_check='Y'");

		
		List<Animal> results=q.list();
		List<Animal> unmodifiable_copy=
				Collections.unmodifiableList(results);
		
		session.getTransaction().commit();
		session.close();		
		return unmodifiable_copy;
	}
	
	@Override
	public void update(Animal animal) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(animal);
		session.getTransaction().commit();
		session.close();		
	}
	
	@Override
	public void delete(Animal animal) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		//Make the pbject persist and then you can delete
		Animal animal_to_delete=readById(animal.getAnimal_id());
		session.delete(animal_to_delete);
		
		session.getTransaction().commit();
		session.close();
	}
	


	/**
	 * Using Criteria to build methods
	 */
	

	
	@Override
	public List<Animal> readByCategory(String category) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria cri=session.createCriteria(Animal.class);
		getCriteriaFromCategory(cri,category);
		List<Animal> results=cri.list();
		
		session.getTransaction().commit();
		session.close();		
		
		return results;
	}
	

	
	@Override
	public List<Animal> readByVet(String first_name, String last_name) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Query query=session.createQuery
				("select area.animals_in_area from Area as area where " +
						"area.the_vet.first_name= :First_Name and "     +
						"area.the_vet.last_name=  :Last_Name")
				.setParameter("First_Name", first_name)
				.setParameter("Last_Name", last_name);
				
		List<Animal> animals=query.list();
		
		session.getTransaction().commit();
		session.close();	
		
		return animals;
	}


	@Override
	public void updateAreaNum(String category, String area_num) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		//The variable "rows_affected" tells us how many rows where affected because of the query
		int rows_affected=session.createQuery
		("update Animal as an set an.area_num= :AREA_NUM where an.department= :CATEGORY or" +
				" an.series= :CATEGORY or an.genus= :CATEGORY or an.species= :CATEGORY").
				setParameter("AREA_NUM", area_num).
				setParameter("CATEGORY", category).
				executeUpdate();
		
		session.getTransaction().commit();
		session.close();	
	}

	//Here we'll use the readByCategory method
	@Override
	public void updateFood(String category, String food) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		
		//The variable "rows_affected" tells us how many rows where affected because of the query
		int rows_affected=session.createQuery
		("update Animal as an set an.food= :FOOD where an.department= :CATEGORY or" +
				" an.series= :CATEGORY or an.genus= :CATEGORY or an.species= :CATEGORY").
				setParameter("FOOD", food).
				setParameter("CATEGORY", category).
				executeUpdate();
		
/*		Criteria cri=session.createCriteria(Animal.class);
		getCriteriaFromCategory(cri,category);
		
		List<Animal> results=cri.list();

		for(Animal an:results) {
			an.setFood(food);
		}*/
		
		session.getTransaction().commit();
		session.close();	
		
		
	}

	@Override
	public void mark_animal_that_need_vet_check(String id,char need_check) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria cri=session.createCriteria(Animal.class);
		cri.add(Restrictions.eq("animal_id" , id));
		Animal animal_checked=(Animal) cri.list().get(0);
		
		animal_checked.setNeed_a_vet_check(need_check);
		session.getTransaction().commit();
		session.close();	
		
	}
	
	//Helper class-we give a criteria from "outside" the method because
	// we want to save opening a session.
	public void getCriteriaFromCategory(Criteria cri,String category) {
		
		Criterion cri1= Restrictions.like("department", category);
		Criterion cri2= Restrictions.like("series", category);
		Criterion cri3= Restrictions.like("genus", category);
		Criterion cri4= Restrictions.like("species", category);
		
		//Cannot do more than 2 OR's, so i split it.
		LogicalExpression orExp1=Restrictions.or(cri1, cri2);
		LogicalExpression orExp2=Restrictions.or(cri3, cri4);
		
		LogicalExpression totalExp=Restrictions.or(orExp1, orExp2);
		cri.add(totalExp);
		
	}
	
	/**
	 * Using SQL to build methods
	 */
	
	
	//TODO:Think maybe i can do shirur of string instead of the 4 defenitions
	@Override
	public List<String> read_only_groups_by_category(String category) {		
		String department_query="Select distinct an.department from Animal as an";
		String series_query="Select distinct an.series from Animal as an";
		String genus_query="Select distinct an.genus from Animal as an";
		String species_query="Select distinct an.species from Animal as an";
		
		Query query = null;
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		switch(category) {
		case("Department"):query=session.createSQLQuery(department_query);
		break;
		case("Series"):query=session.createSQLQuery(series_query);
		break;
		case("Genus"): query=session.createSQLQuery(genus_query);
		break;
		case("Species"):query=session.createSQLQuery(species_query);
		break;
		}
		
		List<String> results=query.list();
		session.getTransaction().commit();
		session.close();	
		return results;
	}

	@Override
	public List<String> getOnlyAnimalsIds() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria cri=session.createCriteria(Animal.class)
						.setProjection(Projections.property("animal_id"));
		
		List<String> results=cri.list();

		
		session.getTransaction().commit();
		session.close();
		
		return results;	
		
	}



}
