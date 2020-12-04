package com.gtmc.carapp.service.workorder.dto;

public class SubmitSignFileDto {

    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 施工单id
     */
    private Long constructionOrderId;
    /**
     * 工单编号
     */
    private String srvOrderNo;
    /**
     * HTML模板文件地址/文件流
     */
    private String templateFile;
    /**
     * 签名图片地址/文件流
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


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSrvOrderNo() {
        return srvOrderNo;
    }

    public void setSrvOrderNo(String srvOrderNo) {
        this.srvOrderNo = srvOrderNo;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getSignatureFile() {
        return signatureFile;
    }

    public void setSignatureFile(String signatureFile) {
        this.signatureFile = signatureFile;
    }

    public Long getConstructionOrderId() {
        return constructionOrderId;
    }

    public void setConstructionOrderId(Long constructionOrderId) {
        this.constructionOrderId = constructionOrderId;
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
}
