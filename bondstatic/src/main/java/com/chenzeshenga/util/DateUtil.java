package com.chenzeshenga.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String result = df.format(date);
		System.out.println(result);
		return result;
	}

	public static String getDate(Date date, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - offset);
		Date result = c.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String result_str = df.format(result);
		return result_str;
	}
}
