package my.pack.test.unitTest;

import static org.junit.Assert.*;

import my.pack.dataAccessTier.dao.workers.WorkerDao_hibernate_impl;
import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.superclasses.Worker;
import my.pack.dataAccessTier.domain.workers.Cleaner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorkerDao_hibernate_impl_UnitTest {

	private WorkerDao_hibernate_impl workerDao;
	private Worker mock_worker;
	
	//Constructor
	public WorkerDao_hibernate_impl_UnitTest() {
		
		workerDao=new WorkerDao_hibernate_impl();
		//Here we'll create some mock worker
		Address cleaner4_address=new Address("Holon","Volfson",38);
		 mock_worker=new Cleaner("Limor","menahem",41,'F',231221,cleaner4_address,4580,0524546436);
	}

	@Test
	public void testCreate() {
		workerDao.create(mock_worker);
	}

	@Test
	public void testRead() {
		workerDao.read(mock_worker);
	}

	@Test
	public void testDelete() {
		workerDao.delete(mock_worker);
	}

}
