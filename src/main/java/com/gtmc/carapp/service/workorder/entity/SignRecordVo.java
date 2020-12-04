package com.gtmc.carapp.service.workorder.entity;

/**
 * 历史签署Vo
 */
public class SignRecordVo {

    /**
     * 合同号
     */
    private String businessId;

    /**
     * 手绘url
     */
    private String signPdfUrl;

    /**
     * 签名后url
     */
    private String signContractPdfUrl;

    /**
     * 是否有效 	0否1是
     */
    private Integer deleteFlag;

    /**
     * 	业务类型 1合同 2协议
     */
    private Integer businessType;

    /**
     * 签署时间
     */
    private String signDate;

    private Integer createBy;

    private String createDate;

    private Integer updateBy;

    private String updateDate;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getSignPdfUrl() {
        return signPdfUrl;
    }

    public void setSignPdfUrl(String signPdfUrl) {
        this.signPdfUrl = signPdfUrl;
    }

    public String getSignContractPdfUrl() {
        return signContractPdfUrl;
    }

    public void setSignContractPdfUrl(String signContractPdfUrl) {
        this.signContractPdfUrl = signContractPdfUrl;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }
}
