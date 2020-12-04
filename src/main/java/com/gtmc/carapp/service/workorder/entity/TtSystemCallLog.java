package com.gtmc.carapp.service.workorder.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tt_system_call_log")
public class TtSystemCallLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 0:本服务调用,1:被第三方调用
     */
    private Integer type;

    @Column(name = "call_url")
    private String callUrl;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "sync_param")
    private String syncParam;

    @Column(name = "response_result")
    private String responseResult;

    @Column(name = "call_status")
    private Integer callStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCallUrl() {
		return callUrl;
	}

	public void setCallUrl(String callUrl) {
		this.callUrl = callUrl;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSyncParam() {
		return syncParam;
	}

	public void setSyncParam(String syncParam) {
		this.syncParam = syncParam;
	}

	public String getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}

	public Integer getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(Integer callStatus) {
		this.callStatus = callStatus;
	}

}