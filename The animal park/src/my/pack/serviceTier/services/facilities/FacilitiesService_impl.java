package my.pack.serviceTier.services.facilities;

import java.util.LinkedList;
import java.util.List;

import my.pack.dataAccessTier.dao.facilities.FacilitiesDao;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Bathroom;
import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="FacilitiesService")
public class FacilitiesService_impl implements FacilitiesService {

	@Autowired
	//@Qualifier("${DataAccess.FacilitiesDao}")
	@Qualifier("FacilitiesDao")
	private FacilitiesDao facDao;


	@Autowired
	@Qualifier("AreaService")
	private AreaService areaService;

	//Constructor
	public FacilitiesService_impl() {
		System.out.println("Stop here for debug");
	}
	
	/**
	 * General Facilities part
	 */
	
	//Creation methods requiers the highest isolation level
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@Override
	public void CreateFacilities(Facilities fac) {
		facDao.create(fac);
	}

	@Transactional(readOnly=true)
	@Override
	public Facilities GetFacilities() {
		return facDao.get();
	}

	@Transactional()
	@Override
	public void UpdateFacilities(Facilities fac) {
		facDao.update(fac);
	}
	
	@Transactional()
	@Override
	public void DeleteFacilities(Facilities fac) {
		facDao.delete(fac);
	}
	
	/**
	 * Logistic Facilities part
	 */

	//Creation methods requiers the highest isolation level
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@Override
	public Logistic_facilities Create_Log_Fac(int gate_num,int camera_num, int fence_num) {

		Logistic_facilities log_fac=new Logistic_facilities();
		Create_Many_Sub_Facilities(log_fac,gate_num,camera_num, fence_num);

		return log_fac;
	}

	//Creation methods requiers the highest isolation level
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@Override
	public void Create_Many_Sub_Facilities
	(Logistic_facilities log_fac,
			int gate_num,int camera_num, int fence_num) {

		List<Gate> gates=new LinkedList<Gate>();
		List<Camera> cameras=new LinkedList<Camera>();
		List<Fence> fences=new LinkedList<Fence>();

		for (int i=1;i<=gate_num;i++) {
			gates.add( new Gate("gate" + i, 'Y' ) );
		}

		for (int i=1;i<=camera_num;i++) {
			cameras.add( new Camera("camera" + i) );
		}

		for (int i=1;i<=fence_num;i++) {
			fences.add( new Fence("fence" + i) );
		}

		log_fac.setGates(gates);
		log_fac.setCameras(cameras);
		log_fac.setFences(fences);

	}
	
	@Transactional(readOnly=true)
	@Override
	public Logistic_facilities Get_Log_fac_with_cameras() {
		return facDao.getLog_fac_with_cameras();
	}

	@Transactional(readOnly=true)
	@Override
	public Logistic_facilities Get_Log_fac_with_gates() {
		return facDao.getLog_fac_with_gates();
	}

	@Transactional(readOnly=true)
	@Override
	public Logistic_facilities Get_Log_fac_with_fences() {
		return facDao.getLog_fac_with_fences();
	}


	/**
	 * Areas part in Logistic Facilities
	 */
	
	@Override
	public AreaService getAreaService() {
		return areaService;
	}

	@Transactional()
	@Override
	public void Add_Areas_To_Log_Fac(List<Area> areas) {
		facDao.add_areas_to_log_fac(areas);		
	}

	@Transactional()
	//Total sessions for this method:	2
	@Override
	public void Add_Bathroom_To_Area(String area_num, int number_of_bathrooms) {

		Area area=areaService.GetAreaByNumber(area_num);//Session Opened
		
		////Creating the bathroom list
		List<Bathroom> bath_list=new LinkedList<>();

		for(int i=0; i<number_of_bathrooms; i++) {
			bath_list.add(new Bathroom("bathroom" + i, 'Y'));
		}
		////


		area.setBathrooms(bath_list);

		areaService.UpdateArea(area);//Session Opened
	}

	
	
	/**
	 * Economic Facilities part
	 */
	@Transactional()
	@Override
	public void Add_Or_Sub_Ticket(char oper) {
		facDao.add_or_sub_Ticket(oper);
	}

	@Transactional(readOnly=true)
	@Override
	public int Get_Number_of_Tickets_Sold() {
		return facDao.get_number_of_tickets_sold();
	}

	@Transactional()
	@Override
	public void AddOrSubPeopleOut(char oper) {
		facDao.add_or_sub_people_out(oper);		
	}

	@Transactional(readOnly=true)
	@Override
	public int GetPeopleInside() {
		return facDao.get_people_inside();
	}




	




}
