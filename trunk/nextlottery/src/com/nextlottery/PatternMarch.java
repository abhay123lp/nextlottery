package com.nextlottery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/********************************************************************
 * File Name:    PatternMarch.java
 *
 * Date Created: Mar 14, 2011
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2011 NextLottery. All Rights Reserved.
 *
 *******************************************************************/

/**
 * Detailed description of the class.
 *
 */
public class PatternMarch {
	
	public String getRegisterNumber(String xmlText){
		String tem = null;
		if(xmlText.indexOf("This study is registered with ") != -1){
			tem = xmlText.substring(xmlText.indexOf("This study is registered with "));
			if(tem.indexOf("number<") != -1){
				tem = tem.substring(tem.indexOf("number<") + 7);
				if(tem.indexOf(">") != -1 && tem.indexOf("<") != -1){
					tem = tem.substring(tem.indexOf(">") + 1, tem.indexOf("<"));
				}
			}
		}
		return tem;
	}
	
	public String getRegisterNumberReg(String xmlText){
		String tem = null;
		int index = xmlText.indexOf("This study is registered with "); 
		if(index != -1){
			tem = xmlText.substring(index, index + 120);
			Pattern p = Pattern.compile("[A-Z]+[0-9]+");
			Matcher m = p.matcher(tem);
			if(m.find()){
				System.out.println("match: " + m.start());
				tem = tem.substring(m.start(), m.end());
			}
		}
		return tem;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PatternMarch pm = new PatternMarch();
		String xml = " This study is registered with ISRCTN, number ISRCTN73318385.</ce:simple-para>			</ce:absdadfasdfasdefasdfadfstract-sec>  ";
		System.out.println("Register number :" + pm.getRegisterNumber(xml) + ":");
		System.out.println("Register number :" + pm.getRegisterNumberReg(xml) + ":");
		

	}

}

