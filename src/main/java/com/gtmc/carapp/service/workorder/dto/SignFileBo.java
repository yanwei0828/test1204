package com.gtmc.carapp.service.workorder.dto;

import java.util.List;

public class SignFileBo {

    /**
     * 合同ID
     */
    private String contractId;
    /**
     * 待签署文件二进制数据
     */
    private String bytes;
    /**
     * 待签署文件后缀。默认pdf
     */
    private String fileSuffix;
    /**
     * 乙方签章位置信息
     */
    private List<SignPosBo> signPos;
    /**
     * 甲方签章位置信息（双方签署时必填）
     */
    private List<SignPosBo> storeSignPos;
    /**
     * 签章类型
     * SINGLE - 单页签章
     * MULTI - 多页签章
     * EDGES - 签骑缝章
     * KEY - 关键字签
     */
    private String signType;
    /**
     * 文档编辑密码，当目标文件设置权限保护时必填
     */
    private String ownerPassword;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getOwnerPassword() {
        return ownerPassword;
    }

    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }

    public List<SignPosBo> getSignPos() {
        return signPos;
    }

    public void setSignPos(List<SignPosBo> signPos) {
        this.signPos = signPos;
    }

    public List<SignPosBo> getStoreSignPos() {
        return storeSignPos;
    }

    public void setStoreSignPos(List<SignPosBo> storeSignPos) {
        this.storeSignPos = storeSignPos;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }
}
