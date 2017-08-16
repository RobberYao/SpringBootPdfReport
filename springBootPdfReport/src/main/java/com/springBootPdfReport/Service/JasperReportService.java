package com.springBootPdfReport.Service;

import net.sf.jasperreports.engine.JasperReport;

public interface JasperReportService {
	
	JasperReport loadTemplate(String template);
	
}
