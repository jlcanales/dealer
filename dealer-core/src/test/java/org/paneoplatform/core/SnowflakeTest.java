package org.paneoplatform.core;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.paneoplatform.core.uniqid.UniqueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import junit.framework.TestCase;


/**
 * This test suite is implemented to ilustrate snowflake common use
 * for dealer project.
 * 
 * @author jdeveloper
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:AppContext.xml" })
public class SnowflakeTest extends TestCase {
	private static Logger log = LoggerFactory.getLogger(SnowflakeTest.class);
	
	/**
	 * Test AppContext Loading to assure all needed beans
	 * are properly configured.
	 */
	@Test
	public void loadAppContextTest(){
		Assert.assertTrue(true);
	}

	/**
	 * Get a SnowFlake UUID 
	 */
	@Test
	public void getIdTest(){

	        UniqueId uid = new UniqueId();
	        int n = 1000000;
	        
	        for(int i=0; i<n; i++) {
	            log.info(uid.getStringId());
	            //System.out.println(uid.getStringId());
	        }


		Assert.assertTrue(true);
	}	
	
}
