package my.pack.serviceTier.services.topManagers;

import java.util.List;

import my.pack.dataAccessTier.domain.workers.Guard;

public interface ManagersExtraFeaturesService {
	
	//For Security chief
	public List<Object[]> ViewAndSetupWeaponLicenseNum(); 
	public void SetWeaponNumber(String guard_id, long value);

}
