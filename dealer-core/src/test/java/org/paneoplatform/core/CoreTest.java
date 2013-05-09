package org.paneoplatform.core;


import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.paneoplatform.core.model.dao.DaoHelper;
import org.paneoplatform.core.model.dao.TransactionDao;
import org.paneoplatform.core.model.domain.Transaction;
import org.paneoplatform.core.uniqid.UniqueId;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import junit.framework.TestCase;



@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "classpath:AppContext.xml" })
public class CoreTest extends TestCase {

	@Resource
	TransactionDao transactionDao;


	
	/**
	 * Test AppContext Loading to assure all needed beans
	 * are properly configured.
	 */
	@Test
	public void loadAppContextTest(){
		Assert.assertTrue(true);
	}

	@Test
	public void saveTest(){
		
		Transaction testTransact = new Transaction();
		 UniqueId uid = new UniqueId();
		
		testTransact.setTransac_id(uid.getStringId());
		testTransact.setCorrelation_id("00001");
		testTransact.setBreadcrumb_id("00002");
		
		testTransact.setDestination("destination");
		testTransact.setOrigin("origint");
		testTransact.setBody("aaa");
		testTransact.setX_coord_from(0);
		testTransact.setY_coord_from(0);
		
		transactionDao.save(testTransact);
		
		Assert.assertTrue(true);
	}
	
	@Test
	public void findTest(){
		
		Transaction testTransact = new Transaction();
		 UniqueId uid = new UniqueId();
		 
		testTransact.setTransac_id(uid.getStringId());
		testTransact.setCorrelation_id("00001");
		testTransact.setBreadcrumb_id("00002");
		
		testTransact.setDestination("destination");
		testTransact.setOrigin("origint");
		testTransact.setBody("Find Test Transaction");
		testTransact.setX_coord_from(0);
		testTransact.setY_coord_from(0);
		
		transactionDao.save(testTransact);
		
		Transaction resTransact = transactionDao.find(testTransact.getTransac_id());
		
		Assert.assertEquals(testTransact, resTransact);
	}	

/*	
	@Test
	public void loadTest(){
		
		for(int index = 0; index < 10000; index++){
		Transaction testTransact = new Transaction();
		 UniqueId uid = new UniqueId();
		 
		testTransact.setTransac_id(uid.getStringId());
		testTransact.setCorrelation_id("00001");
		testTransact.setBreadcrumb_id("00002");
		
		testTransact.setDestination("destination");
		testTransact.setOrigin("origint");
		testTransact.setBody(Integer.toString(index));
		testTransact.setX_coord_from(0);
		testTransact.setY_coord_from(0);
		
		transactionDao.save(testTransact);
		}
		Assert.assertTrue(true);
	}
*/	
}
