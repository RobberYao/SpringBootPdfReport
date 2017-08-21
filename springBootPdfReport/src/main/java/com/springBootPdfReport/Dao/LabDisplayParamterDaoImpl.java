package com.springBootPdfReport.Dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springBootPdfReport.Model.LabDisplayParamter;

@Repository
public class LabDisplayParamterDaoImpl implements LabDisplayParamterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// @Override
	// public LabDisplayParamter findOne(int id) {
	// String sql="select * from LABDISPLAYPARAMTER WHERE ID = ?";
	// String name = (String) jdbcTemplate.queryForObject("SELECT name FROM USER
	// WHERE user_id = ?", new Object[] {id}, java.lang.String.class);
	// //jdbcTemplate.query(, rse)
	//
	// return null;
	// }

	// 查询方式(查询一个对象)
	public LabDisplayParamter findOneById(int id) {
		String sql = "select * from LAB_DISPLAYPARAMTER where id=?";
		Object[] obj = new Object[] { id };
		Object labDisplay = jdbcTemplate.queryForObject(sql, obj, new BeanPropertyRowMapper(LabDisplayParamter.class));
		return (LabDisplayParamter) labDisplay;
	}
	
	
	public  List<LabDisplayParamter> findListItems(String tableName, Date createOn, Date stopEnd) {

		String sql = "select * from ? where CREATEDON between ? and ? ;";
		return jdbcTemplate.queryForList(sql, new Object[] { tableName, createOn, stopEnd }, LabDisplayParamter.class);

	}
	
	
	
}
