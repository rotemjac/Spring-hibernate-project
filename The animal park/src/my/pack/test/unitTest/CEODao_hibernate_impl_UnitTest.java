package my.pack.test.unitTest;

import static org.junit.Assert.*;

import my.pack.dataAccessTier.dao.managers.CEODao_hibernate_impl;
import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.submanagers.TL_janitor;

import org.junit.Test;

public class CEODao_hibernate_impl_UnitTest {

	private CEODao_hibernate_impl ceoDao;
	
	public CEODao_hibernate_impl_UnitTest(){
		ceoDao=new CEODao_hibernate_impl();
	}
	
	@Test
	public void testCreateCeo() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddSubManager() {
		
		//Add Janitor team leader and add janitors to it
		Address tl_janitor_addr2=new Address("Raanana","Shiryon",44); 
		TL_janitor tl_janitor=new TL_janitor("Haim", "alfasi", 60, 'M', 4773650, tl_janitor_addr2, 13500, 052335717, 104); 
		//ceoDao.addSubManager(tl_janitor);
		
	}

	@Test
	public void testGetAllWorkers() {
		ceoDao.getAllWorkers();
	}

	@Test
	public void testGetAllManagers() {
		ceoDao.getAllManagers();
	}

	@Test
	public void testGetAllPositions() {
		ceoDao.getAllPositions();
	}

	@Test
	public void testSetFacilities() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFacilities() {
		ceoDao.getFacilities();
	}

	@Test
	public void testDelete_worker() {
		fail("Not yet implemented");
	}

}
