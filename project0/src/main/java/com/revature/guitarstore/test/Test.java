package com.revature.guitarstore.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Test {

	private static final Logger logger = LogManager.getLogger(Test.class);
	
	public static void main (String[] args) {
		
		logger.trace("we have just greeted the user!");
		logger.trace("We've just greeted the user!");
		logger.debug("We've just greeted the user!");
		logger.info("We've just greeted the user!");
		logger.warn("We've just greeted the user!");
		
		logger.error("We've just greeted the user!");
		logger.fatal("We've just greeted the user!");
		
	}
	
	
}
