package com.gtmc.carapp.service.workorder.dto;

public class SealDataBo {

    /**
     * 印章/签名图片的base64数据
     */
    private String sealDataStr;
    /**
     * 个人印章模板类型
     */
    private String personTemplateType;
    /**
     * 企业印章模板类型
     */
    private String organizationTemplateType;
    /**
     * 印章颜色
     */
    private String sealColor;
    /**
     * 生成印章中的横向文内容
     */
    private String sealName;
    /**
     * 生成印章中的下弦文内容
     */
    private String securityCode;

    public String getSealDataStr() {
        return sealDataStr;
    }

    public void setSealDataStr(String sealDataStr) {
        this.sealDataStr = sealDataStr;
    }

    public String getPersonTemplateType() {
        return personTemplateType;
    }

    public void setPersonTemplateType(String personTemplateType) {
        this.personTemplateType = personTemplateType;
    }

    public String getOrganizationTemplateType() {
        return organizationTemplateType;
    }

    public void setOrganizationTemplateType(String organizationTemplateType) {
        this.organizationTemplateType = organizationTemplateType;
    }

    public String getSealColor() {
        return sealColor;
    }

    public void setSealColor(String sealColor) {
        this.sealColor = sealColor;
    }

    public String getSealName() {
        return sealName;
    }

    public void setSealName(String sealName) {
        this.sealName = sealName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
