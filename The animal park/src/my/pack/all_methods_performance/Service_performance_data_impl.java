package my.pack.all_methods_performance;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="Service_performance_data")
public class Service_performance_data_impl implements Service_performance_data {

	@Autowired
	@Qualifier("Dao_performance_data")
	private Dao_performance_data pd_dao;
	
	@Override
	public void Create(Performance_data pd) {
		pd_dao.create(pd);
	}

	@Override
	public Set<Method_performance> GetAllMethodsData() {
		return pd_dao.get_all_methods_data();
	}

	@Override
	public void AddMeasureToMethodData(String method_name,double time) {
		pd_dao.add_measure_to_method_data(method_name, time);
	}

	@Override
	public void ResetMethodData(String method_name) {
		pd_dao.reset_method_data(method_name);
	}

}
