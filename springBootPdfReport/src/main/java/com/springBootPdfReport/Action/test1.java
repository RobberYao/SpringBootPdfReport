package com.springBootPdfReport.Action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.fill.JRBaseFiller;
import net.sf.jasperreports.engine.fill.JRFiller;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;

public class test1 {
	@Autowired
	private static DataSource dataSource;
	// $P{}来标识参数，$F{}来标识字段
	// http://www.cnblogs.com/mingforyou/p/4568521.html

	private static ResourceLoader RESOURCE_LOADER = new DefaultResourceLoader();
	static test1 t1 = new test1();

	public static void main(String[] args) {
		String contentType = "application/pdf";
		String fileSuffix = ".pdf";
		String jasper = "testBlue.jasper";
		String jrxml = "testBlue.jrxml";
		Map<String, Object> parameters = new HashMap<String, Object>();
		Connection conn;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM  lab_displayparamter0101"); //用数据集传入带参数的SQL语句
		try {
			conn = dataSource.getConnection();// 连接数据库？连接jasper？
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UniGB-UCS2-H");
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");

			// String template = getServletContext().getRealPath("Report/jrxml/"
			// + reportName);
			// String
			// template=RESOURCE_LOADER.getClassLoader().getResource("/jasper/test.jrxml").getFile();
			URL url = t1.getClass().getClassLoader().getResource("jasper/test.jrxml");

			JasperCompileManager.compileReportToFile(jrxml, jasper);

			File sourceFile = new File(jasper);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(sourceFile);// load什么东西？模版？
			JRBaseFiller filler = JRFiller.createFiller(jasperReport);
			//JasperPrint print = filler.fill(parameters, conn);
			
			
			
			JasperPrint print=JasperFillManager.fillReport(jasper, parameters, new JRResultSetDataSource(rs));
					
					
					
			// JasperFillManager.fillReport("test.jasper", params);
			conn.close();
			
			//response.setContentType("application/x-download");

			StringBuilder header = new StringBuilder("attachment; filename=\"");
			header.append(URLEncoder.encode(print.getName(), "UTF-8"));
			header.append(parameters.get("startDate"));
			header.append("~");
			header.append(parameters.get("endDate"));
			header.append(fileSuffix);
			header.append("\"");
			//response.setHeader("Content-Disposition", header.toString());

			ByteArrayOutputStream baos = new ByteArrayOutputStream(512000);
			JasperReportsUtils.render(exporter, print, baos);

			//response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();

			baos.writeTo(out);
			out.flush();
			out.close();
			baos.close();
			
			
			
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
