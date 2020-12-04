package com.gtmc.carapp.service.workorder.entity;

/**
 * 施工单主体Vo
 */
public class EsignConOrderVo {

    /**
     * 主键
     */
    private Long conOrderNumber;

    /**
     * 工单编号
     */
    private String srvOrderNo;

    /**
     * 未签字文件地址
     */
    private String unsignFileUrl;

    /**
     * 已签字文件地址
     */
    private String signedFileUrl;

    /**
     * 状态
     * 0-待生成;1-待签字;2-已签字;3-线下已签;4-已失效;
     */
    private Integer status;

    /**
     * 删除标识
     * 1-已删除;0-有效
     */
    private Integer deleteFlag;

    /**
     * 签名时间
     */
    private String signDate;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 更新时间
     */
    private String updateDate;

    /**
     * 是否存在历史已签署文件 0否 1是
     */
    private Integer isHistoryFile;

    public Long getConOrderNumber() {
        return conOrderNumber;
    }

    public void setConOrderNumber(Long conOrderNumber) {
        this.conOrderNumber = conOrderNumber;
    }

    public String getSrvOrderNo() {
        return srvOrderNo;
    }

    public void setSrvOrderNo(String srvOrderNo) {
        this.srvOrderNo = srvOrderNo;
    }

    public String getUnsignFileUrl() {
        return unsignFileUrl;
    }

    public void setUnsignFileUrl(String unsignFileUrl) {
        this.unsignFileUrl = unsignFileUrl;
    }

    public String getSignedFileUrl() {
        return signedFileUrl;
    }

    public void setSignedFileUrl(String signedFileUrl) {
        this.signedFileUrl = signedFileUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsHistoryFile() {
        return isHistoryFile;
    }

    public void setIsHistoryFile(Integer isHistoryFile) {
        this.isHistoryFile = isHistoryFile;
    }
}
