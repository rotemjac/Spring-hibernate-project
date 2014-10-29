package my.pack.serviceTier.services.workers;

public interface CaregiverService extends WorkersService {
	
	public void SetProblemToLandPortion(String problem,String area_num, String lp_num);
	public void MarkAnimalThatNeedVetCheck(String id,char need_check);

}
