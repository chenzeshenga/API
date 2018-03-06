package com.chenzeshenga.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String result = df.format(date);
		System.out.println(result);
		return result;
	}
}
