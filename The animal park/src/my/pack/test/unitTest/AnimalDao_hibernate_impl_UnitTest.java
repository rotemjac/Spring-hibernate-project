package my.pack.test.unitTest;

import my.pack.dataAccessTier.dao.facilities.AnimalDao_hibernate_impl;

import org.junit.Test;

public class AnimalDao_hibernate_impl_UnitTest {
	
	AnimalDao_hibernate_impl andao;
	
	public AnimalDao_hibernate_impl_UnitTest() {
		andao=new AnimalDao_hibernate_impl();
	}
	
/*	@Test
	public void testCreate() {
		
		Animal elephant3=new Animal
				("elephant_3","Mammal","Proboscidea","Elephant",
				 "African bush",'F',0,0,"weed",'N');
		andao.create(elephant3);
	}

	@Test
	public void testReadById() {
		andao.readById("elephant_3");
	}

	@Test
	public void testGetAllAnimals() {
		andao.getAllAnimals();
	}

	@Test
	public void testGet_unchecked_animals() {
		andao.get_unchecked_animals();
	}

	@Test
	public void testUpdate() {
		//I'm am not using this function.
		}

	@Test
	public void testDelete() {
		Animal elephant3=new Animal
				("elephant_3","Mammal","Proboscidea","Elephant",
				 "African bush",'F',0,0,"weed",'N');
		andao.delete(elephant3);
	}

	@Test
	public void testReadByCategory() {
		andao.readByCategory("Mammal");
	}

	@Test
	public void testReadByVet() {
		andao.readByVet("Ariel", "sofer");
	}

	@Test
	public void testUpdateAreaNum() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateFood() {
		fail("Not yet implemented");
	}

	@Test
	public void testMark_animal_that_need_vet_check() {
		andao.mark_animal_that_need_vet_check("croc_1", 'Y');
	}
*/

	@Test
	public void testRead_only_groups_by_category() {
		andao.read_only_groups_by_category("Department");
	}

	@Test
	public void testGetOnlyAnimalsIds() {
		andao.getOnlyAnimalsIds();
	}

}
