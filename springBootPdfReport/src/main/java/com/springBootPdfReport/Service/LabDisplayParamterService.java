package com.springBootPdfReport.Service;

import java.util.Date;
import java.util.List;

import com.springBootPdfReport.Model.LabDisplayConditions;
import com.springBootPdfReport.Model.LabDisplayParamter;

public interface LabDisplayParamterService {

	LabDisplayConditions getLabDisplayConditions(String disProbeNumber, Date createdTime, Date stopTime)
			throws Exception;

	LabDisplayParamter findOneById(int id);

	List<LabDisplayParamter> findMore(String tableName, Date createdOn, Date stopEnd);

}
