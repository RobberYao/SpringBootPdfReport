package com.springBootPdfReport.Action;

import java.net.URL;

import javax.annotation.Resource;
import javax.annotation.Resources;
import org.springframework.core.io.ResourceLoader;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.springframework.core.io.DefaultResourceLoader;

public class test1 {
	
	
	//http://www.cnblogs.com/mingforyou/p/4568521.html
	
	private static ResourceLoader RESOURCE_LOADER = new DefaultResourceLoader();
	static test1 t1=new test1();
	public static void main(String[] args) {
		String contentType = "application/pdf";
		String fileSuffix = ".pdf";
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UniGB-UCS2-H");
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");

		//String template = getServletContext().getRealPath("Report/jrxml/" + reportName);
		//String template=RESOURCE_LOADER.getClassLoader().getResource("/jasper/test.jrxml").getFile();
		URL url = t1.getClass().getClassLoader().getResource("jasper/test.jrxml");

		try {
			JasperCompileManager.compileReportToFile("test.jrxml", "test.jasper");
			
			JasperFillManager.fillReport("test.jasper", params);
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		
	}
}
