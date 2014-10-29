package my.pack.cache_performance;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.statistics.StatisticsGateway;


public class Cache_methods_data {

	private static Cache cache;

	public  Cache_methods_data() {}

	public static void show_statics() {

		CacheManager cache_manager = CacheManager.newInstance("src/ehcache.xml");
		String[] cacheNamesForManager = cache_manager.getCacheNames();
		
		for(String current_cache:cacheNamesForManager) {	
			Cache cache=cache_manager.getCache(current_cache);
			StatisticsGateway stats=cache.getStatistics();
			
			System.out.println("********"+current_cache+"***********");
			
			System.out.println
			("cacheHitCount of " +current_cache+": "+stats.cacheHitCount());
			
			System.out.println
			("cacheMissCount of " +current_cache+": "+stats.cacheMissCount());
			
			System.out.println
			("Size of " +current_cache+": "+stats.getSize());
			
			System.out.println("*************************************");

		}

	}

}
