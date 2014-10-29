package my.pack.cache_performance;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Component;

//@Component(value="Cache_2LC_data")
public class Cache_2LC_data {
	
	public Cache_2LC_data() {}
	
	public static void get2LC(Session session,String the_domain) {
		
		Statistics stats=session.getSessionFactory().getStatistics();
		stats.setStatisticsEnabled(true);
		System.out.println(stats.getSecondLevelCacheStatistics(the_domain));
	}

}
