package com.springBootPdfReport.Action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.groovy.tools.groovydoc.Main;

import com.springBootPdfReport.Utils.DateSourceBaseFactory;

public class test2 {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
		String createdOn = "2017-07-18 10:09:28";
		String stopEnd = "2017-07-19 10:09:28";

		try {
			Date date1 = sdf.parse(createdOn);
			Date date2 = sdf.parse(stopEnd);
			List list1=DateSourceBaseFactory.createBeanCollection("lab_displayparamter0101", date1, date2);
			System.out.println(list1.size());
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
