package com.vuclip.all_latest_apis.main;


import com.vuclip.all_latest_apis.bo.ProcessTextFile;
import com.vuclip.all_latest_apis.utils.Constants;

public class AllLatestApis {
	/*
	 * private static final Logger logger = Logger.getLogger(AllLatestApis.class);
	 */

	public static void main(String[] args) {
		/* logger.info(">> In " + Constants.API_NAME); */
		
		ProcessTextFile processTextFile= new ProcessTextFile();
		processTextFile.process();
		/* logger.info("<< Out " + Constants.API_NAME); */
	}

}
