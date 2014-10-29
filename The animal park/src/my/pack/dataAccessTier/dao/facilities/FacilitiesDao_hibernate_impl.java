package my.pack.dataAccessTier.dao.facilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Economic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Sub_Facility;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;
import my.pack.utils.Config_File;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

@Repository(value="FacilitiesDao_hibernate_impl")
public class FacilitiesDao_hibernate_impl implements FacilitiesDao {


	
	public FacilitiesDao_hibernate_impl() {
		System.out.println("Hi! i'm in FacilitiesDao_hibernate_impl constructor");
	}
	
	@Override
	public void create(Facilities fac) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(fac);
		session.getTransaction().commit();
		session.close();	
	}
	
	@Override
	public Facilities get() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria cri=session.createCriteria(Facilities.class);
		Facilities result=(Facilities) cri.list().get(0);
		
		session.getTransaction().commit();
		session.close();	
		return result;
	}

	@Override
	public void update(Facilities fac) {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(fac);
		session.getTransaction().commit();
		session.close();	
		
		
	}

	@Override
	public void delete(Facilities fac) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Facilities fac_to_delete=get();
		session.delete(fac_to_delete);
		session.getTransaction().commit();
		session.close();		
	}
	
	
	
	@Override
	public void add_areas_to_log_fac(List<Area> areas) {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery
		("select fac.log_fac From Facilities as fac");
		
		Logistic_facilities log_fac=(Logistic_facilities) q.list().get(0);
		log_fac.setAreas(areas);
		
		session.update(log_fac);
		session.getTransaction().commit();
		session.close();
	}	
	

	
	@Override
/*	@Transactional(
			isolation=Isolation.SERIALIZABLE,
			propagation=Propagation.REQUIRES_NEW
			)*/
	public void add_or_sub_Ticket(char oper) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery("From Facilities ");
		List<Facilities> results=q.list();
		
		Facilities fac=results.get(0);
		fac.getEco_fac().add_or_sub_ticket(oper);
		
		session.getTransaction().commit();
		session.close();	
	}
	
	@Override
	public int get_number_of_tickets_sold() {		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria cri=session.createCriteria(Facilities.class,"fac")
				.setProjection(Projections.property("fac.eco_fac.tickets_sold"));
				
		int res=(int) cri.list().get(0);

		session.getTransaction().commit();
		session.close();		
		return res;
	}
	
	@Override
	public void add_or_sub_people_out(char oper) {	
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q=session.createQuery("From Facilities ");
		List<Facilities> results=q.list();
		
		Facilities fac=results.get(0);
		fac.getEco_fac().add_or_sub_people_out(oper);
		
		session.getTransaction().commit();
		session.close();		
	}
	
	@Override
	public int get_people_inside() {		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria cri=session.createCriteria(Facilities.class,"fac");
		
		//Building projection list
		ProjectionList projectionList=Projections.projectionList();
		projectionList.add(Projections.property("fac.eco_fac.tickets_sold"));
		projectionList.add(Projections.property("fac.eco_fac.people_out"));
		
		//Adding it ti the criteria
		cri.setProjection(projectionList);
		
		//Getting the results
		Object[] results=(Object[]) cri.list().get(0);
		int tickets_sold=(int) results[0];
		int people_out=(int) results[1];

		session.getTransaction().commit();
		session.close();
		return (tickets_sold-people_out);
	}

	@Override
	public Logistic_facilities getLog_fac_with_cameras() {
		
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria criteria=session.createCriteria(Facilities.class)
						  .setFetchMode("log_fac.cameras", FetchMode.JOIN);

		Facilities result= (Facilities) criteria.list().get(0);
		
		session.getTransaction().commit();
		session.close();		
		
		return result.getLog_fac();
	}

	@Override
	public Logistic_facilities getLog_fac_with_gates() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria criteria=session.createCriteria(Facilities.class)
						  .setFetchMode("log_fac.gates", FetchMode.JOIN);

		Facilities result= (Facilities) criteria.list().get(0);
		
		session.getTransaction().commit();
		session.close();		
		
		return result.getLog_fac();
	}

	@Override
	public Logistic_facilities getLog_fac_with_fences() {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria criteria=session.createCriteria(Facilities.class)
						  .setFetchMode("log_fac.fences", FetchMode.JOIN);

		Facilities result= (Facilities) criteria.list().get(0);
		
		session.getTransaction().commit();
		session.close();		
		
		return result.getLog_fac();
	}

}
