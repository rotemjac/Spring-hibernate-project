package my.pack.dataAccessTier.dao.facilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.dataAccessTier.domain.workers.Caregiver;
import my.pack.dataAccessTier.domain.workers.Vet;
import my.pack.utils.Config_File;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository(value="AreaDao_hibernate_impl")
public class AreaDao_hibernate_impl implements AreaDao {
		
	public AreaDao_hibernate_impl() {
		System.out.println("Hi! i'm in AreaDao_hibernate_impl constructor");
	}

	@Override
	public void create(Area area) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(area);
		session.getTransaction().commit();
		session.close();	
	}

	@Override
	public Area get(String num) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria cri=session.createCriteria(Area.class);
		Criterion criterion=Restrictions.like("number", num);
		cri.add(criterion);

		List<Area> results=cri.list();

		session.getTransaction().commit();
		session.close();
		if (results.get(0)!=null) return results.get(0);
		return null;
	}

	@Override
	public Area get_with_animals(String num) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria cri=session.createCriteria(Area.class);
		Criterion criterion=Restrictions.eq("number", num);
		cri.add(criterion);
		cri.setFetchMode("animals_in_area", FetchMode.JOIN);

		Area result=(Area) cri.list().get(0);

		session.getTransaction().commit();
		session.close();

		return result;

	}

	@Override
	public Area get_with_land_portions(String num) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria cri=session.createCriteria(Area.class);
		Criterion criterion=Restrictions.eq("number", num);
		cri.add(criterion);
		cri.setFetchMode("land_portions_in_area", FetchMode.JOIN);

		Area result=(Area) cri.list().get(0);

		session.getTransaction().commit();
		session.close();

		return result;
	}

	@Override
	public Area get_with_bathrooms(String num) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria cri=session.createCriteria(Area.class);
		Criterion criterion=Restrictions.eq("number", num);
		cri.add(criterion);
		cri.setFetchMode("bathrooms", FetchMode.JOIN);

		Area result=(Area) cri.list().get(0);

		session.getTransaction().commit();
		session.close();

		return result;
	}

	@Override
	public List<Area> get_all_areas() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		List<Area> areas=session.createQuery("From Area").list();
		session.getTransaction().commit();
		session.close();	
		return areas;
	}
	
	@Override
	public List<Area> get_all_areas_with_vet() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		List<Area> areas=session.createQuery("From Area as area left join fetch area.the_vet").list();
		session.getTransaction().commit();
		session.close();	
		return areas;
	}
	


	@Override
	public void update(Area area) {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(area);	
		session.getTransaction().commit();
		session.close();	
	}

	@Override
	public void delete(Area area) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Area area_to_delete=get(area.getNumber());
		session.delete(area_to_delete);
		session.getTransaction().commit();
		session.close();		
	}



	@Override
	public void put_animals_in_land_portion(String area_num,Stack<String> animal_categories) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		//Get the right area including the land_portions
		Criteria cri=session.createCriteria(Area.class);
		Criterion criterion=Restrictions.eq("number", area_num);
		cri.add(criterion);
		cri.setFetchMode("land_portions_in_area", FetchMode.JOIN);
		Area area=(Area) cri.list().get(0);

		//Get list of all land portions in the area
		List<Land_Portion> lp_list= area.getLand_portions_in_area();
		
		//Stand with iterator on the first land portion
		ListIterator<Land_Portion> lp_list_iterator=lp_list.listIterator();
		
		String category;
		
		while (!animal_categories.empty()) {
			
			category=animal_categories.pop();
			//Get the right criteria that will be inserted into the
			//getCriteriaFromCategory method
			cri=session.createCriteria(Animal.class);
			
			//Here i'll save the animals id's (strings)
			//I'm creating each time a new list
			List<String> an_ids=new LinkedList<String>();
			
			//Bring all the animals which belong to that category
			getCriteriaFromCategory(cri, category);
			List<Animal> animals_in_category=cri.list();
			
			//Get the id's (strings) from each animal and put it in list
			for(Animal an:animals_in_category) {
			an_ids.add(an.getAnimal_id());
			}
			 
			Land_Portion current_lp=lp_list_iterator.next();
			current_lp.setAnimals_id(an_ids);

		}		
		
		session.getTransaction().commit();
		session.close();	
		
	}
	
	@Override
	public void set_worker_to_area(String pos,int area_num,String first_name,String last_name) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria cri=session.createCriteria(Area.class)
							.add(Restrictions.eq("number", String.valueOf(area_num)));
		Area area=(Area) cri.list().get(0);
		
		switch (pos) {
		
		case ("Vet"):
		cri=session.createCriteria(Vet.class)
				.add(Restrictions.eq("first_name", first_name))
				.add(Restrictions.eq("last_name" , last_name));
		Vet vet=(Vet) cri.list().get(0);
		//Update bi-directional relationship
		area.setThe_vet(vet);
		vet.getAreas_of_responsibility().add(area);
		break;
		
		case ("Caregiver"):
		cri=session.createCriteria(Caregiver.class)
				.add(Restrictions.eq("first_name", first_name))
				.add(Restrictions.eq("last_name" , last_name));
		Caregiver caregiver=(Caregiver) cri.list().get(0);
		//Update bi-directional relationship
		area.setThe_caregiver(caregiver);
		caregiver.setArea_of_responsibility(area);
		break;
		
		}//End of switch
		session.getTransaction().commit();
		session.close();	
	}
	


	//Helper class (exists in animalDao Too)-we give a criteria from
		//"outside" the method because
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
		
		//TODO:I created this in order to check the SPeL feature(Look at the
		// ara_num field in the animal class) but the SPeL doesn't worl for me 
		@Override
		public int get_area_num_by_animal_id(String an_id) {
			
			Session session=Config_File.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria cri=session.createCriteria(Area.class)
					.add(Restrictions.like("area.animals_in_area", an_id));
			Area area=(Area) cri.list().get(0);
			
			session.getTransaction().commit();
			session.close();	
			
			System.out.println("HI I'm Here!!!!!");
			
			return Integer.valueOf( area.getNumber() );
		}











}
