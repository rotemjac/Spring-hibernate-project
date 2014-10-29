package my.pack.test.unitTest;

import static org.junit.Assert.*;

import my.pack.dataAccessTier.dao.facilities.AreaDao_hibernate_impl;

import org.junit.Test;

public class AreaDao_hibernate_impl_UnitTest {

	private AreaDao_hibernate_impl areaDao;
	
	public AreaDao_hibernate_impl_UnitTest() {
		areaDao=new AreaDao_hibernate_impl();
	}
	
	
	@Test
	public void testGet() {
		areaDao.get("4");
	}

	@Test
	public void testGet_with_animals() {
		areaDao.get_with_animals("4");
	}

	@Test
	public void testGet_with_land_portions() {
		areaDao.get_with_land_portions("4");
	}

	@Test
	public void testGet_with_bathrooms() {
		areaDao.get_with_bathrooms("4");
	}

	@Test
	public void testGet_all_areas() {
		areaDao.get_all_areas();
	}

	@Test
	public void testPut_animals_in_land_portion() {
		fail("Not yet implemented");
	}

	@Test
	public void testSet_worker_to_area() {
		fail("Not yet implemented");
	}


}
