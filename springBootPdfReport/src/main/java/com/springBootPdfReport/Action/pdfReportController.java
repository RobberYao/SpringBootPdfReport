package com.springBootPdfReport.Action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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
import com.springBootPdfReport.Utils.DateSourceBaseFactory;
import com.springBootPdfReport.Utils.DateUtils;

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
	public void testQuery(HttpServletRequest request, HttpServletResponse response) throws ParseException {

		// String disProbeNumber =
		// labDisplayParamterService.findOneById(1).getDisProbeNumber();
		// System.out.println("======= "+disProbeNumber);
		// return disProbeNumber;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
		String createdOn = "2017-07-18 10:09:28";
		String stopEnd = "2017-07-19 10:09:28";

		List list1 = labDisplayParamterService.findMore("lab.lab_displayparamter0101", DateUtils.strToDate(createdOn),
				DateUtils.strToDate(stopEnd));
		System.out.println(list1.size());

	}

}
