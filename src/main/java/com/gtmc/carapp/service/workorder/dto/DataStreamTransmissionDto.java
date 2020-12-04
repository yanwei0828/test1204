package com.gtmc.carapp.service.workorder.dto;

import com.gtmc.carapp.service.workorder.dto.base.BaseExoValidateDto;

public class DataStreamTransmissionDto {

    /**
     * app版本号
     */
    private String appVersion;

    /**
     * 业务id（合同号/协议主键ID/施工单号）
     */
    private String businessId;

    /**
     * 业务类型
     *      1：合同详情页
     *      2：协议详情页
     *      3：合同历史签署文件页
     */
    private Integer businessType;

    /**
     * 业务参数
     *      1：合同历史签署文件页——页码
     */
    private Integer businessParam;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户id
     */
    private Integer userId;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getBusinessParam() {
        return businessParam;
    }

    public void setBusinessParam(Integer businessParam) {
        this.businessParam = businessParam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
