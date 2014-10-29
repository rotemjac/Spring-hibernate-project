package my.pack.test.unitTest;

import static org.junit.Assert.*;

import my.pack.dataAccessTier.dao.facilities.FacilitiesDao_hibernate_impl;

import org.junit.Test;

public class FacilitiesDao_hibernate_impl_UnitTest {

	FacilitiesDao_hibernate_impl facDao;
	
	public FacilitiesDao_hibernate_impl_UnitTest() {
		facDao=new FacilitiesDao_hibernate_impl();
	}
	
	@Test
	public void testGet() {
		facDao.get();
	}

	@Test
	public void testAdd_or_sub_Ticket() {		
		facDao.add_or_sub_Ticket('A');
	}

	@Test
	public void testAdd_or_sub_people_out() {
		facDao.add_or_sub_people_out('A');
	}
	
	@Test
	public void testGet_number_of_tickets_sold() {
		facDao.get_number_of_tickets_sold();
	}

	@Test
	public void testGet_people_inside() {
		facDao.get_people_inside();
	}

	@Test
	public void testGetLog_fac_with_cameras() {
		facDao.getLog_fac_with_cameras();
	}

	@Test
	public void testGetLog_fac_with_gates() {
		facDao.getLog_fac_with_gates();
	}

	@Test
	public void testGetLog_fac_with_fences() {
		facDao.getLog_fac_with_fences();
	}
	
	@Test
	public void testAdd_areas_to_log_fac() {
		fail("Not yet implemented");
	}

}
