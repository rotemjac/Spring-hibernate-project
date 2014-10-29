package my.pack.all_methods_performance;

import javax.persistence.Column;
import javax.persistence.Embeddable;


//@Embeddable
public class Method_performance implements Comparable<Method_performance>{

	@Column(name="name_of_method",nullable = true)
	private String name_of_method;

	@Column(name="best_time")
	private double time_1;

	@Column(name="second_time")
	private double time_2;

	@Column(name="third_time")
	private double time_3;

	@Column(name="fourth_time")
	private double time_4;

	@Column(name="fifth_time")
	private double time_5;

	@Column(name="average_time")
	private double average_time;


	/**
	 * constructor
	 */
	
	public Method_performance () {}
	
	public Method_performance(String name,int t1,int t2,int t3,int t4,int t5){
		this.name_of_method=name;
		this.time_1=t1;
		this.time_2=t2;
		this.time_3=t3;
		this.time_4=t4;
		this.time_5=t5;

		set_average();
	}

	
	/**
	 * methods-general
	 */

	public void set_average() {
		this.average_time=		
			   ( this.time_1+
				 this.time_2+
				 this.time_3+
				 this.time_4+
				 this.time_5 )/5;
	}


	//If the measured time is in the top 5, insert it in the right place
	public void add_measure(double time) {
		
		if (time<time_1 && time!=0) {
			time_5=time_4;
			time_4=time_3;
			time_3=time_2;
			time_2=time_1;
			time_1=time;
		}
		
		else if (time<time_2 && time!=0) {
			time_5=time_4;
			time_4=time_3;
			time_3=time_2;
			time_2=time;

		}
		
		else if (time<time_3 && time!=0) {
			time_5=time_4;
			time_4=time_3;
			time_3=time;
		}
		
		else if (time<time_4 && time!=0) {
			time_5=time_4;
			time_4=time;
		}
		
		else if (time<time_5 && time!=0) {
			time_5=time;
		}
		
	}
	
	
	public void reset_data() {
		 this.time_1=0;
		 this.time_2=0;
		 this.time_3=0;
		 this.time_4=0;
		 this.time_5=0;
		 set_average();
	}
	
	/**
	 * methods-getters & setters
	 */

	public String getName_of_method() {
		return name_of_method;
	}
	public void setName_of_method(String name_of_method) {
		this.name_of_method = name_of_method;
	}
	public double getTime_1() {
		return time_1;
	}
	public void setTime_1(double time_1) {
		this.time_1 = time_1;
	}
	public double getTime_2() {
		return time_2;
	}
	public void setTime_2(double time_2) {
		this.time_2 = time_2;
	}
	public double getTime_3() {
		return time_3;
	}
	public void setTime_3(double time_3) {
		this.time_3 = time_3;
	}
	public double getTime_4() {
		return time_4;
	}
	public void setTime_4(double time_4) {
		this.time_4 = time_4;
	}
	public double getTime_5() {
		return time_5;
	}
	public void setTime_5(double time_5) {
		this.time_5 = time_5;
	}
	public double getAverage_time() {
		return average_time;
	}
	public void setAverage_time(double average_time) {
		this.average_time = average_time;
	}

	@Override
	public int compareTo(Method_performance mp) {
		//Returns: 
		//-1 when: this.average_time < mp.average_time
		// 0 when: this.average_time = mp.average_time	
		// 1 when: this.average_time > mp.average_time	
	return	( Double.compare(this.average_time, mp.average_time) );		
	}
	



}
