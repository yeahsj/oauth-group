package net.suntec.framework.iauto.client;

import junit.framework.TestCase;
import net.suntec.framework.iauto.client.dto.ResourceConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class IautoTest extends TestCase {

	protected Logger logger = LoggerFactory.getLogger(IautoTest.class);
	protected ResourceConfig instance = null;

	public void setUp() throws Exception {
		super.setUp();
		instance = ResourceConfig.getInstance();
		instance.init("config/SystemConfig.xml");
	}

	public void tearDown() throws Exception {
		instance.destroyed();
		super.tearDown();
	}

	//
	// /**
	// * @return the suite of tests being tested
	// */
	// public static Test suite() {
	// return new TestSuite(IautoTest.class);
	// }

	public void testHello() {
		logger.info("hello iauto");
	}

}
