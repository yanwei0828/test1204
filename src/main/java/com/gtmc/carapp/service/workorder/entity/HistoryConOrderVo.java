package com.gtmc.carapp.service.workorder.entity;

public class HistoryConOrderVo {
    //创建时间
    private String createDate;
    //签署时间
    private String autographDate;
    //详情PDF连接
    private String htoInfoPdfUrl;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAutographDate() {
        return autographDate;
    }

    public void setAutographDate(String autographDate) {
        this.autographDate = autographDate;
    }

    public String getHtoInfoPdfUrl() {
        return htoInfoPdfUrl;
    }

    public void setHtoInfoPdfUrl(String htoInfoPdfUrl) {
        this.htoInfoPdfUrl = htoInfoPdfUrl;
    }
}
