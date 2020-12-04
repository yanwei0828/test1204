package com.gtmc.carapp.service.workorder.dto;


import java.util.List;

public class SignUncertifiedRspBo {

    /**
     * 个人/企业（乙方）签署者账户ID
     */
    private String accountId;
    /**
     * 门店（甲方）签署账户ID
     */
    private String storeAccountId;
    /**
     * 签署记录ID
     */
    private Long signId;
    /**
     * 已签署的文件二进制数据
     */
    private byte[] bytes;
    /**
     * 验签结果
     */
    private List<SealVerifiedInfo> sealVerifiedInfos;

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

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public List<SealVerifiedInfo> getSealVerifiedInfos() {
        return sealVerifiedInfos;
    }

    public void setSealVerifiedInfos(List<SealVerifiedInfo> sealVerifiedInfos) {
        this.sealVerifiedInfos = sealVerifiedInfos;
    }
}
