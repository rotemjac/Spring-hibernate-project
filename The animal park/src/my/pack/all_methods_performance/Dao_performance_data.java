package my.pack.all_methods_performance;

import java.util.Set;

public interface Dao_performance_data{
	
	public void create(Performance_data pd);
	public Set<Method_performance> get_all_methods_data();
	public void add_measure_to_method_data(String method_name,double time);
    public void reset_method_data(String method_name);
}
