package com.gtmc.carapp.service.workorder.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * E签宝注册表
 */
@Table(name = "tt_esign_account")
public class TtEsignAccount {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 注册用户手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 注册用户ID
     */
    @Column(name = "user_id")
    private int userId;

    /**
     * e签宝授权账号
     */
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
}
