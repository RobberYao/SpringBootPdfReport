package com.springBootPdfReport.Model;

import java.util.Date;

/**
 * Conditions for JdbcSql
 * 
 * @author RobberYao 2017-08-14
 *
 */
public class LabDisplayConditions {

	private String inputProbeNumber;
	private String disProbeNumber;
	private Date createdTime;
	private Date stopTime;

	public LabDisplayConditions(String inputProbeNumber, String disProbeNumber, Date createdTime, Date stopTime) {
		super();
		this.inputProbeNumber = inputProbeNumber;
		this.disProbeNumber = disProbeNumber;
		this.createdTime = createdTime;
		this.stopTime = stopTime;
	}

	public LabDisplayConditions(String disProbeNumber, Date createdTime, Date stopTime) {
		super();
		this.disProbeNumber = disProbeNumber;
		this.createdTime = createdTime;
		this.stopTime = stopTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String getInputProbeNumber() {
		return inputProbeNumber;
	}

	public void setInputProbeNumber(String inputProbeNumber) {
		this.inputProbeNumber = inputProbeNumber;
	}

	public String getDisProbeNumber() {
		return disProbeNumber;
	}

	public void setDisProbeNumber(String disProbeNumber) {
		this.disProbeNumber = disProbeNumber;
	}

}
