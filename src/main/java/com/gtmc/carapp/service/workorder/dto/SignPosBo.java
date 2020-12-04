package com.gtmc.carapp.service.workorder.dto;

public class SignPosBo {

    /**
     * 定位类型
     * COORDINATE - 坐标定位
     * KEYWORD - 关键字定位
     * 默认COORDINATE，SignType为关键字签署的时候，必须为KEYWORD
     */
    private String posType;
    /**
     * 签署页码
     * 若为多页签章，支持页码格式“1-3,5,8“，若为坐标定位时，不可空
     */
    private String posPage;
    /**
     * 签署位置X坐标
     * 若为关键字定位，相对于关键字的X坐标偏移量，默认0
     */
    private float posX;
    /**
     * 签署位置Y坐标
     * 若为关键字定位，相对于关键字的Y坐标偏移量，默认0
     */
    private float posY;
    /**
     * 关键字
     * 仅限关键字签章时有效，若为关键字定位时，不可空；
     * 关键字不可设置特殊字符
     */
    private String key;
    /**
     * 印章展现宽度
     * 为空时默认159
     */
    private float width;
    /**
     * 是否是二维码签署
     * 默认为false。二维码签署不支持骑缝签和多页签
     */
    private boolean qrcodeSign;
    /**
     * 是否是作废签签署
     * 默认为false
     */
    private boolean cacellingSign;
    /**
     * 是否显示本地签署时间
     * 需要width设置92以上才可以看到时间
     */
    private boolean addSignTime;

    public String getPosType() {
        return posType;
    }

    public void setPosType(String posType) {
        this.posType = posType;
    }

    public String getPosPage() {
        return posPage;
    }

    public void setPosPage(String posPage) {
        this.posPage = posPage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public boolean isQrcodeSign() {
        return qrcodeSign;
    }

    public void setQrcodeSign(boolean qrcodeSign) {
        this.qrcodeSign = qrcodeSign;
    }

    public boolean isCacellingSign() {
        return cacellingSign;
    }

    public void setCacellingSign(boolean cacellingSign) {
        this.cacellingSign = cacellingSign;
    }

    public boolean isAddSignTime() {
        return addSignTime;
    }

    public void setAddSignTime(boolean addSignTime) {
        this.addSignTime = addSignTime;
    }
}
