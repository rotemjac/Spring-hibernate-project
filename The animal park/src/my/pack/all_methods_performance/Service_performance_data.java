package my.pack.all_methods_performance;

import java.util.Set;

public interface Service_performance_data {
	
		//Being used at park creation
		public void Create(Performance_data pd);
		
		//Being used at web tier to display table
		public Set<Method_performance> GetAllMethodsData();
		
		//Being used at AOP
		public void AddMeasureToMethodData(String method_name,double time);
		
		//Being used at ?
	    public void ResetMethodData(String method_name);
}
