package com.springBootPdfReport.Service;


import java.util.Date;

import com.springBootPdfReport.Model.LabDisplayConditions;


public interface LabDisplayParamterManager {

	LabDisplayConditions getLabDisplayConditions(String disProbeNumber, Date createdTime, Date stopTime)
			throws Exception;

}
