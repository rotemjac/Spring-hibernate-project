package my.pack.test.unitTest;

import static org.junit.Assert.*;

import my.pack.dataAccessTier.dao.facilities.Land_PortionDao_hibernate_impl;
import my.pack.dataAccessTier.domain.subfacilities.Animal;

import org.junit.Test;

public class Land_PortionDao_hibernate_impl_UnitTest {

	Land_PortionDao_hibernate_impl lpDao;
	
	public Land_PortionDao_hibernate_impl_UnitTest() {
		lpDao=new Land_PortionDao_hibernate_impl(); 
	}
	
	@Test
	public void testRead() {
		lpDao.read("area3_lp0");
	}
	
	@Test
	public void testRead_land_portion_by_animal() {
		Animal sloth1=new Animal
		("sloth_1","Mammal","Pilosa","Sloth","Three-toed",'M',0,0,"weed",'N');
		lpDao.read_land_portion_by_animal(sloth1);
	}

	@Test
	public void testGetAll() {
		lpDao.getAll();
	}

	@Test
	public void testGet_all_lp_with_problems() {
		lpDao.get_all_lp_with_problems_and_areas();
	}

	@Test
	public void testGet_all_lp_with_animals_id() {
		lpDao.get_all_lp_with_animals_id();
	}
	
	@Test
	public void testSet_problem_to_land_portion() {
		lpDao.set_problem_to_land_portion("No light", "1", "3");
	}

	@Test
	public void testRemove_problems_for_land_portion() {
		lpDao.remove_problems_for_land_portion("area1_lp3");
	}


	@Test
	public void testSet_animals_land_portion() {
		lpDao.set_animals_land_portion("area1_lp4", "ost_1", 'N');
	}

}
