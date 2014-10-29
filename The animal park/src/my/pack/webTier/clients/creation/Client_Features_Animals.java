package my.pack.webTier.clients.creation;

import my.pack.serviceTier.services.facilities.FacilitiesService;

public class Client_Features_Animals {
	
	public Client_Features_Animals(FacilitiesService fac_service) {
		
			fac_service.getAreaService().UpdateAnimalAreaNum("Aves", "1");
			/*	
	fac_service.getAreaService().UpdateAnimalAreaNum("Reptilia", "2");
	fac_service.getAreaService().UpdateAnimalAreaNum("Primates", "3");
	fac_service.getAreaService().UpdateAnimalAreaNum("Carnivores", "4");
	fac_service.getAreaService().UpdateAnimalAreaNum("Sloth", "4");
	fac_service.getAreaService().UpdateAnimalAreaNum("Perissodactyla", "5");
	fac_service.getAreaService().UpdateAnimalAreaNum("Artiodactyla", "5");
	fac_service.getAreaService().UpdateAnimalAreaNum("Elephant", "5");
	fac_service.getAreaService().UpdateAnimalAreaNum("Lion", "6");*/
	
	fac_service.getAreaService().SetVetOfArea(1, "Shaul", "simhoni");
	fac_service.getAreaService().SetVetOfArea(2, "Asaf", "vais");
	fac_service.getAreaService().SetVetOfArea(3, "Ariel", "sofer");
	fac_service.getAreaService().SetVetOfArea(4, "Shaul", "simhoni");
	fac_service.getAreaService().SetVetOfArea(5, "Asaf", "vais");
	fac_service.getAreaService().SetVetOfArea(6, "Ariel", "sofer");
	
	fac_service.getAreaService().UpdateAnimalFood("Carnivores", "beef");
	
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("ost_1", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("her_1", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("croc_2", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("komodo_2", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("capuchin_1", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("gorilla_2", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("wolf_1", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("sloth_1", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("lion_1", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("zebra_2", 'Y');
	fac_service.getAreaService().MarkAnimalThatNeedVetCheck("ganu_1", 'Y');
	}

}
