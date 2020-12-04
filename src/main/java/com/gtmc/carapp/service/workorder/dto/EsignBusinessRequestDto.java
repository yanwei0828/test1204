package com.gtmc.carapp.service.workorder.dto;

import com.gtmc.carapp.service.workorder.dto.base.BaseExoValidateDto;

public class EsignBusinessRequestDto extends BaseExoValidateDto {

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
     * 已签署文件
     */
    private String signedFile;
    
    /**
     * 来源类型 1-APP 2 -H5
     */
    private Integer sourceType;

    /**
     * 业务参数
     *      1：合同历史签署文件页——页码
     */
    private Integer businessParam;

    /**
     * 业务下接口
     *      1：合同历史
     */
    private Integer interfaceType;
    
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

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

	public String getSignedFile() {
		return signedFile;
	}

	public void setSignedFile(String signedFile) {
		this.signedFile = signedFile;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

    public Integer getBusinessParam() {
        return businessParam;
    }

    public void setBusinessParam(Integer businessParam) {
        this.businessParam = businessParam;
    }

    public Integer getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Integer interfaceType) {
        this.interfaceType = interfaceType;
    }
}
