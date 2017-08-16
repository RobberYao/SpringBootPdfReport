package com.springBootPdfReport.Action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springBootPdfReport.Service.LabDisplayParamterService;
import com.springBootPdfReport.Service.LabDisplayParamterServiceImpl;

@EnableAutoConfiguration
@Controller
public class pdfReportController {

	@Autowired
	LabDisplayParamterService labDisplayParamterService;

	@ResponseBody
	@RequestMapping(value = "/rpt", method = RequestMethod.GET)
	public String generateReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Enumeration<String> pNames = request.getParameterNames();
		System.out.println("1111");
		return "helloWorld";
	}

	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String testQuery(HttpServletRequest request, HttpServletResponse response) {

		String disTableName = labDisplayParamterService.findOneById(1).getDisplayTableName();
		System.out.println("=======  "+disTableName);
		return disTableName;
	}

}
