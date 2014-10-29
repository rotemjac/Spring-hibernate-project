package my.pack.serviceTier.services.topManagers;

import java.util.List;

import my.pack.dataAccessTier.dao.managers.ExtraFeaturesManagersDao;
import my.pack.dataAccessTier.dao.managers.ManagerDao;
import my.pack.dataAccessTier.domain.superclasses.Manager;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.workers.Guard;
import my.pack.serviceTier.services.workers.WorkersService_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="ManagerService")
public class ManagerService_impl extends WorkersService_impl 
				implements ManagerGeneralService, ManagersExtraFeaturesService {

	@Autowired
	//@Qualifier("${DataAccess.ManagerDao}")
	@Qualifier("ManagerDao")
	private ManagerDao managerDao;
	
	@Autowired
	//@Qualifier("${DataAccess.ExtraFeaturesManagersDao}")
	@Qualifier("ExtraFeaturesManagersDao")
	private	ExtraFeaturesManagersDao efManagersDao;

	/**
	 * "All Managers" part
	 */
	
	@Override
	public void CreateManager(Manager man, List<Worker> worker_list) {
		managerDao.create_manager(man, worker_list);	
	}
	
	@Override
	public void AddWorkerToStaff(String man_position, Worker worker) {
		managerDao.addWorker(man_position, worker);
	}


	@Override
	public List<Worker> GetYourStaff(String position) {
				return managerDao.getYourStaff(position);
	}

	@Override
	public List<Object[]> GetYourStaffPartly(String position) {
				return managerDao.getYourStaff_partly(position);
	}

	/**
	 * "Specific to a certain manager" part
	 */
	
	//Method of security chief
	@Override
	public List<Object[]> ViewAndSetupWeaponLicenseNum() {
		return efManagersDao.view_and_setup_weapon_license_num();
	}


	@Override
	public void SetWeaponNumber(String guard_id, long value) {
		efManagersDao.set_weapon_num(guard_id, value);	
	}






}
