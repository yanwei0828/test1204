package com.gtmc.carapp.service.workorder.dto;


import com.gtmc.carapp.service.workorder.dto.base.BaseExoValidateDto;

public class AuthCertificaInfoStatusDto extends BaseExoValidateDto {


    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 粒子id
     * 1-身份证
     * 9-驾驶证
     * 14-购车车主
     * 5-机动车登记证认证
     * 4-行驶证
     * 13-购车实名
     * 16-简项实名
     */
    private String particleId;
    /**
     * 机动车登记认证类型
     * 当粒子为5 需传 1  机动车线下认证 2  机动车线下认证
     */
    private String type;
    /**
     * 车辆编码
     * 当粒子为5或14、4时 须上传
     */
    private String vin;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParticleId() {
        return particleId;
    }

    public void setParticleId(String particleId) {
        this.particleId = particleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
