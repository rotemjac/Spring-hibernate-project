package my.pack.advices;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class Monitoring_Advices {

	private boolean enabled = true;

	private int callCount = 0;

	private long accumulatedCallTime = 0;


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void reset() {
		this.callCount = 0;
		this.accumulatedCallTime = 0;
	}

	public int getCallCount() {
		return callCount;
	}

	public long getCallTime() {
		return (this.callCount > 0 ? this.accumulatedCallTime / this.callCount : 0);
	}


	@Around("execution(* my.pack.serviceTier.services.facilities.AreaService_impl.*(..))")
	///("execution(* my.pack.serviceTier.services.facilities.*(..))")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {

		

		if (this.enabled) {
			StopWatch sw = new StopWatch(joinPoint.toShortString());

			//Getting the method string
			///////////////////////////////////////////////////////////////
			get_the_performed_method(joinPoint);
			///////////////////////////////////////////////////////////////
			
			sw.start("invoke");
			try {return joinPoint.proceed();} 

			finally {
				sw.stop();
				synchronized (this) {
					this.callCount++;
					//this.accumulatedCallTime += sw.getTotalTimeMillis();
					this.accumulatedCallTime = sw.getTotalTimeMillis();
					//Switch to seconds
					this.accumulatedCallTime=this.accumulatedCallTime/100;
				}
				System.out.println
				("Total time for method: "+this.accumulatedCallTime);
			}
		} 

		else { return joinPoint.proceed();}

	}//End of around

	
	private String get_the_performed_method(ProceedingJoinPoint joinPoint) {
		
		int start_point = 0,end_point=0;
		String current_method= joinPoint.toShortString();
		
		for (int index=0;index<current_method.length();index++) {
			if (current_method.charAt(index)=='(') {
				start_point=index+1;
				break;
			}
		}
		
		end_point=current_method.length()-3;
		
		 current_method=current_method.substring(start_point, end_point);
		 
		 return current_method;
	}
	
}
