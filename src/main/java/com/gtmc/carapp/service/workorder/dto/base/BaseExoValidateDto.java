package com.gtmc.carapp.service.workorder.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "外部系统认证Dto")
public class BaseExoValidateDto {
    /**
     * 系统ID
     */
    @ApiModelProperty(value = "系统ID")
    private String appId;
    /**
     * 随机数
     */
    @ApiModelProperty(value = "随机数")
    private String nonce;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private String timestamp;
    /**
     * 签名
     */
    @ApiModelProperty(value = "签名")
    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
