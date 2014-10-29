package my.pack.advices;

import my.pack.advices.photos.Window_with_photos;
import my.pack.serviceTier.services.workers.VetService;
import my.pack.utils.Config_File;
import my.pack.webTier.clients.MainGuis.Main_menu;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Services_Advices {
	
	//Not working like this:"Cant find refrened pointcut"
	//@Pointcut() 
	//public void animals_created(){}

	Main_menu mm;

	
	
/*	@After("execution(* my.pack.webTier.clients.creation.Client_Creating_Animals.create_the_animals(..))‬")
	public void show_photo() {
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {			
				try {
					Thread.sleep(5000);
				} 
				catch (InterruptedException e) 
				{e.printStackTrace();}
			}
		});
		
		t1.start();
		try {t1.join();} 
		catch (InterruptedException e) 
		{e.printStackTrace();}
		
	}*/

	@After
	("execution(* my.pack.serviceTier.services.facilities.AreaService_impl.*(..))" +
			"|| execution(* my.pack.serviceTier.services.facilities.FacilitiesService_impl.*(..))‬" +
		    "&& !execution(* my.pack.serviceTier.services.facilities.FacilitiesService_impl.getAreaService(..))")
	
	public void seperate_transaction() {
		for (int row=0;row<3;row++) {
			for (int column=0;column<50;column++) {
				System.out.print("*");
				if (row==1&&column==25) {System.out.print("Transaction Finished!!");}
				else if (row==0||row==2) {System.out.print("*");}
			}
			System.out.println(" ");
		}
	}

}
