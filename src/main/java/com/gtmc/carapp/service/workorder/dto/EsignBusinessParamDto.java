package com.gtmc.carapp.service.workorder.dto;

import com.gtmc.carapp.service.workorder.dto.base.BaseExoValidateDto;

public class EsignBusinessParamDto extends BaseExoValidateDto {

    /**
     * app版本号
     */
    private String appVersion;
    /**
     * 业务id
     */
    private String businessId;
    /**
     * 业务类型
     */
    private Integer businessType;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 签名图片(文件流)
     */
    private String signatrueFile;
    
    /**
     * 未签署文件路径
     */
    private String templateFile;

    /**
     * 左下角的 Y  (单位 PX)
     */
    private float yAxis;

    /**
     * 左下角的 X  (单位 PX)
     */
    private float xAxis;

    /**
     * 签字所在页码
     */
    private Integer signPageNo;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
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

    public String getSignatrueFile() {
        return signatrueFile;
    }

    public void setSignatrueFile(String signatrueFile) {
        this.signatrueFile = signatrueFile;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

	public float getyAxis() {
		return yAxis;
	}

	public void setyAxis(float yAxis) {
		this.yAxis = yAxis;
	}

	public float getxAxis() {
		return xAxis;
	}

	public void setxAxis(float xAxis) {
		this.xAxis = xAxis;
	}

	public String getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

    public Integer getSignPageNo() {
        return signPageNo;
    }

    public void setSignPageNo(Integer signPageNo) {
        this.signPageNo = signPageNo;
    }
}
