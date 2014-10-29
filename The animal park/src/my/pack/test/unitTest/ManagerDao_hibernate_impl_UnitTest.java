package my.pack.test.unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import my.pack.dataAccessTier.dao.managers.ManagerDao_hibernate_impl;
import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.submanagers.TL_janitor;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.workers.Janitor;

import org.junit.Test;

public class ManagerDao_hibernate_impl_UnitTest {

	private ManagerDao_hibernate_impl managerDao;
	
	
	
	public ManagerDao_hibernate_impl_UnitTest() {
		managerDao=new ManagerDao_hibernate_impl();
	}

	@Test
	public void testCreate_manager() {
		
		//Create Janitors 
		Address janitor4_address=new Address("Ramat-Gan","Negev",171);
		Janitor janitor4=new Janitor("Yuval2","rabiner2",47,'M',515272,janitor4_address,8000,07310153711);
		
		Address janitor5_address=new Address("Ramat-Gan","Negev",182);
		Janitor janitor5=new Janitor("Tal2","bril2",46,'M',515273,janitor5_address,8550,07310153712);
		
		Address janitor6_address=new Address("Ramat-Gan","Negev",193);
		Janitor janitor6=new Janitor("Omer2","tzur2",45,'M',515274,janitor6_address,8530,07310153713);
		
		//Add Janitor team leader and add janitors to it
		Address tl_janitor_addr2=new Address("Raanana","Shiryon",54); 
		TL_janitor tl_janitor=new TL_janitor("Haim2", "alfasi2", 70, 'M', 022153424, tl_janitor_addr2, 14500, 052335717, 114); 
		
		//Create janitors list
		List<Worker> janitors_list=new ArrayList<Worker>();
		janitors_list.add(janitor4);
		janitors_list.add(janitor5);
		janitors_list.add(janitor6);
		
		managerDao.create_manager(tl_janitor, janitors_list);
	}

	@Test
	public void testAddWorker() {
		Address janitor7_address=new Address("Ramat-Gan","Negev",293);
		Janitor janitor7=new Janitor("Omer4","tzur4",48,'M',515277,janitor7_address,8535,07310153715);
		managerDao.addWorker("TL_janitor", janitor7);
	}
	

	@Test
	public void testGetYourStaff() {		
		managerDao.getYourStaff("TL_caregiver");
	}

	@Test
	public void testGetYourStaff_partly() {
		fail("Not yet implemented");
	}


}
