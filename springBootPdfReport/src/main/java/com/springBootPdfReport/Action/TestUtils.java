package com.springBootPdfReport.Action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class TestUtils {

	public static void main(String[] args) throws JRException {
		//getFirst();
		getPa();
	}

	private static void getFirst() throws JRException {
		// 第一步：装载jasper文件
		File jasperFileName = new File("D://Jasper/test2.jasper");
		// 第二步：设置参数值
		/* 设置参数 */
		HashMap<String, Object> params = new HashMap<String, Object>();// 建立参数表
		params.put("name", "我们的产品"); // 设置参数值
		// 第三步：利用JasperRunManager生成PDF文件
		JasperRunManager.runReportToPdfFile(jasperFileName.getPath(), params, new JREmptyDataSource());
		
	}

	private static void getPa() throws JRException {
		JRPdfExporter jrpdf = new JRPdfExporter();
		JasperReport report1 = (JasperReport) JRLoader.loadObject(new File("D://Jasper/test.jasper"));
		JasperReport report2 = (JasperReport) JRLoader.loadObject(new File("D://Jasper/test2.jasper"));
		List<String> list = new ArrayList<String>();
		list.add("测试数据");
		JRBeanCollectionDataSource ds1 = new JRBeanCollectionDataSource(list);
		JRBeanCollectionDataSource ds2 = new JRBeanCollectionDataSource(list);
		HashMap<String, Object> params = new HashMap<String, Object>();// 建立参数表
		params.put("name", "我们的产品"); // 设置参数值

		JasperPrint jasperPrint = JasperFillManager.fillReport(report1, params, ds1);
		JasperPrint jasperPrint2 = JasperFillManager.fillReport(report2, params, ds2);
		List jasperPrintList = new ArrayList();
		jasperPrintList.add(jasperPrint);
		jasperPrintList.add(jasperPrint2);

		File file = new File("D://Jasper/Test.pdf");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();
		byte[] bytes = baos.toByteArray();
		// 确定写出文件的位置
		try {
			// 建立输出字节流
			FileOutputStream fos = new FileOutputStream(file);
			// 用FileOutputStream 的write方法写入字节数组
			fos.write(bytes);
			System.out.println("写入成功");
			// 为了节省IO流的开销，需要关闭
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
