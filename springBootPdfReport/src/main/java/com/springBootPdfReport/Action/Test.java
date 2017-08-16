package com.springBootPdfReport.Action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import product.file.report.ReportGenerater;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.fill.JRFiller;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings("unchecked")
public class Test implements ReportGenerater {

	// 生成PDF
	public void generatePdf(String template, String output, Map param, Map<String, List<Map>> datasource) {

		JasperReport jasper = this.loadTemplate(template); // 加载japserreport模版
		JasperPrint jPrint = this.fileReport(jasper, param, datasource); // 填充数据
		try {
			JasperExportManager.exportReportToPdfFile(jPrint, output); // 生成PDF文件
		} catch (JRException e) {
			throw new RuntimeException();
		}
	}

	// 生成Html
	public void generateHtml(String template, String output, Map param, Map<String, List<Map>> datasource) {
		JasperReport jasper = this.loadTemplate(template); // 加载japserreport模版
		JasperPrint jPrint = this.fileReport(jasper, param, datasource); // 填充数据
		try {
			JasperExportManager.exportReportToHtmlFile(jPrint, output); // 生成html文件
		} catch (JRException e) {
			throw new RuntimeException();
		}
	}

	// 生成PDF输出流
	public void generatePdf(String template, OutputStream os, Map param, Map<String, List<Map>> datasource) {

		JasperReport jasper = this.loadTemplate(template); // 加载japserreport模版
		JasperPrint jPrint = this.fileReport(jasper, param, datasource); // 填充数据
		try {
			JasperExportManager.exportReportToPdfStream(jPrint, os); // 生成PDF文件流
		} catch (JRException e) {
			throw new RuntimeException();
		}
	}

	// 加载报表模版
	private JasperReport loadTemplate(String template) {
		try {
			JasperReport jasper = (JasperReport) JRLoader.loadObject(template);
			return jasper;
		} catch (JRException e) {
			throw new RuntimeException("JRException");
		}
	}

	// 填充报表数据
	private JasperPrint fileReport(JasperReport jasper, Map param, Map<String, List<Map>> datasource) {
		Set keyset = datasource.keySet();
		Iterator itor = keyset.iterator();
		while (itor.hasNext()) {
			String tokey = String.valueOf(itor.next());
			JRDataSource jrDs = new JRMapCollectionDataSource(datasource.get(tokey));
			param.put(tokey, jrDs);
		}

		try {
			JasperPrint jPrint = JRFiller.fillReport(jasper, param);
			return jPrint;
		} catch (JRException e) {
			throw new RuntimeException();
		}
	}

	// 测试Main
	public static void main(String[] args) {
		JasperGenerater jasperGenerater = new JasperGenerater();
		String template = "D://编程工具//ireport//user.jasper"; // jasper模版
		String outputfile = "D://测试.PDF"; // 输出路径

		Map<String, List<Map>> datasource = new HashMap<String, List<Map>>(); // 数据源；可为多List
		List<Map> list = new ArrayList<Map>();
		for (int i = 0; i < 10; i++) {
			Map cur = new HashMap();
			cur.put("USER_ID", "USER" + i);
			cur.put("USER_NAME", "用户" + i);
			cur.put("LOGIN_COUNT", i);
			list.add(cur);
		}
		datasource.put("REPORT_DATA_SOURCE", list); // 指定数据源

		Map param = new HashMap(); // 参数

		param.put("title", "用户登陆统计");
		param.put("date", "2009.01.01——2009.08.08");
		jasperGenerater.generatePdf(template, outputfile, param, datasource); // 生成PDF文件

	}

}
