package com.springBootPdfReport.Service;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

import com.springBootPdfReport.Model.LabDisplayConditions;

public class LabDisplayParamterManagerImpl implements LabDisplayParamterManager {

	public LabDisplayConditions getLabDisplayConditions(String disProbeNumber, Date createdTime, Date stopTime)
			throws Exception {

		if (StringUtils.isBlank(disProbeNumber)) {
			throw new Exception("disProbeNumber is null please check");
		}

		return new LabDisplayConditions(disProbeNumber, createdTime, stopTime);
	}

}
