package my.pack.dataAccessTier.dao.facilities;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Animal;

public interface AnimalDao {
	
	public void create(Animal animal);
	public Animal readById(String id);
	public List<Animal> getAllAnimals();
	public List<String> getOnlyAnimalsIds();
	public List<Animal> get_unchecked_animals();
	public void update(Animal animal);
	public void delete(Animal animal);
	
	public List<Animal> readByCategory(String category);
	public List<String> read_only_groups_by_category(String category);
	public List<Animal> readByVet(String first_name,String last_name);
	public void updateAreaNum(String category,String area_num);
	public void updateFood(String category,String food);
	public void mark_animal_that_need_vet_check(String id,char need_check);
}
