package com.springBootPdfReport.Utils;

import java.text.SimpleDateFormat;

public class DateUtils {
	public static java.sql.Date strToDate(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
