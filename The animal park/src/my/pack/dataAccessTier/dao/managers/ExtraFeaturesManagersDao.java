package my.pack.dataAccessTier.dao.managers;

import java.util.List;

import my.pack.dataAccessTier.domain.workers.Guard;

public interface ExtraFeaturesManagersDao {
	
	/**
	 * Security chief part
	 */
	public List<Object[]> view_and_setup_weapon_license_num(); 
	public void set_weapon_num(String guard_id, long value);

}
