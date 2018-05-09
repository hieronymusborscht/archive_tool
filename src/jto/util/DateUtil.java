package jto.util;

import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {




	
	
	
	/*
	public static boolean is_this_a_sensible_date_value(java.sql.Date value){
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(value);
		int year = cal.get(java.util.Calendar.YEAR);
		return (year>1800 &&year<2030);
	}
	*/

	public static java.sql.Date makeSqlDate(String s){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		sdf.setLenient(false);
		java.util.Date utilDate = null;
		java.sql.Date sqlDate = null;
		try {
			utilDate = sdf.parse(s);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

	    return sqlDate;
	}
		

	public static String makeString(java.sql.Date d){
		java.text.DateFormat df = new java.text.SimpleDateFormat("dd-MMM-yyyy");
		return  df.format(d);
	}
	/*public static String makeFormatDate(java.sql.Date d){
		String s = "no date";
		java.text.DateFormat df = new java.text.SimpleDateFormat("MMM dd, yyyy");
		if(!d.equals( makeDateValue("01/20/1970"))){
			s= df.format(d);
		}
		return  s;
	}
	*/
	
	/*
	public static java.sql.Date makeDateValue(String inputString){
		java.sql.Date sql_date = null;
		java.text.DateFormat df = new java.text.SimpleDateFormat("MM/dd/yyyy");
		try {
			sql_date = new java.sql.Date(df.parse(inputString).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sql_date;
	}
	*/
	/*
	public static java.util.GregorianCalendar makeGregorian(java.sql.Date d){
	
		java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
		gc.setTimeInMillis(d.getTime());
		return gc;
	}
	*/
	public static String generateDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
/*	
	public static String generateDate(){
		java.text.DateFormat df = new java.text.SimpleDateFormat("dd-MMM-yyyy");
		java.util.GregorianCalendar calobj = new java.util.GregorianCalendar();
		calobj.roll(java.util.GregorianCalendar.MONTH, 1);
		calobj.set(java.util.GregorianCalendar.DAY_OF_MONTH, 1);
		return df.format(calobj.getTime());
		
	}
	*/
	/*
	public static java.util.GregorianCalendar generateFirstOfMonth(){
		//java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.GregorianCalendar calobj = new java.util.GregorianCalendar();
		calobj.roll(java.util.GregorianCalendar.MONTH, 1);
		calobj.set(java.util.GregorianCalendar.DAY_OF_MONTH, 1);
		return calobj;
		
	}
	*/

}

