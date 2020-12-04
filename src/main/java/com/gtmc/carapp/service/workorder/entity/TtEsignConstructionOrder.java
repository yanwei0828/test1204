package com.gtmc.carapp.service.workorder.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 施工单详情表
 */
@Table(name = "tt_esign_construction_order")
public class TtEsignConstructionOrder {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 工单编号
     */
    @Column(name = "srv_order_no")
    private String srvOrderNo;

    /**
     * 未签字文件地址
     */
    @Column(name = "unsign_file_url")
    private String unsignFileUrl;

    /**
     * 已签字文件地址
     */
    @Column(name = "signed_file_url")
    private String signedFileUrl;

    /**
     * 状态
     * 0-待生成;1-待签字;2-已签字;3-线下已签;4-已失效;
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 删除标识
     * 1-已删除;0-有效
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 签名时间
     */
    @Column(name = "sign_date")
    private Date signDate;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 意愿状态
     * 0-不同意; 1-同意
     */
    @Column(name = "willing_to_agree")
    private Integer willingToAgree;

    /**
     * 意愿用户id
     */
    @Column(name = "will_user")
    private Integer willUser;

    /**
     * 意愿同意时间
     */
    @Column(name = "will_date")
    private Date willDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrvOrderNo() {
        return srvOrderNo;
    }

    public void setSrvOrderNo(String srvOrderNo) {
        this.srvOrderNo = srvOrderNo;
    }

    public String getUnsignFileUrl() {
        return unsignFileUrl;
    }

    public void setUnsignFileUrl(String unsignFileUrl) {
        this.unsignFileUrl = unsignFileUrl;
    }

    public String getSignedFileUrl() {
        return signedFileUrl;
    }

    public void setSignedFileUrl(String signedFileUrl) {
        this.signedFileUrl = signedFileUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getWillingToAgree() {
        return willingToAgree;
    }

    public void setWillingToAgree(Integer willingToAgree) {
        this.willingToAgree = willingToAgree;
    }

    public Integer getWillUser() {
        return willUser;
    }

    public void setWillUser(Integer willUser) {
        this.willUser = willUser;
    }

    public Date getWillDate() {
        return willDate;
    }

    public void setWillDate(Date willDate) {
        this.willDate = willDate;
    }
}
