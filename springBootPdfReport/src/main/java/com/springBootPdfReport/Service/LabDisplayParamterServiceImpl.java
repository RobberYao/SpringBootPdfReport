package com.springBootPdfReport.Service;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootPdfReport.Dao.LabDisplayParamterDaoImpl;
import com.springBootPdfReport.Model.LabDisplayConditions;
import com.springBootPdfReport.Model.LabDisplayParamter;
@Service
public class LabDisplayParamterServiceImpl implements LabDisplayParamterService {
	@Autowired
	LabDisplayParamterDaoImpl LabDisplayParamterDaoImpl;

	public LabDisplayConditions getLabDisplayConditions(String disProbeNumber, Date createdTime, Date stopTime)
			throws Exception {

		if (StringUtils.isBlank(disProbeNumber)) {
			throw new Exception("disProbeNumber is null please check");
		}

		return new LabDisplayConditions(disProbeNumber, createdTime, stopTime);
	}

	@Override
	public LabDisplayParamter findOneById(int id) {
		
		LabDisplayParamter display=LabDisplayParamterDaoImpl.findOneById(id);
		
		return display;
	}

}
