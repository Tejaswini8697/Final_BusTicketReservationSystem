package com.app.pojos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DayFromDate {
	private static String[] day= {"sun", "mon", "tues", "wed", "thurs", "fri", "sat"};
	
	public DayFromDate() {
		System.out.println("In Day From Date");
	}
	
	public static String getDay(String date)
	{
		try {
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			d=formatter.parse(date);
			return day[d.getDay()];
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int getDayInt(String date)
	{
		try {
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			d=formatter.parse(date);
			return d.getDay();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static Date getDate(String date)
	{
		try {
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			return formatter.parse(date);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static LocalDate getLocalDate(String date) {
		return LocalDate.parse(date);
	}
	
	public static String getStringDate(Date d)
	{
		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(d);
	}
}
