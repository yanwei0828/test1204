package com.gtmc.carapp.service.workorder.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tt_api_access_allot")
public class TtApiAccessAllot {
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
     * appKey
     */
    @Column(name = "APP_KEY")
    private String appKey;

    /**
     * 使用范围
     */
    @Column(name = "USE_SCOPE")
    private String useScope;

    /**
     * 说明
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 创建者
     */
    @Column(name = "CREATE_BY")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 更新者
     */
    @Column(name = "UPDATE_BY")
    private String updateBy;

    /**
     * 更新时间
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
     * 获取appId
     *
     * @return APP_ID - appId
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置appId
     *
     * @param appId appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取appKey
     *
     * @return APP_KEY - appKey
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * 设置appKey
     *
     * @param appKey appKey
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * 获取使用范围
     *
     * @return USE_SCOPE - 使用范围
     */
    public String getUseScope() {
        return useScope;
    }

    /**
     * 设置使用范围
     *
     * @param useScope 使用范围
     */
    public void setUseScope(String useScope) {
        this.useScope = useScope;
    }

    /**
     * 获取说明
     *
     * @return DESCRIPTION - 说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置说明
     *
     * @param description 说明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建者
     *
     * @return CREATE_BY - 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_DATE - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新者
     *
     * @return UPDATE_BY - 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新者
     *
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_DATE - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}