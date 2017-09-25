package com.springBootPdfReport.Action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.jasperreports.JasperReportsUtils;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class TestPdf20170925 {

	public static void main(String[] args) {

	}

	public String getIreportPDFAction(){
	      Connection conn =null;//连接
	      try {
	         //String projectapproid =request.getParameter("projectapproid");
	    	  String projectapproid ="projectapproid";
	          ////连接JDBC得到数据URL
	          String url ="jdbc:mysql://localhost:3306/lab?amp;characterEncoding=GBK&amp;allowMultiQueries=true";
	          Class.forName("com.mysql.jdbc.Driver");
	          conn = DriverManager.getConnection(url,"root","");
	          //设置参数
	          Map parmerters = new HashMap();
	          parmerters.put("projectapproid", projectapproid);
	         //读取报表模板文件
	         File jasperFile=new File("D:/Workspaces/zwjxSystem/WebRoot/jasper/report1.jasper");
	         //设置报表中参数的值
	         Map map =new HashMap();
	         map.put("projectapproid", projectapproid);
	          //输出流
	          //OutputStream ous =response.getOutputStream();
	         OutputStream ous=new FileOutputStream(arg0);
	          //输出文件名
	          String filenames="项目立项";
	          //打印
	          JasperPrint print =JasperFillManager.fillReport(jasperFile.getPath(), map,conn);
	          //生成pdf
	          JRAbstractExporter exporter =new JRPdfExporter();
	          //response.reset();
	         // response.setContentType("application/pdf");
	         // response.setHeader("Content-Disposition","attachment;filename="+new String(filenames.getBytes("gbk"),"iso8859-1")+".pdf");
	          //指向
	          //JasperReportsUtils.render(exporter, print,ous);
	          JasperReportsUtils.
	          
	          ous.flush();
	          ous.close();
	      } catch (Exceptione) {
	          e.printStackTrace();
	          System.out.println("ireport生成报表出错！");
	      } finally {
	          try {
	             conn.close();
	          }catch(SQLException e) {
	             e.printStackTrace();
	          }
	      }
	          return null;
	   }

}
