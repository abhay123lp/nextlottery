/********************************************************************
 * File Name:    DataPreparer.java
 *
 * Date Created: Mar 5, 2011
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2011 NextBio. All Rights Reserved.
 *
 *******************************************************************/

package com.nextlottery.analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Detailed description of the class.
 *
 */
public class DataPreparer {
	private final String megaMillFile = "." + File.separator + "resources" + File.separator + "data" + File.separator +"MegaMillions.txt";

	public void getNumbers(){
		List<String> numbers = new ArrayList<String>();
		BufferedReader br = null;

		try{
			br = new BufferedReader(new FileReader(new File(this.megaMillFile)));
			String aLine;
			Pattern p = Pattern.compile("^[\\d].*");
			Pattern split = Pattern.compile("[\\s]+");
			while((aLine = br.readLine()) != null){
				if(p.matcher(aLine) != null && p.matcher(aLine).matches()){
					String[] aLineNum = split.split(aLine);
					if(aLineNum != null){
						processLineNums(aLineNum, numbers);
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	private void processLineNums(String[] aLineNum, List<String> numbers) {
		for(int i = 2; i < 8; i++){
			numbers.add(aLineNum[i]);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataPreparer dp = new DataPreparer();
		dp.getNumbers();
		System.out.println("Finished. ");

	}

}

