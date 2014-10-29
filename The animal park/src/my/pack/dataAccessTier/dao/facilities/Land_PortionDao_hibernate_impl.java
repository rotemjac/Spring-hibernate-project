package my.pack.dataAccessTier.dao.facilities;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.utils.Config_File;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository(value="Land_PortionDao_hibernate_impl")
public class Land_PortionDao_hibernate_impl implements Land_PortionDao {

	@Override
	public void create(Land_Portion land_Portion) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(land_Portion); 
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Land_Portion read(String id) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

	Query q=session.createQuery
	("From Land_Portion as land_portion where land_portion.land_portion_id = :ID")
		.setParameter("ID", id);

		Land_Portion result=(Land_Portion) q.list().get(0);

		session.getTransaction().commit();
		session.close();		

		return result;
	}

	@Override
	public String read_land_portion_by_animal(Animal an) {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		//Working:
		Query q1=session.createQuery
	(" from Land_Portion as lp left join fetch lp.animals_id as ids where :animal in (ids)")
		.setParameter(  "animal", an.getAnimal_id()  );
		
		Land_Portion result=  (Land_Portion) q1.list().get(0);

		session.getTransaction().commit();
		session.close();		

		return result.getLand_portion_id();
	}

	@Override
	public void update(Land_Portion land_portion) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(land_portion); 
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Land_Portion land_portion) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Land_Portion lp_to_delete=read(land_portion.getLand_portion_id());
		session.delete(lp_to_delete); 
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Land_Portion> getAll() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery("From Land_Portion");
		List<Land_Portion> results=q.list();		
		session.getTransaction().commit();
		session.close();		

		return results;
	}

	@Override
	public List<Land_Portion> get_all_lp_with_problems_and_areas() {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria cri=session.createCriteria(Land_Portion.class);
		cri.add(Restrictions.eq("need_to_fix", 'Y'));
		cri.setFetchMode("list_of_problems",FetchMode.JOIN );
		cri.setFetchMode("area",FetchMode.JOIN );
		
		List<Land_Portion> lp_list=cri.list();
		session.getTransaction().commit();
		session.close();

		return lp_list;
	}


	@Override
	public void set_problem_to_land_portion(String problem,String area_num, String lp_num) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria cri=session.createCriteria(Land_Portion.class);
		cri.add(Restrictions.like("land_portion_id" , "area"+area_num+"_lp"+lp_num ));

		List<Land_Portion> results=cri.list();
		
		Land_Portion lp=results.get(0);
 		
		lp.getList_of_problems().add(problem);			
		
		lp.setNeed_to_fix('Y');

		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void remove_problems_for_land_portion(String lp_id) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria cri=session.createCriteria(Land_Portion.class);
		cri.add(Restrictions.eq("land_portion_id" , lp_id ));
		cri.setFetchMode("list_of_problems",FetchMode.JOIN );
		
		Land_Portion lp=(Land_Portion) cri.list().get(0);
		
		while (lp.getList_of_problems().size()!=0) {
			
			lp.getList_of_problems().remove(0);
		}
		
		lp.setNeed_to_fix('N');
		
		session.getTransaction().commit();
		session.close();
		
	}
	

	@Override
	public List<Land_Portion> get_all_lp_with_animals_id() {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria cri=session.createCriteria(Land_Portion.class)
							.setFetchMode("animals_id", FetchMode.JOIN)
							.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		List<Land_Portion> results=cri.list();
		
		session.getTransaction().commit();
		session.close();
		return results;
	}

	@Override
	public void set_animals_land_portion(String lp_id, String an_id,char clean_lp_flag) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		//Get all land portions including the animals ids
		Criteria cri=session.createCriteria(Land_Portion.class)
							.setFetchMode("animals_id", FetchMode.JOIN);
		
		List<Land_Portion> all_land_portions=cri.list();
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
		session.update(chosen_lp);
		
		//If the animal was in other land portion - remove it from there
		for (Land_Portion current_lp:all_land_portions) {
			if (current_lp!=chosen_lp && current_lp.getAnimals_id().contains(an_id)) {
				current_lp.getAnimals_id().remove(an_id);
				break;
			}
		}
		
		session.getTransaction().commit();
		session.close();
		
	}






}
