package com.gtmc.carapp.service.workorder.dto;

import com.gtmc.carapp.service.workorder.dto.base.BaseExoValidateDto;
import org.springframework.web.bind.annotation.RequestParam;

public class OpenSignDto extends BaseExoValidateDto {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户手机号
     */
    private String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
