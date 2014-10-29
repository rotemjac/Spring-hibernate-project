package my.pack.webTier.clients.creation;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.serviceTier.services.facilities.FacilitiesService;

@Component(value="Client_Creating_Animals")
public class Client_Creating_Animals {

	@Autowired
	@Qualifier("FacilitiesService")
	FacilitiesService fac_service;
	
	public Client_Creating_Animals() {}
	
	
	public void create_the_animals() {
		/**
		 * Creating Animals
		 */
		
		Animal ost1=new Animal("ost_1","Aves","Struthioniformes","Struthio","Ostrich",'M',30,100,"weed",'N');
		Animal ost2=new Animal("ost_2","Aves","Struthioniformes","Struthio","Ostrich",'F',24,80,"weed",'N');
		fac_service.getAreaService().CreateAnimal(ost1);
		fac_service.getAreaService().CreateAnimal(ost2);

		Animal pelican1=new Animal("pel1","Aves","Pelecaniformes","Pelican","Great_White",'M',10,4,"fish",'N');
		Animal pelican2=new Animal("pel2","Aves","Pelecaniformes","Pelican","Great_White",'F',8,5,"fish",'N');
		fac_service.getAreaService().CreateAnimal(pelican1);
		fac_service.getAreaService().CreateAnimal(pelican2);

		Animal heron1=new Animal("her_1","Aves","Pelecaniformes","Night Heron","Japanese White",'M',6,1,"fish",'N');
		Animal heron2=new Animal("her_2","Aves","Pelecaniformes","Night Heron","Japanese White",'F',7,2,"fish",'N');
		fac_service.getAreaService().CreateAnimal(heron1);
		fac_service.getAreaService().CreateAnimal(heron2);

		Animal peacock1=new Animal("peacock_1","Aves","Galliformes","Peacock","Indian Peafowl",'M',15,5,"vegetable",'N');
		Animal peacock2=new Animal("peacock_2","Aves","Galliformes","Peacock","Indian Peafowl",'F',13,4,"vegetable",'N');
		fac_service.getAreaService().CreateAnimal(peacock1);
		fac_service.getAreaService().CreateAnimal(peacock2);

		Animal tortoise1=new Animal("tortoise_1","Reptilia","Testudo","Tortoise","Russian tortoise",'M',28,3,"vegetable",'N');
		Animal tortoise2=new Animal("tortoise_2","Reptilia","Testudo","Tortoise","Russian tortoise",'F',25,4,"vegetable",'N');
		fac_service.getAreaService().CreateAnimal(tortoise1);
		fac_service.getAreaService().CreateAnimal(tortoise2);

		Animal crocodile1=new Animal("croc_1","Reptilia","Crocodylia","Crocodile","Salt-water",'M',60,2200,"fish",'N');
		Animal crocodile2=new Animal("croc_2","Reptilia","Crocodylia","Crocodile","Salt-water",'F',50,2000,"fish",'N');
		fac_service.getAreaService().CreateAnimal(crocodile1);
		fac_service.getAreaService().CreateAnimal(crocodile2);

		Animal iguana1=new Animal("iguana_1","Reptilia","Squamata","Iguana","Green iguana",'M',12,7,"leaves",'N');
		Animal iguana2=new Animal("iguana_2","Reptilia","Squamata","Iguana","Green iguana",'F',10,8,"leaves",'N');		
		fac_service.getAreaService().CreateAnimal(iguana1);
		fac_service.getAreaService().CreateAnimal(iguana2);

		Animal komodo1=new Animal("komodo_1","Reptilia","Squamata","Varanus","Komodo dragon",'M',32,70,"meat",'N');
		Animal komodo2=new Animal("komodo_2","Reptilia","Squamata","Varanus","Komodo dragon",'F',27,60,"meat",'N');
		fac_service.getAreaService().CreateAnimal(komodo1);
		fac_service.getAreaService().CreateAnimal(komodo2);

		Animal capuchin1=new Animal("capuchin_1","Mammal","Primates","Capuchin","Ecuadorian capuchin",'M',7,5,"leaves",'N');
		Animal capuchin2=new Animal("capuchin_2","Mammal","Primates","Capuchin","Ecuadorian capuchin",'F',5,6,"leaves",'N');
		fac_service.getAreaService().CreateAnimal(capuchin1);
		fac_service.getAreaService().CreateAnimal(capuchin2);

		Animal orangutan1=new Animal("orangutan_1","Mammal","Primates","Orangutan","Bornean Orangutan",'M',29,115,"leaves",'N');
		Animal orangutan2=new Animal("orangutan_2","Mammal","Primates","Orangutan","Bornean Orangutan",'F',24,100,"leaves",'N');
		fac_service.getAreaService().CreateAnimal(orangutan1);
		fac_service.getAreaService().CreateAnimal(orangutan2);

		Animal chimpanzee1=new Animal("chimpanzee_1","Mammal","Primates","Chimpanzee","Western chimpanzee",'M',43,70,"leaves",'N');
		Animal chimpanzee2=new Animal("chimpanzee_2","Mammal","Primates","Chimpanzee","Western chimpanzee",'F',37,64,"leaves",'N');	
		fac_service.getAreaService().CreateAnimal(chimpanzee1);
		fac_service.getAreaService().CreateAnimal(chimpanzee2);

		Animal gorilla1=new Animal("gorilla_1","Mammal","Primates","Gorilla","G.gorilla",'M',7,150,"leaves",'N');
		Animal gorilla2=new Animal("gorilla_2","Mammal","Primates","Gorilla","G.gorilla",'F',5,130,"leaves",'N');
		fac_service.getAreaService().CreateAnimal(gorilla1);
		fac_service.getAreaService().CreateAnimal(gorilla2);

		Animal lion1=new Animal("lion_1","Mammal","Carnivores","Panthera","lion",'M',0,0,"meat",'N');
		Animal lion2=new Animal("lion_2","Mammal","Carnivores","Panthera","lion",'F',0,0,"meat",'N');
		fac_service.getAreaService().CreateAnimal(lion1);
		fac_service.getAreaService().CreateAnimal(lion2);

		Animal wolf1=new Animal("wolf_1","Mammal","Carnivores","Canis","wolf",'M',0,0,"meat",'N');
		Animal wolf2=new Animal("wolf_2","Mammal","Carnivores","Canis","wolf",'F',0,0,"meat",'N');	
		fac_service.getAreaService().CreateAnimal(wolf1);
		fac_service.getAreaService().CreateAnimal(wolf2);

		Animal sand_cat1=new Animal("sand_cat_1","Mammal","Carnivores","Felis","Sand cat",'M',0,0,"meat",'N');
		Animal sand_cat2=new Animal("sand_cat_2","Mammal","Carnivores","Felis","Sand cat",'F',0,0,"meat",'N');		
		fac_service.getAreaService().CreateAnimal(sand_cat1);
		fac_service.getAreaService().CreateAnimal(sand_cat2);

		Animal leopard1=new Animal("leopard_1","Mammal","Carnivores","Panthera","Leopard",'M',0,0,"meat",'N');
		Animal leopard2=new Animal("leopard_2","Mammal","Carnivores","Panthera","Leopard",'F',0,0,"meat",'N');	
		fac_service.getAreaService().CreateAnimal(leopard1);
		fac_service.getAreaService().CreateAnimal(leopard2);

		Animal zebra1=new Animal("zebra_1","Mammal","Perissodactyla","Equus","Zebra",'M',0,0,"weed",'N');
		Animal zebra2=new Animal("zebra_2","Mammal","Perissodactyla","Equus","Zebra",'F',0,0,"weed",'N');
		fac_service.getAreaService().CreateAnimal(zebra1);
		fac_service.getAreaService().CreateAnimal(zebra2);

		Animal rhinoceros1=new Animal("rhinoceros_1","Mammal","Perissodactyla","rhinoceros","White rhinoceros",'M',0,0,"weed",'N');
		Animal rhinoceros2=new Animal("rhinoceros_2","Mammal","Perissodactyla","rhinoceros","White rhinoceros",'F',0,0,"weed",'N');	 
		fac_service.getAreaService().CreateAnimal(rhinoceros1);
		fac_service.getAreaService().CreateAnimal(rhinoceros2);

		Animal hippopotamus1=new Animal("Hippopotamus_1","Mammal","Artiodactyla","Hippopotamus","H.amphibius",'M',0,0,"weed",'N');
		Animal hippopotamus2=new Animal("Hippopotamus_2","Mammal","Artiodactyla","Hippopotamus","H.amphibiuss",'F',0,0,"weed",'N');
		fac_service.getAreaService().CreateAnimal(hippopotamus1);
		fac_service.getAreaService().CreateAnimal(hippopotamus2);

		Animal ganu1=new Animal("ganu_1","Mammal","Artiodactyla","Connochaetes","Ganu",'M',0,0,"weed",'N');
		Animal ganu2=new Animal("ganu_2","Mammal","Artiodactyla","Connochaetes","Ganu",'F',0,0,"weed",'N');
		fac_service.getAreaService().CreateAnimal(ganu1);
		fac_service.getAreaService().CreateAnimal(ganu2);

		Animal giraffa1=new Animal("giraffa_1","Mammal","Artiodactyla","Giraffa","G.camelopardalis",'M',0,0,"weed",'N');
		Animal giraffa2=new Animal("giraffa_2","Mammal","Artiodactyla","Giraffa","G.camelopardalis",'F',0,0,"weed",'N');
		fac_service.getAreaService().CreateAnimal(giraffa1);
		fac_service.getAreaService().CreateAnimal(giraffa2);

		Animal elephant1=new Animal("elephant_1","Mammal","Proboscidea","Elephant","African bush",'M',0,0,"weed",'N');
		Animal elephant2=new Animal("elephant_2","Mammal","Proboscidea","Elephant","African bush",'F',0,0,"weed",'N');
		fac_service.getAreaService().CreateAnimal(elephant1);
		fac_service.getAreaService().CreateAnimal(elephant2);

		Animal sloth1=new Animal("sloth_1","Mammal","Pilosa","Sloth","Three-toed",'M',0,0,"weed",'N');
		Animal sloth2=new Animal("sloth_2","Mammal","Pilosa","Sloth","Three-toed",'F',0,0,"weed",'N'); 
		fac_service.getAreaService().CreateAnimal(sloth1);
		fac_service.getAreaService().CreateAnimal(sloth2);

		/**
		 * Adding animals to areas
		 */	

		fac_service.getAreaService().PopulateAnimalByCategory
		("Aves", fac_service.getAreaService().GetAreaByNumber_With_Animals("1"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("Reptilia", fac_service.getAreaService().GetAreaByNumber_With_Animals("2"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("Primates", fac_service.getAreaService().GetAreaByNumber_With_Animals("3"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("Carnivores", fac_service.getAreaService().GetAreaByNumber_With_Animals("4"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("Sloth", fac_service.getAreaService().GetAreaByNumber_With_Animals("4"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("Zebra", fac_service.getAreaService().GetAreaByNumber_With_Animals("5"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("rhinoceros", fac_service.getAreaService().GetAreaByNumber_With_Animals("5"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("Hippopotamus", fac_service.getAreaService().GetAreaByNumber_With_Animals("5"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("Ganu", fac_service.getAreaService().GetAreaByNumber_With_Animals("5"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("Giraffa", fac_service.getAreaService().GetAreaByNumber_With_Animals("5"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("Elephant", fac_service.getAreaService().GetAreaByNumber_With_Animals("5"));

		fac_service.getAreaService().PopulateAnimalByCategory
		("lion", fac_service.getAreaService().GetAreaByNumber_With_Animals("6"));


		/**
		 * Connecting the animals id's to land portions
		 */
		
		Stack<String> area_1_animals_by_order=new Stack<String>();
		area_1_animals_by_order.push("ostrich");
		area_1_animals_by_order.push("Pelican");
		area_1_animals_by_order.push("Night Heron");
		area_1_animals_by_order.push("peacock");

		Stack<String> area_2_animals_by_order=new Stack<String>();
		area_2_animals_by_order.push("Russian tortoise");
		area_2_animals_by_order.push("Salt-water Crocodylus");
		area_2_animals_by_order.push("Green iguana");
		area_2_animals_by_order.push("Komodo dragon");

		Stack<String> area_3_animals_by_order=new Stack<String>();
		area_3_animals_by_order.push("Ecuadorian capuchin");
		area_3_animals_by_order.push("Bornean Orangutan");
		area_3_animals_by_order.push("Western chimpanzee ");
		area_3_animals_by_order.push("Gorilla");

		Stack<String> area_4_animals_by_order=new Stack<String>();
		area_4_animals_by_order.push("Gray wolf");
		area_4_animals_by_order.push("Sand cat");
		area_4_animals_by_order.push("Leopard");
		area_4_animals_by_order.push("Sloth");

		fac_service.getAreaService().PutAnimals_In_LandPortion("1", area_1_animals_by_order);
		fac_service.getAreaService().PutAnimals_In_LandPortion("2", area_2_animals_by_order);
		fac_service.getAreaService().PutAnimals_In_LandPortion("3", area_3_animals_by_order);
		fac_service.getAreaService().PutAnimals_In_LandPortion("4", area_4_animals_by_order);


	}		



}
