package com.gtmc.carapp.service.workorder.dto;

public class EsignRequestDto {

    /**
     * 签署账户类型
     * PERSON-个人
     * ORGANIZATION-企业
     */
    private String acctType;
    /**
     * 签署类型
     * UNILATERAL-单方签署（只存在用户意愿签字）
     * BOTH_SIDES-双方签署（用户和门店方双方签署，即甲乙双方签署）
     */
    private String signatoryType;
    /**
     * 个人/企业签署者账户ID
     */
    private String accountId;
    /**
     * 甲方签署账户ID
     * 当signatoryType为双方签署时，storeAccountId和storeOrgAcctBo有一个必填
     */
    private String storeAccountId;
    /**
     * 个人签署账户信息
     * 当acctType为个人并且accountId为空时必填
     */
    private PersonAcctBo personAcct;
    /**
     * 企业签署账户信息
     * 当acctType为企业并且accountId为空时必填
     */
    private String orgAcct;
    /**
     * 印章信息
     * 当accountId为空时必填，当accountId不为空时，有值即更新账户印章
     */
    private SealDataBo sealData;
    /**
     * 甲方账户信息
     * 当signatoryType 为双方签署时，storeAccountId和storeOrgAcctBo有一个必填
     */
    private String storeOrgAcct;
    /**
     * 甲方印章信息
     * 当signatoryType 为双方签署时，有值则更新印章信息，为空则使用原来的
     */
    private SealDataBo storeSealData;
    /**
     * 待签署文件信息
     */
    private SignFileBo signFile;

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getSignatoryType() {
        return signatoryType;
    }

    public void setSignatoryType(String signatoryType) {
        this.signatoryType = signatoryType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(String storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public String getOrgAcct() {
        return orgAcct;
    }

    public void setOrgAcct(String orgAcct) {
        this.orgAcct = orgAcct;
    }

    public String getStoreOrgAcct() {
        return storeOrgAcct;
    }

    public void setStoreOrgAcct(String storeOrgAcct) {
        this.storeOrgAcct = storeOrgAcct;
    }

    public PersonAcctBo getPersonAcct() {
        return personAcct;
    }

    public void setPersonAcct(PersonAcctBo personAcct) {
        this.personAcct = personAcct;
    }

    public SealDataBo getSealData() {
        return sealData;
    }

    public void setSealData(SealDataBo sealData) {
        this.sealData = sealData;
    }

    public SealDataBo getStoreSealData() {
        return storeSealData;
    }

    public void setStoreSealData(SealDataBo storeSealData) {
        this.storeSealData = storeSealData;
    }

    public SignFileBo getSignFile() {
        return signFile;
    }

    public void setSignFile(SignFileBo signFile) {
        this.signFile = signFile;
    }
}
