package my.pack.serviceTier.services.topManagers;

import java.util.List;

import my.pack.dataAccessTier.dao.managers.CEODao;
import my.pack.dataAccessTier.dao.managers.ManagerDao;
import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.topmanagers.CEO;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;
import my.pack.serviceTier.services.facilities.FacilitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="CeoService")
public class CEOService_impl extends ManagerService_impl 

		implements CEOService {

	@Autowired
	//@Qualifier("${DataAccess.CEODao}")
	@Qualifier("CEODao")
	private CEODao ceoDao;
	
	
	@Autowired
	@Qualifier("FacilitiesService")
	private FacilitiesService fac_service;

	
	@Override
	public void CreateCEO(CEO ceo, List<Manager> mng_list) {
		ceoDao.createCeo(ceo, mng_list);		
	}
	
	@Override
	public void AddSubManager(CEO ceo,Manager mng) {
		ceoDao.addSubManager(mng);
	}
	
	@Override
	public List<Manager> GetAllManagers() {
		return ceoDao.getAllManagers();
	}
	
	@Override
	public List<String> GetAllPositions() {
		return ceoDao.getAllPositions();
	}

	@Override
	public void CreateFacilities(Facilities fac) {
		fac_service.CreateFacilities(fac);
	}
	
	@Override
	public void SetFacilities(Facilities fac) {
		ceoDao.setFacilities(fac);	
	}


	@Override
	public List<Worker> GetAllWorkers() {
		return ceoDao.getAllWorkers();
	}

	@Override
	public Facilities GetFacilities() {
			
		return ceoDao.getFacilities();
	}

	@Override
	public void FireWorker(String id) {
		ceoDao.delete_worker(id);		
	}

}
