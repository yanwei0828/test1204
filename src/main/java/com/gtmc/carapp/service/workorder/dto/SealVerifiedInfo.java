package com.gtmc.carapp.service.workorder.dto;


public class SealVerifiedInfo {

    /**
     * 签署人证书名称
     */
    private String certName;
    /**
     * 签署人证书序列号
     */
    private String certSn;
    /**
     * 证书发布者名称
     */
    private String issuerCn;
    /**
     * 签署人证书有效期开始时间
     */
    private long startDate;
    /**
     * 签署人证书有效期结束时间
     */
    private long endDate;
    /**
     * 该文件中签名的验证结果
     */
    private boolean isValid;
    /**
     * 文档签署时间
     */
    private long signDate;

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertSn() {
        return certSn;
    }

    public void setCertSn(String certSn) {
        this.certSn = certSn;
    }

    public String getIssuerCn() {
        return issuerCn;
    }

    public void setIssuerCn(String issuerCn) {
        this.issuerCn = issuerCn;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public long getSignDate() {
        return signDate;
    }

    public void setSignDate(long signDate) {
        this.signDate = signDate;
    }
}
