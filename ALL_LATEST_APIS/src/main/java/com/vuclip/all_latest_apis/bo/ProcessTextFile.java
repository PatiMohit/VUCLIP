package com.vuclip.all_latest_apis.bo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.vuclip.all_latest_apis.utils.Constants;

public class ProcessTextFile implements ProcessText {

	//private static final Logger logger = Logger.getLogger(ProcessTextFile.class);

	public void process() {
		final String METHOD_NAME = "process";
		/*
		 * logger.info(">> In class " + this.getClass().getName()); logger.info(">> In "
		 * + METHOD_NAME);
		 */
		List<String> items = new ArrayList<String>();
		try (Stream<String> stream = Files.lines(Paths.get(Constants.FILE_PATH))) {
			Consumer<String> action = new Consumer<String>() {

				@Override
				public void accept(String t) {
					items.addAll(Arrays.asList(t.split("\\s*,\\s*")));

				}
			};
			stream.forEach(action);

		} catch (IOException e) {
			e.printStackTrace();
			return;
			/* logger.error("Error in processing the file" + e.getMessage()); */
		}
		findAPI(items);

	}

	final static void findAPI(List<String> list) {
		Map<String, Integer> maxApiVersionMap = new HashMap<String, Integer>();
		int i = 1;
		while (i < list.size()) {

			Integer value = Integer.parseInt(list.get(i + 1).replaceAll("[^\\d]", ""));
			if (maxApiVersionMap.containsKey(list.get(i))) {

				if (maxApiVersionMap.get(list.get(i)) < value) {
					maxApiVersionMap.put(list.get(i), value);
				}
			} else {
				maxApiVersionMap.put(list.get(i), value);
			}
			i = i + 3;
		}
		//System.out.println(maxApiVersionMap);
		//System.out.println(list);

		i = 1;
		Set<String> outputList = new HashSet<String>();
		while (i <= list.size()) {

			// Integer value=Integer.parseInt(list.get(i+1).replaceAll("[^\\d]", "" ));
			if (("v" + String.valueOf(maxApiVersionMap.get(list.get(i)))).equals((list.get(i + 1).trim()))) {
				
			} else {
				//outputList.remove(list.get(i - 1));
				outputList.add(list.get(i - 1));
			}
			i = i + 3;
		}
		i=0;
		while(i<list.size()) {
			if(!outputList.contains(list.get(i))) {
				System.out.println(list.get(i));
				return;
			}
			i=i+3;
		}

	}

}
