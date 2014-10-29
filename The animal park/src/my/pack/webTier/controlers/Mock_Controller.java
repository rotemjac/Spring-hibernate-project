package my.pack.webTier.controlers;

import my.pack.all_methods_performance.Service_performance_data;
import my.pack.serviceTier.services.facilities.FacilitiesService;
import my.pack.serviceTier.services.subManagers.Security_chief_Service;
import my.pack.serviceTier.services.subManagers.TL_caregiver_Service;
import my.pack.serviceTier.services.subManagers.TL_casheer_Service;
import my.pack.serviceTier.services.subManagers.TL_cleaner_Service;
import my.pack.serviceTier.services.subManagers.TL_janitor_Service;
import my.pack.serviceTier.services.subManagers.Vet_chief_Service;
import my.pack.serviceTier.services.topManagers.CEOService;
import my.pack.serviceTier.services.topManagers.ManagerGeneralService;
import my.pack.serviceTier.services.workers.CaregiverService;
import my.pack.serviceTier.services.workers.CasheerService;
import my.pack.serviceTier.services.workers.GuardService;
import my.pack.serviceTier.services.workers.VetService;
import my.pack.serviceTier.services.workers.WorkersService;
import my.pack.utils.Config_File;
import my.pack.webTier.clients.creation.Client_Creating_Animals;

public class Mock_Controller {

	private static Mock_Controller obj;
	
	private CEOService ceoService;
	private ManagerGeneralService managerService;
	//There was no need to add the ManagersExtraFeaturesService
	private WorkersService workerService;
	
	private FacilitiesService facService;

	private TL_casheer_Service tl_casheer_Service;
	private TL_cleaner_Service tl_cleaner_Service;
	private TL_janitor_Service tl_janitor_Service;
	private TL_caregiver_Service tl_caregiver_Service;
	private Security_chief_Service sec_chief_Service;
	private Vet_chief_Service vet_chief_Service;

	private CasheerService csrService;
	private CaregiverService caregiverService;
	private VetService vetService;
	private GuardService guardService;
	
	private Service_performance_data pd_service;
	
	private Client_Creating_Animals client_creating_animals;

	private Mock_Controller() {
		
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				ceoService=(CEOService) Config_File.getContainer().getBean("CeoService");
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				managerService=(ManagerGeneralService) Config_File.getContainer().getBean("ManagerService");
			}
		});
		
		Thread t3=new Thread(new Runnable() {
			@Override
			public void run() {
				workerService=(WorkersService) Config_File.getContainer().getBean("WorkersService");
			}
		});
		
		Thread t4=new Thread(new Runnable() {
			@Override
			public void run() {
				facService=(FacilitiesService)Config_File.getContainer().getBean("FacilitiesService");
			}
		});
		
		Thread t5=new Thread(new Runnable() {
			@Override
			public void run() {
				tl_casheer_Service=(TL_casheer_Service)Config_File.getContainer().getBean("TL_casheer_Service");
			}
		});
		
		Thread t6=new Thread(new Runnable() {
			@Override
			public void run() {
				tl_cleaner_Service=(TL_cleaner_Service)Config_File.getContainer().getBean("TL_cleaner_Service");
			}
		});
		
		Thread t7=new Thread(new Runnable() {
			@Override
			public void run() {
				tl_janitor_Service=(TL_janitor_Service)Config_File.getContainer().getBean("TL_janitor_Service");
			}
		});
		
		Thread t8=new Thread(new Runnable() {
			@Override
			public void run() {
				tl_caregiver_Service=(TL_caregiver_Service)Config_File.getContainer().getBean("TL_caregiver_Service");
			}
		});
		
		Thread t9=new Thread(new Runnable() {
			@Override
			public void run() {
				vet_chief_Service=(Vet_chief_Service)Config_File.getContainer().getBean("Vet_chief_Service");
			}
		});
		
		Thread t10=new Thread(new Runnable() {
			@Override
			public void run() {
				sec_chief_Service=(Security_chief_Service)Config_File.getContainer().getBean("Security_chief_Service");
			}
		});
		
		Thread t11=new Thread(new Runnable() {
			@Override
			public void run() {
				csrService=(CasheerService) Config_File.getContainer().getBean("CasheerService");
			}
		});
		
		Thread t12=new Thread(new Runnable() {
			@Override
			public void run() {
				caregiverService=(CaregiverService) Config_File.getContainer().getBean("CaregiverService");
			}
		});
		
		Thread t13=new Thread(new Runnable() {
			@Override
			public void run() {
				vetService=(VetService) Config_File.getContainer().getBean("VetService");
			}
		});
		
		Thread t14=new Thread(new Runnable() {
			@Override
			public void run() {
				guardService=(GuardService) Config_File.getContainer().getBean("GuardService");
			}
		});
		
		Thread t15=new Thread(new Runnable() {
			@Override
			public void run() {
				pd_service=(Service_performance_data) Config_File.getContainer().getBean("Service_performance_data");
			}
		});
		
		
		
		
		Thread t16=new Thread(new Runnable() {
			@Override
			public void run() {
				client_creating_animals=(Client_Creating_Animals) Config_File.getContainer().getBean("Client_Creating_Animals");
			}
		});
		
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t11.start();
		t12.start();
		t13.start();
		t14.start();
		t15.start();
		t16.start();
		
		try {
			t1.join();t2.join();t3.join();
			t4.join();t5.join();t6.join();
			t7.join();t8.join();t9.join();
			t10.join();t11.join();t12.join();
			t13.join();t14.join();t15.join();
			t16.join();
		} 
		catch (InterruptedException e) {e.printStackTrace();}
		
		
	}
	
	public static Mock_Controller getInstance() {
		
		if (obj==null) {
			obj=new Mock_Controller();
		}
		
		return obj;
	}

	
	public CEOService getCeoService() {
		return ceoService;
	}

	public ManagerGeneralService getManagerService() {
		return managerService;
	}

	public WorkersService getWorkerService() {
		return workerService;
	}



	public FacilitiesService getFacService() {
		return facService;
	}

	public Vet_chief_Service getVet_chief_Service() {
		return vet_chief_Service;
	}
	
	public Security_chief_Service getSec_chief_Service() {
		return sec_chief_Service;
	}

	public TL_casheer_Service getTl_casheer_Service() {
		return tl_casheer_Service;
	}

	public TL_cleaner_Service getTl_cleaner_Service() {
		return tl_cleaner_Service;
	}

	public TL_janitor_Service getTl_janitor_Service() {
		return tl_janitor_Service;
	}

	public TL_caregiver_Service getTl_caregiver_Service() {
		return tl_caregiver_Service;
	}
	
	public CasheerService getCsrService() {
		return csrService;
	}

	public CaregiverService getCaregiverService() {
		return caregiverService;
	}

	public VetService getVetService() {
		return vetService;
	}

	public GuardService getGuardService() {
		return guardService;
	}
	

	public Service_performance_data getPd_service() {
		return pd_service;
	}

	public Client_Creating_Animals getClient_Creating_Animals() {
		return client_creating_animals;
	}
	
	
	
	
	
}
