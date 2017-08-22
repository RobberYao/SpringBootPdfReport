package com.springBootPdfReport.Utils;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.jdbc.core.JdbcTemplate;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import Entity.Employee;
import Utils.PDbJdbcUtil;

//http://blog.csdn.net/lianruanjian/article/details/46008161
/**
 * jasperSoft报表生成 调用工具
 * 
 * @author Albert
 * 
 */
public class JasperReportUtil {
	/**
	 * 直接打印
	 * 
	 * @param fileName
	 *            模板文件
	 * @param parameters
	 *            参数
	 */
	public static void print(String fileName, Map<String, Object> parameters) {
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		InputStream inReport = JasperReportUtil.class.getResourceAsStream(fileName);
		try {
			jasperReport = JasperCompileManager.compileReport(inReport);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, .getConnection());
			// 直接打印，true为可选择打印机
			JasperPrintManager.printReport(jasperPrint, false);
			// 预览
			// JasperViewer.setDefaultLookAndFeelDecorated(true);
			// JasperViewer.viewReport(jasperPrint, true);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void print_p(String fileName, Map<String, Object> parameters) {
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		InputStream inReport = JasperReportUtil.class.getResourceAsStream(fileName);
		try {
			jasperReport = JasperCompileManager.compileReport(inReport);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, .getConnection());
			// 直接打印，true为可选择打印机
			// JasperPrintManager.printReport(jasperPrint, false);
			// 预览
			// JasperViewer.setDefaultLookAndFeelDecorated(false);
			if (jasperPrint.getPages().size() > 0) {
				JasperViewer.viewReport(jasperPrint, false);
			} else {
				JOptionPane.showMessageDialog(null, "无预览数据");
			}
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印预览
	 * 
	 * @param fileName
	 * @param list
	 */
	public static void preview(String fileName, List<Employee> list) {
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		InputStream inReport = JasperReportUtil.class.getResourceAsStream(fileName);
		try {
			jasperReport = JasperCompileManager.compileReport(inReport);
			jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(list));
		} catch (JRException e) {
			e.printStackTrace();
		}
		// 预览
		JasperViewer.setDefaultLookAndFeelDecorated(false);
		JasperViewer.viewReport(jasperPrint, false);
	}
}
