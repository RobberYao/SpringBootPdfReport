package com.springBootPdfReport.Dao;

import java.util.Date;
import java.util.List;

import com.springBootPdfReport.Model.LabDisplayParamter;

public interface LabDisplayParamterDao {

	LabDisplayParamter findOneById(int id);

	List<LabDisplayParamter> findListItems(String tableName, String createOn, String stopEnd);
}
