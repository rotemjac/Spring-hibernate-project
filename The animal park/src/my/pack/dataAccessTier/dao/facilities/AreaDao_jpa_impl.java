package my.pack.dataAccessTier.dao.facilities;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Animal_;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Area_;
import my.pack.dataAccessTier.domain.subfacilities.Bathroom;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.dataAccessTier.domain.workers.Caregiver;
import my.pack.dataAccessTier.domain.workers.Caregiver_;
import my.pack.dataAccessTier.domain.workers.Guard;
import my.pack.dataAccessTier.domain.workers.Guard_;
import my.pack.dataAccessTier.domain.workers.Vet;
import my.pack.dataAccessTier.domain.workers.Vet_;
import my.pack.utils.Config_File;

@Repository(value="AreaDao_jpa_impl")
public class AreaDao_jpa_impl implements AreaDao {

	private EntityManagerFactory emf;

	//@PersistenceContext(name="myDatabaseConfig")
	private EntityManager em;

	public AreaDao_jpa_impl() {
		emf=Persistence.createEntityManagerFactory("myDatabaseConfig");
		System.out.println("Hi! i'm in AreaDao_jpa_impl constructor");
	}


	@Override
	public void create(Area area) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(area);
		tx.commit();
		em.close();	
	}

	@Override
	public Area get(String num) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Area> criteria_query=builder.createQuery(Area.class);
		Root<Area> root_of_query=criteria_query.from(Area.class);

		ParameterExpression<String> number=builder.parameter(String.class);
		criteria_query.where(builder.equal(root_of_query.get(Area_.number) ,number));

		TypedQuery<Area> final_query=em.createQuery(criteria_query);
		final_query.setParameter(number, num);

		Area area=final_query.getSingleResult();

		tx.commit();
		em.close();	
		if (area!=null) return area;
		return null;

	}

	@Override
	public Area get_with_animals(String num) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Area> criteria_query=builder.createQuery(Area.class);
		Root<Area> root_of_query=criteria_query.from(Area.class);
		
		root_of_query.fetch("animals_in_area",JoinType.LEFT);
		
		ParameterExpression<String> number=builder.parameter(String.class);
		criteria_query.where(builder.equal(root_of_query.get(Area_.number) ,number));

		TypedQuery<Area> final_query=em.createQuery(criteria_query);
		final_query.setParameter(number, num);



		Area area=final_query.getSingleResult();

		tx.commit();
		em.close();	

		return area;

	}

	@Override
	public Area get_with_land_portions(String num) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=em.getCriteriaBuilder();
		CriteriaQuery<Area> criteria_query=builder.createQuery(Area.class);
		Root<Area> root_of_query=criteria_query.from(Area.class);
		
		root_of_query.fetch("land_portions_in_area",JoinType.LEFT);
		
		criteria_query.select(root_of_query);
		
		ParameterExpression<String> number=builder.parameter(String.class);
		Predicate condition=builder.equal(root_of_query.get(Area_.number),number);
		criteria_query.where(condition);

		TypedQuery<Area> final_query=em.createQuery(criteria_query);
		final_query.setParameter(number, num);
				


		Area area=final_query.getSingleResult();

		tx.commit();
		em.close();	

		return area;
	}

	@Override
	public Area get_with_bathrooms(String num) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Area> criteria_query=builder.createQuery(Area.class);
		Root<Area> root_of_query=criteria_query.from(Area.class);

		root_of_query.fetch("bathrooms",JoinType.LEFT);
		
		ParameterExpression<Long> number=builder.parameter(Long.class);
		criteria_query.where(builder.equal(root_of_query.get(Area_.number) ,number));

		TypedQuery<Area> final_query=em.createQuery(criteria_query);
		final_query.setParameter("number", num);

		Area area=final_query.getSingleResult();

		tx.commit();
		em.close();	

		return area;
	}

	@Override
	public List<Area> get_all_areas() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		List<Area> areas=em.createQuery("From Area").getResultList();	
		tx.commit();
		em.close();	
		return areas;
	}
	
	@Override
	public List<Area> get_all_areas_with_vet() {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		List<Area> areas=em.createQuery
				("select area From Area as area left join fetch area.the_vet").getResultList();	
		tx.commit();
		em.close();	
		return areas;
	}

	@Override
	public void update(Area area) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		if (area.getDb_id()==0) {
			em.persist(area);
		}
		
		Area area2=em.merge(area);	
		
		tx.commit();
		em.close();		
	}

	@Override
	public void delete(Area area) {
		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Area area_to_delete=get(area.getNumber());
		em.remove(area_to_delete);
		tx.commit();
		em.close();		
	}



	@Override
	public void put_animals_in_land_portion(String area_num,Stack<String> animal_categories) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		//Get the right area including the land_portions
		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Area> criteria_query=builder.createQuery(Area.class);
		Root<Area> root_of_query=criteria_query.from(Area.class);

		root_of_query.fetch("land_portions_in_area",JoinType.LEFT);
		
		ParameterExpression<String> number=builder.parameter(String.class);
		criteria_query.where(builder.equal(root_of_query.get(Area_.number) ,number));

		TypedQuery<Area> final_query=em.createQuery(criteria_query);
		final_query.setParameter(number, area_num);

		Area area=final_query.getSingleResult();

		//Get list of all land portions in the area
		List<Land_Portion> lp_list= area.getLand_portions_in_area();

		//Stand with iterator on the first land portion
		ListIterator<Land_Portion> lp_list_iterator=lp_list.listIterator();

		String category;

		while (!animal_categories.empty()) {

			category=animal_categories.pop();

			//Here i'll save the animals id's (strings)
			//I'm creating each time a new list
			List<String> an_ids=new LinkedList<String>();

			//Bring all the animals which belong to that category
			CriteriaQuery<Animal> query=getCriteriaFromCategory(builder, category);

			TypedQuery<Animal> final_query2=em.createQuery(query);
			List<Animal> animals_in_category=final_query2.getResultList();

			//Get the id's (strings) from each animal and put it in list
			for(Animal an:animals_in_category) {
				an_ids.add(an.getAnimal_id());
			}

			Land_Portion current_lp=lp_list_iterator.next();
			current_lp.setAnimals_id(an_ids);

		}		

		tx.commit();
		em.close();	

	}

	@Override
	public void set_worker_to_area(String pos,int area_num,String first_name,String last_name) {

		em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();

		CriteriaBuilder builder=emf.getCriteriaBuilder();
		CriteriaQuery<Area> criteria_query=builder.createQuery(Area.class);
		Root<Area> root_of_query=criteria_query.from(Area.class);

		ParameterExpression<String> number=builder.parameter(String.class);
		criteria_query.where(builder.equal(root_of_query.get(Area_.number) ,number));

		TypedQuery<Area> final_query=em.createQuery(criteria_query);
		final_query.setParameter(number, String.valueOf(area_num));

		Area area=final_query.getSingleResult();

		switch (pos) {

		case ("Vet"):

			CriteriaQuery<Vet> criteria_query2=builder.createQuery(Vet.class);
		Root<Vet> root_of_query2=criteria_query2.from(Vet.class);
		criteria_query2.where(builder.equal(root_of_query2.get(Vet_.first_name) ,first_name));
		criteria_query2.where(builder.equal(root_of_query2.get(Vet_.last_name) ,last_name));

		TypedQuery<Vet> final_query2=em.createQuery(criteria_query2);
		Vet vet=(Vet) final_query2.getSingleResult();

		//Update bi-directional relationship
		area.setThe_vet(vet);
		vet.getAreas_of_responsibility().add(area);
		break;

		case ("Caregiver"):

			CriteriaQuery<Caregiver> criteria_query3=builder.createQuery(Caregiver.class);
		Root<Caregiver> root_of_query3=criteria_query3.from(Caregiver.class);

		criteria_query3.where(builder.equal(root_of_query3.get(Caregiver_.first_name) ,first_name));
		criteria_query3.where(builder.equal(root_of_query3.get(Caregiver_.last_name) ,last_name));

		TypedQuery<Caregiver> final_query3=em.createQuery(criteria_query3);
		Caregiver caregiver=(Caregiver) final_query3.getSingleResult();

		//Update bi-directional relationship
		area.setThe_caregiver(caregiver);
		caregiver.setArea_of_responsibility(area);
		break;

		}//End of switch
		tx.commit();
		em.close();		
	}



	//Helper class (exists in animalDao Too)-we give a criteria from
	//"outside" the method because
	// we want to save opening a session.
	public CriteriaQuery<Animal> getCriteriaFromCategory(CriteriaBuilder builder,String category) {

		CriteriaQuery<Animal> criteria_query=builder.createQuery(Animal.class);
		Root<Animal> root_of_query=criteria_query.from(Animal.class);

		Predicate predicate=builder.or(		
				builder.like(root_of_query.get(Animal_.department)  ,category),
				builder.like(root_of_query.get(Animal_.series)  ,category),
				builder.like(root_of_query.get(Animal_.genus)   ,category),
				builder.like(root_of_query.get(Animal_.species) ,category)	
				);


		criteria_query.select(root_of_query);
		criteria_query.where(predicate);
		criteria_query.distinct(true);

		return criteria_query;

	}

	//TODO:I created this in order to check the SPeL feature(Look at the
	// ara_num field in the animal class) but the SPeL doesn't worl for me 
	@Override
	public int get_area_num_by_animal_id(String an_id) {
		return 0;
	}



}
