package com.gtmc.carapp.service.workorder.dto;

public class PersonAcctBo {

    /**
     * 个人姓名
     */
    private String personName;
    /**
     * 证件号码
     */
    private String idNo;
    /**
     * 证件类型
     * MAINLAND - 大陆身份证，15位或者17位+一位校验位
     * HONGKONG - 香港居民往来内地通行证，字母H或者h开头，后接8位或者10位数字
     * MACAO - 澳门居民往来内地通行证，字母M或者m开头，后接8位或者10位数字
     * TAIWAN - 台湾居民来往大陆通行证，8位或者10位数字
     * FOREIGN - 外籍，不校验
     * PASSPORT - 护照，【GgEePpSsDd】中任一开头，后接1位0-9数字或者【.】，再+7位数字，总计9
     * SOLDIER_IDNO - 军人身份证，15位或者17位+一位校验位
     * SOCIAL_SECURITY_CARD - 社会保障卡，15位或者17位+一位校验位
     * ARMED_POLICE_ID - 武装警察身份证件，不校验
     * RESIDENCE_BOOKLET - 户口簿，不校验
     * TEMPORARY_IDNO - 临时居民身份证，15位或者17位+一位校验位
     * FOREIGNER_PERMANENT_RESIDENCE_PERMIT - 外国人永久居留证，三位国籍代码（代码为字母，不区分大小写）+12位数字
     * OTHER - 其他，不校验
     */
    private String idType;
    /**
     * 个人邮箱
     */
    private String personEmail;
    /**
     * 个人手机号码
     */
    private String personMobile;
    /**
     * 个人地址
     */
    private String personAddress;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 所属公司
     */
    private String organization;
    /**
     * 职位
     */
    private String title;
    /**
     * 部门
     */
    private String department;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
