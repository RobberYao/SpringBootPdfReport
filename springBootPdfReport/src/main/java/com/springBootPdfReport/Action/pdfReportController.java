package com.springBootPdfReport.Action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class pdfReportController {

	public static void main(String[] args) {
		SpringApplication.run(pdfReportController.class, args);

	}

	@ResponseBody
	@RequestMapping(value = "/rpt", method = RequestMethod.GET)
	public String generateReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Enumeration<String> pNames = request.getParameterNames();
		System.out.println("1111");
		return "helloWorld";

	}
	
	
	
	
	
	
	

}
