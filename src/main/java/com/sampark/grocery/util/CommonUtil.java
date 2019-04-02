package com.sampark.grocery.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CommonUtil {
	public static String HOUR = "HOUR";
	public static String MINUTE = "MIN";
	public static String SECOND = "SEC";
	public static String DAY = "DAY";

	public static boolean isNull(String str) {
		if(str == null || str.equalsIgnoreCase("NULL") || str.equalsIgnoreCase("")) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean isNull(java.sql.Date str) {
		if(str == null) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean isNull(Integer str) {
		if(str == null) {
			return true;
		}else {
			return false;
		}
	}
	public static String replaceNull(String str) {
		if(str == null || str.equalsIgnoreCase("NULL") || str.equalsIgnoreCase("")) {
			return "";
		}else {
			return str;
		}
	}
	public static Double replaceNull(Double str) {
		if(str == null) {
			return 0.00;
		}else {
			return str;
		}
	}
	public static Integer replaceNull(Integer str) {
		if(str == null) {
			return 0;
		}else {
			return str;
		}
	}
	public static Double timestampdiff(Timestamp startTime,Timestamp endTime,String type) {

		HashMap<String,Integer> timeMap = new HashMap<String,Integer>();
		timeMap.put("SEC", 1);
		timeMap.put("MIN", 2);
		timeMap.put("HOUR", 3);
		timeMap.put("DAY", 4);

		Long milliseconds1 = startTime.getTime();
		Long milliseconds2 = endTime.getTime();

		Long diff = milliseconds2 - milliseconds1;
		Double doubleVal = 0.0;
		switch (timeMap.get(type)) {
		case 1:
			doubleVal = Double.parseDouble(diff.toString()) / 1000;
			break;
		case 2:
			doubleVal = Double.parseDouble(diff.toString()) / (60 * 1000);
			break;
		case 3:
			doubleVal = Double.parseDouble(diff.toString()) / (60 * 60 * 1000);
			break;
		case 4:
			doubleVal = Double.parseDouble(diff.toString()) / (24 * 60 * 60 * 1000);
			break;

		default:
			doubleVal = Double.parseDouble(diff.toString());
		}

		return doubleVal;

	}
	public static Double timestampdiff(String startTime,String endTime,String type) {
		Timestamp st = Timestamp.valueOf(startTime);
		Timestamp et = Timestamp.valueOf(endTime);


		HashMap<String,Integer> timeMap = new HashMap<String,Integer>();
		timeMap.put("SEC", 1);
		timeMap.put("MIN", 2);
		timeMap.put("HOUR", 3);
		timeMap.put("DAY", 4);

		Long milliseconds1 = st.getTime();
		Long milliseconds2 = et.getTime();

		Long diff = milliseconds2 - milliseconds1;
		Double doubleVal = 0.0;
		switch (timeMap.get(type)) {
		case 1:
			doubleVal = Double.parseDouble(diff.toString()) / 1000;
			break;
		case 2:
			doubleVal = Double.parseDouble(diff.toString()) / (60 * 1000);
			break;
		case 3:
			doubleVal = Double.parseDouble(diff.toString()) / (60 * 60 * 1000);
			break;
		case 4:
			doubleVal = Double.parseDouble(diff.toString()) / (24 * 60 * 60 * 1000);
			break;

		default:
			doubleVal = Double.parseDouble(diff.toString());
		}

		return doubleVal;

	}
/*
	public static Boolean validateFileName(String fileName) {
		Boolean isValid = true;
		int pos = fileName.lastIndexOf(".");
		String ext= fileName.substring(pos);
		
		isValid = StringUtils.containsIgnoreCase(Constants.FILE_FORMAT, ext);
		
		//System.out.println("Exntesion check validation: "+isValid);
		
		if(!isValid) {
			return isValid;
		}	
		
		isValid = !(StringUtils.countMatches(fileName,".") > 1);
		
		//System.out.println("Extension >1 check validation "+isValid);
		
		if(!isValid) {
			return isValid;
		}
		
		isValid = !StringUtils.containsIgnoreCase(fileName,"%00");
		
		//System.out.println("%00 Exists validation : "+isValid);
		
		if(!isValid) {
			return isValid;
		}
		
		isValid = !StringUtils.containsIgnoreCase(fileName,";");
		
		//System.out.println("; Exists validation : "+isValid);
		
		if(!isValid) {
			return isValid;
		}
		
		return true;
	}
	*/
	public static String convertToFormat(String dateStr, String format) {
		
		
		DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");
		
		// parse the date string into Date object
		Date date=null;
		try {
			date = srcDf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		DateFormat destDf = new SimpleDateFormat(format);
		 
		// format the date into another format
		dateStr = destDf.format(date);
		return dateStr;
	}
	
	public static String getLastDayOfPreviousMonth() {
		Calendar aCalendar = Calendar.getInstance();
		// add -1 month to current month
		aCalendar.add(Calendar.MONTH, -1);
		// set DATE to 1, so first date of previous month
		aCalendar.set(Calendar.DATE, 1);

		Date firstDateOfPreviousMonth = aCalendar.getTime();

		// set actual maximum date of previous month
		aCalendar.set(Calendar.DATE,     aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		//read it
		Date lastDateOfPreviousMonth = aCalendar.getTime();
		
		String newDate = new SimpleDateFormat("yyyy-MM-dd").format(lastDateOfPreviousMonth);
		
		return newDate;
	}
}
