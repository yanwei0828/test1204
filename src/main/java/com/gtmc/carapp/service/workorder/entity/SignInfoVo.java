package com.gtmc.carapp.service.workorder.entity;

import java.util.Date;

/**
 * 签字详情页Vo
 */
public class SignInfoVo {

    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 展示URL（待签、已签）
     */
    private String showUrl;

    /**
     * 状态 1、未签，2、已签。
     */
    private Integer status;

    /**
     * 是否存在历史 0、否，1、是。
     */
    private Integer isHistoryFile;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 签名时间
     */
    private Date signDate;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsHistoryFile() {
        return isHistoryFile;
    }

    public void setIsHistoryFile(Integer isHistoryFile) {
        this.isHistoryFile = isHistoryFile;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
}
