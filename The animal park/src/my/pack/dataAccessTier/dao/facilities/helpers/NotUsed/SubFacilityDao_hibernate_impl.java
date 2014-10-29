package my.pack.dataAccessTier.dao.facilities.helpers.NotUsed;

import java.util.LinkedList;
import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Bathroom;
import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.subfacilities.Sub_Facility;
import my.pack.utils.Config_File;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

//@Repository(value="SubFacilityDao")
public class SubFacilityDao_hibernate_impl implements SubFacilityDao {

	@Override
	public void create(Sub_Facility sub_fac) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(sub_fac); 
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void create_many(String which_sub_fac, int num) {

		List<Sub_Facility> list_sub_fac=new LinkedList<>();
		//create_list(which_sub_fac, list_sub_fac,num);

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		for(Sub_Facility sb_f:list_sub_fac) {
			session.save(sb_f);
		}
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Sub_Facility read(String id) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Query q=session.createQuery
				("From Sub_Facility as sub_fac where sub_fac.id = :ID")
				.setParameter("ID", id);

		Sub_Facility result=(Sub_Facility) q.list().get(0);

		session.getTransaction().commit();
		session.close();		

		return result;
	}

	@Override
	public void update(Sub_Facility sub_fac) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(sub_fac); 
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Sub_Facility sub_fac) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(sub_fac); 
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Sub_Facility> getAll(String the_chosen_sub_fac) {
		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria cri=session.createCriteria(Sub_Facility.class);

		switch(the_chosen_sub_fac) {
		case "Gate":
			cri.add(Restrictions.like("id","%gate%"));
			break;
		case "Camera":
			cri.add(Restrictions.like("id","%camera%"));
			break;
		case "Fence":
			cri.add(Restrictions.like("id","%fence%"));
			break;
		case "Bathroom":
			cri.add(Restrictions.like("id","%bathroom%"));
			break;
		}

		List<Sub_Facility> results=cri.list();		
		session.getTransaction().commit();
		session.close();		

		return results;

	}

	/*public void create_list(String which_sub_fac,
							 List<Sub_Facility> list_sub_fac,int num) {

		switch(which_sub_fac) {

		case "Gate":
			for(int i=0; i<num; i++) {
				list_sub_fac.add(new Gate("gate" + i, 'Y'));
			}
			break;

		case "Camera":
			for(int i=0; i<num; i++) {
				list_sub_fac.add(new Camera("camera" + i));
			}
			break;

		case "Fence":
			for(int i=0; i<num; i++) {
				list_sub_fac.add(new Fence("fence" + i));
			}
			break;

		default:
			for(int i=0; i<num; i++) {
				list_sub_fac.add(new Bathroom("bathroom" + i, 'Y'));
			}
			break;


}
		}*/


}
