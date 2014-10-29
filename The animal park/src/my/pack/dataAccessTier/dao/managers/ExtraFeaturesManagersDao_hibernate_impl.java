package my.pack.dataAccessTier.dao.managers;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.workers.Guard;
import my.pack.utils.Config_File;

@Repository(value="ExtraFeaturesManagersDao_hibernate_impl")
public class ExtraFeaturesManagersDao_hibernate_impl implements ExtraFeaturesManagersDao {


	@Override
	public List<Object[]> view_and_setup_weapon_license_num() {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query=session.createQuery
		("select guard.id,guard.first_name,guard.last_name,guard.weapon_num from Guard as guard");
		
		List<Object[]> guards_list=query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return guards_list;
	}

	//There was no need to pull all the guard's object
	//from the DB here.
	@Override
	public void set_weapon_num(String guard_id, long value) {

		Session session=Config_File.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria criteria=session.createCriteria(Guard.class)
					.add(Restrictions.like("id", Long.parseLong(guard_id)));
							
		Guard guard=(Guard) criteria.list().get(0);
		guard.setWeapon_num(value);
		
		session.getTransaction().commit();
		session.close();
			
	}

}
