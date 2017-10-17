package com.springBootPdfReport.Action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRBaseFiller;
import net.sf.jasperreports.engine.fill.JRFiller;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.WebContentGenerator;
import com.springBootPdfReport.Service.LabDisplayParamterService;
import com.springBootPdfReport.Utils.DateUtils;


@SuppressWarnings("deprecation")
@Controller
public class ReportFormController extends WebContentGenerator {

	private static final int OUTPUT_BYTE_ARRAY_INITIAL_SIZE = 51200;

	private boolean DEBUG = false;

	// localhost:9081/pdf?tableName=lab_displayparamter&createdOn="2016-07-15 12:44:51"&stopEnd="2018-07-15 13:34:42"
	// localhost:9081/tableName/lab_displayparamter/createdOn/2016-07-15
	// 12:44:51/stopEnd/2018-08-15 13:34:42"
	@Autowired
	private DataSource dataSource;

	@Autowired
	private LabDisplayParamterService LabDisplayParamterService;

	// @RequestMapping(value =
	// "/tableName/{tableName}/createdOn/{createdOn}/stopEnd/{stopEnd}")
	@RequestMapping(value = "/pdf")
	public void generateReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Enumeration<String> pNames = request.getParameterNames();

		while (pNames.hasMoreElements()) {
			String name = pNames.nextElement();
			String value = request.getParameter(name);

			if ("index".equals(name)) {
				int intValue = Integer.parseInt(value);
				if (intValue > 1)
					parameters.put(name, intValue);
			} else {
				parameters.put(name, value);
			}
		}
		String format = "pdf";
	    String tableName = (String) parameters.get("tableName");
		String createdOn = (String) parameters.get("createdOn");
		String stopEnd = (String) parameters.get("stopEnd");
		
		createdOn = createdOn.replace("\"","");
		stopEnd = stopEnd.replace("\"","");
		
		System.out.println(createdOn);
		System.out.println(stopEnd);

		String sql = "SELECT * FROM " + tableName + " WHERE CREATEDON BETWEEN " + createdOn + " AND " + stopEnd;
		parameters.put("tableName", tableName);
		parameters.put("createdOn", DateUtils.strToDate(createdOn));
		parameters.put("stopEnd", DateUtils.strToDate(stopEnd));
		//parameters.put("createdOn", createdOn);
		//parameters.put("stopEnd", stopEnd);

		System.out.println(sql);
		JRExporter exporter = null;
		String contentType = "";
		String fileSuffix = "";
		boolean download = true;

		if ("xls".equalsIgnoreCase(format)) { // xls
			contentType = "application/vnd.ms-excel";
			fileSuffix = ".xls";
			exporter = new JRXlsExporter();
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		} else if ("pdf".equalsIgnoreCase(format)) { // pdf
			contentType = "application/pdf";
			fileSuffix = ".pdf";
			exporter = new JRPdfExporter();
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UniGB-UCS2-H");
		} else { // html
			contentType = "text/html;charset=GBK";
			download = false;
			exporter = new JRHtmlExporter();
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		}

		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		response.setContentType(contentType);
		String template = getServletContext().getRealPath("Report/jrxml/" + tableName);
		String jrxml = template + ".jrxml";
		String jasper = template + ".jasper";

		System.out.println("jrxml: " + jrxml);
		System.out.println("jasper: " + jasper);
		// if (!new File(jasper).exists()) {
		JasperCompileManager.compileReportToFile(jrxml, jasper);// 根据jrxml模版生成相应的jasper文件
		// }
		File sourceFile = new File(jasper);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(sourceFile);// load什么东西？模版？
		JRBaseFiller filler = JRFiller.createFiller(jasperReport);
		Connection conn = dataSource.getConnection();// 连接数据库？连接jasper？
		JasperPrint print = filler.fill(parameters, conn);
		// JasperPrint print = JasperFillManager.fillReport(jasper, parameters,
		// conn);
		conn.close();
		if (!download) {
			JasperReportsUtils.render(exporter, print, response.getWriter());
		} else {
			response.setContentType("application/x-download");
			StringBuilder header = new StringBuilder("attachment; filename=\"");
			header.append(URLEncoder.encode(print.getName(), "UTF-8"));
			header.append(parameters.get("createdOn"));
			header.append("~");
			header.append(parameters.get("stopEnd"));
			header.append(fileSuffix);
			header.append("\"");
			response.setHeader("Content-Disposition", header.toString());
			ByteArrayOutputStream baos = new ByteArrayOutputStream(OUTPUT_BYTE_ARRAY_INITIAL_SIZE);
			JasperReportsUtils.render(exporter, print, baos);
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
			out.close();
			baos.close();
		}
	}

}