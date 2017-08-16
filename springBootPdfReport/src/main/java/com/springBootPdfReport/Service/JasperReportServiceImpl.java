package com.springBootPdfReport.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperReportServiceImpl implements JasperReportService {

	// 加载报表模版
	public JasperReport loadTemplate(String template) {
		try {
			JasperReport jasper = (JasperReport) JRLoader.loadObjectFromFile(template);
			return jasper;
		} catch (JRException e) {
			throw new RuntimeException("JRException");
		}
	}

}
