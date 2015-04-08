package net.suntec.framework;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMath extends TestCase {
	public Logger logger = LoggerFactory.getLogger(TestMath.class);

	public void testhasPayRent() {
		logger.info("total pay: " + ( 1350 * 12 + 1400 * 12 + 1500 * 12 + 1600
				* 12 ) );
	}

	public void testTheRent() {
		double rate = 0.07;
		double total = 0;
		double result = 0;
		double t = 3000;
		for (int i = 0; i < 10; i++) {
			result = t * Math.pow(1 + rate, i);
			total += result * 12;
		}
		logger.info("for ten years , result : " + result);
		logger.info("for ten years,  total : " + total);
	}

	public void testSalary() {
		double total = 0;
		double baseSalary = 15.5;
		double rate = 0.1;
		int years = 10;
		double result = 0;
		for (int i = 0; i < years; i++) {
			result = baseSalary * Math.pow(1 + rate, i);
			total += result * 14 * 0.5;
			logger.info("for " + i + " years  , your salary : " + result);
		}
		logger.info("for twenty years,  total : " + total);
	}
}
