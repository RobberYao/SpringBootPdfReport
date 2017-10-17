package com.springBootPdfReport.Utils;

import java.text.SimpleDateFormat;

public class DateUtils {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static java.sql.Date strToDate(String strDate) {
		String str = strDate;
		
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(d.getTime());
		return date;
	}
}
