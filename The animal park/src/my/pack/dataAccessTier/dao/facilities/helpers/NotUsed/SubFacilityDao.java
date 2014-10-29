package my.pack.dataAccessTier.dao.facilities.helpers.NotUsed;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.subfacilities.Sub_Facility;

public interface SubFacilityDao {
	
	public void create(Sub_Facility sub_facility);
	public void create_many(String which_sub_fac,int num);
	public Sub_Facility read(String id);
	public void update(Sub_Facility sub_facility);
	public void delete(Sub_Facility sub_facility);
	public List<Sub_Facility> getAll(String the_chosen_sub_fac);
}
