package my.pack.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


@Component(value="Config")
public class Config_File {
	
	
	public  Config_File(){
		System.out.println("Here for debug");
	};
	
	private static ApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
	private static SessionFactory sessionFactory=(SessionFactory) container.getBean("sessionFactory");
	
	//If we use hibernate's way in order to get session factory
	//private static ServiceRegistry serviceRegistry;
	

	
	public static ApplicationContext getContainer() {
		return container;
	}

	
	/*
	//If we use hibernate's way in order to get session factory
	public static void configureSessionFactory() throws HibernateException {
	    Configuration configuration = new Configuration();
		configuration.configure();
			
	    serviceRegistry = new ServiceRegistryBuilder().
	    		applySettings(configuration.getProperties()).buildServiceRegistry();        
	    try {sessionFactory = configuration.buildSessionFactory(serviceRegistry);} 
	    catch (Exception e) {e.printStackTrace();}

	}*/




	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		Config_File.sessionFactory = sessionFactory;
	}

/*	
 	//If we use hibernate's way in order to get session factory
 	public static ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
	
	//If we use hibernate's way in order to get session factory
	public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
		Config_File.serviceRegistry = serviceRegistry;
	}*/
	
	

}
