package com.springBootPdfReport.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springBootPdfReport.Model.LabDisplayParamter;

/**
 * 接下来实现上个类中的DateSourceBaseFactory(提供数据的数据源工厂)，它是实际从数据库中取出相应数据，然后将其封装在map中，
 * 然后又将相应的map装在List容器中。
 * 
 * @author Administrator
 *
 */
@Repository
public class DateSourceBaseFactory {
	@Autowired
	private static JdbcTemplate jdbcTemplate;

	public static List<LabDisplayParamter> createBeanCollection(String tableName, Date createOn, Date stopEnd) {

		String sql = "select * from ? where createdOn between ? and ? ;";
		return jdbcTemplate.queryForList(sql, new Object[] { tableName, createOn, stopEnd }, LabDisplayParamter.class);

	}

}
