package my.pack.serviceTier.services.workers;

import my.pack.serviceTier.services.facilities.FilterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="GuardService")
public class GuardService_impl extends WorkersService_impl
								 implements GuardService {

	@Autowired
	private FilterService filterServ;

	@Override
	public void Add_Or_Sub_People_Out(char oper) {
		filterServ.AddOrSubPeopleOut(oper);
	}
	


}
