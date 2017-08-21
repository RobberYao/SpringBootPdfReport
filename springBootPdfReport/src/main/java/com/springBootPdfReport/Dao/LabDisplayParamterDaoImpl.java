package com.springBootPdfReport.Dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	@SuppressWarnings("unchecked")
	public List<LabDisplayParamter> findListItems(String tableName, String createOn, String stopEnd) {

		// String sql = "select * from ? where CREATEDON between ? and ? ";

		String sql = "select  INPUTPROBENUMBER,DISPROBENUMBER,CREATEDON,DISTEMPERATURE,DISHUMIDITY  from  " + tableName
				+ "  where  CREATEDON  between  \'" + createOn + "\' and  \'" + stopEnd + "\'";
		// System.out.println(sql);
		// List<Map<String,Object>> lm=new ArrayList<Map<String,Object>>();
		// List<LabDisplayParamter> ll=new ArrayList<LabDisplayParamter>();
		// ll=jdbcTemplate.queryForList(sql, LabDisplayParamter.class);
		// lm=jdbcTemplate.queryForList(sql);

		return jdbcTemplate.query(sql, new Object[] {}, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				LabDisplayParamter item = new LabDisplayParamter();
				//item.setId(rs.getString("id"));
				item.setInputProbeNumber(rs.getString("INPUTPROBENUMBER"));
				item.setDisProbeNumber(rs.getString("DISPROBENUMBER"));
				item.setDisTemperature(rs.getDouble("DISTEMPERATURE"));
				item.setDisHumidity(rs.getDouble("DISHUMIDITY"));
				item.setCreatedOn(rs.getString("CREATEDON"));
				return item;
			}
		});
	}

	/**
	 * 查询数据表结果集转换为list
	 * 
	 * @param rs
	 * @return
	 * @throws java.sql.SQLException
	 */
	public List<Map> resultSetToList(ResultSet rs) throws java.sql.SQLException {
		if (rs == null)
			return Collections.EMPTY_LIST;
		ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
		int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
		List<Map> list = new ArrayList();
		Map rowData = new HashMap();
		while (rs.next()) {
			rowData = new HashMap(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			rowData.get("TAB_DISPLAYNAME");
			rs.getObject("ID");
			list.add(rowData);
		}
		return list;
	}

}
