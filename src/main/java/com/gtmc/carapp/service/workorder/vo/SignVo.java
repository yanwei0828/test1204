package com.gtmc.carapp.service.workorder.vo;

/**
 * 签字详情页Vo
 */
public class SignVo {

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 已签署文件
     */
    private String signedFile;

    /**
     * 签名时间
     */
    private String signDate;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 是否存在历史 0、否，1、是。
     */
    private Integer isHistoryFile;

    /**
     * 状态
     *     0-待生成
     *     1-待签字
     *     2-已签字
     *     3-线下已签
     *     4-已失效
     */
    private Integer status;

    /**
     * 待签署文件
     */
    private String unsignFile;

    /**
     * 待签署html
     */
    private String unsignUrl;

    /**
     * 左下角的 Y  (单位 PX)
     */
    private Double yAxis;

    /**
     * 左下角的 X  (单位 PX)
     */
    private Double xAxis;

    /**
     * 签字所在页码
     */
    private Integer signPageNo;

    /**
     * 文件路径复处理的待签署文件
     */
    private String signingFilePath;
    
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getSignedFile() {
        return signedFile;
    }

    public void setSignedFile(String signedFile) {
        this.signedFile = signedFile;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getIsHistoryFile() {
        return isHistoryFile;
    }

    public void setIsHistoryFile(Integer isHistoryFile) {
        this.isHistoryFile = isHistoryFile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUnsignFile() {
        return unsignFile;
    }

    public void setUnsignFile(String unsignFile) {
        this.unsignFile = unsignFile;
    }

    public String getUnsignUrl() {
        return unsignUrl;
    }

    public void setUnsignUrl(String unsignUrl) {
        this.unsignUrl = unsignUrl;
    }

    public Double getyAxis() {
        return yAxis;
    }

    public void setyAxis(Double yAxis) {
        this.yAxis = yAxis;
    }

    public Double getxAxis() {
        return xAxis;
    }

    public void setxAxis(Double xAxis) {
        this.xAxis = xAxis;
    }

	public String getSigningFilePath() {
		return signingFilePath;
	}

	public void setSigningFilePath(String signingFilePath) {
		this.signingFilePath = signingFilePath;
	}

    public Integer getSignPageNo() {
        return signPageNo;
    }

    public void setSignPageNo(Integer signPageNo) {
        this.signPageNo = signPageNo;
    }
}
