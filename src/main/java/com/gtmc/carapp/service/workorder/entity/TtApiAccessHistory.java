package com.gtmc.carapp.service.workorder.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tt_api_access_history")
public class TtApiAccessHistory {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * appId
     */
    @Column(name = "APP_ID")
    private Integer appId;

    /**
     * �����
     */
    @Column(name = "NONCE")
    private String nonce;

    /**
     * ʱ���
     */
    @Column(name = "TIMESTAMP")
    private String timestamp;

    /**
     * ǩ��
     */
    @Column(name = "SIGNATURE")
    private String signature;

    /**
     * ������
     */
    @Column(name = "CREATE_BY")
    private String createBy;

    /**
     * ����ʱ��
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * ������
     */
    @Column(name = "UPDATE_BY")
    private String updateBy;

    /**
     * ����ʱ��
     */
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * ��ȡappId
     *
     * @return APP_ID - appId
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * ����appId
     *
     * @param appId appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * ��ȡ�����
     *
     * @return NONCE - �����
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * ���������
     *
     * @param nonce �����
     */
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * ��ȡʱ���
     *
     * @return TIMESTAMP - ʱ���
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * ����ʱ���
     *
     * @param timestamp ʱ���
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * ��ȡǩ��
     *
     * @return SIGNATURE - ǩ��
     */
    public String getSignature() {
        return signature;
    }

    /**
     * ����ǩ��
     *
     * @param signature ǩ��
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * ��ȡ������
     *
     * @return CREATE_BY - ������
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * ���ô�����
     *
     * @param createBy ������
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * ��ȡ����ʱ��
     *
     * @return CREATE_DATE - ����ʱ��
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * ���ô���ʱ��
     *
     * @param createDate ����ʱ��
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * ��ȡ������
     *
     * @return UPDATE_BY - ������
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * ���ø�����
     *
     * @param updateBy ������
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * ��ȡ����ʱ��
     *
     * @return UPDATE_DATE - ����ʱ��
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * ���ø���ʱ��
     *
     * @param updateDate ����ʱ��
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}