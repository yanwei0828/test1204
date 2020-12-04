package com.gtmc.carapp.service.workorder.dto;


import org.springframework.web.multipart.MultipartFile;

public class UploadFileDto {

    /**
     * 上传文件
     */
    private String file;
    /**
     * 业务类型
     */
    private Integer businessType;
    /**
     * 用户手机号
     */
    private String phone;

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
