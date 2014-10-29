package my.pack.dataAccessTier.dao.facilities;

import java.util.List;

import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Sub_Facility;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;

public interface FacilitiesDao {
	
	public void create(Facilities fac);
	public Facilities get();
	public void update(Facilities fac);
	public void delete(Facilities fac);

	public Logistic_facilities getLog_fac_with_cameras();
	public Logistic_facilities getLog_fac_with_gates();
	public Logistic_facilities getLog_fac_with_fences();

	public void add_areas_to_log_fac(List<Area> areas);
	
	public void add_or_sub_Ticket(char oper);
	public int get_number_of_tickets_sold();
	public void add_or_sub_people_out(char oper);
	public int get_people_inside();

}
