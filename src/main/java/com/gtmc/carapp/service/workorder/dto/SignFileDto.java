package com.gtmc.carapp.service.workorder.dto;


public class SignFileDto {

    /**
     * 未签署文件路径
     */
    private String templateFile;
    /**
     * 签名图片路径
     */
    private String signatureFile;
    /**
     * 签署位置X坐标
     */
    private float posX;
    /**
     * 签署位置Y坐标
     */
    private float posY;
    /**
     * 签字所在页码
     */
    private Integer signPageNo;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 业务id
     */
    private String businessId;

    public String getSignatureFile() {
        return signatureFile;
    }

    public void setSignatureFile(String signatureFile) {
        this.signatureFile = signatureFile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Integer getSignPageNo() {
        return signPageNo;
    }

    public void setSignPageNo(Integer signPageNo) {
        this.signPageNo = signPageNo;
    }
}
