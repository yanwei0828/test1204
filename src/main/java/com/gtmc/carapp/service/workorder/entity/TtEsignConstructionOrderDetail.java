package com.gtmc.carapp.service.workorder.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 施工单详情表
 */
@Table(name = "tt_esign_construction_order_detail")
public class TtEsignConstructionOrderDetail {

    @Column(name = "construction_order_id")
    private Long constructionOrderId;

    @Column(name = "sa_name")
    private String saName;

    @Column(name = "srv_type_name")
    private String srvTypeName;

    @Column(name = "srv_order_no")
    private String srvOrderNo;

    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "regist_no")
    private String registNo;

    @Column(name = "vin")
    private String vin;

    @Column(name = "car_model_name")
    private String carModelName;

    @Column(name = "twc_user_name")
    private String twcUserName;

    @Column(name = "twc_tel1")
    private String twcTel1;

    @Column(name = "twc_tel2")
    private String twcTel2;

    @Column(name = "buy_car_date")
    private Date buyCarDate;

    @Column(name = "insurecomcode")
    private String insurecomcode;

    @Column(name = "effective_date_start")
    private Date effectiveDateStart;

    @Column(name = "effective_date_end")
    private Date effectiveDateEnd;

    @Column(name = "latest_srv_date")
    private Date latestSrvDate;

    @Column(name = "latest_srv_type_name")
    private String latestSrvTypeName;

    @Column(name = "latest_mileage")
    private String latestMileage;

    @Column(name = "twc_result")
    private String twcResult;

    @Column(name = "project_total_amount")
    private String projectTotalAmount;

    @Column(name = "project_discount_amount")
    private String projectDiscountAmount;

    @Column(name = "warranty_cost")
    private String warrantyCost;

    @Column(name = "project_cost")
    private String projectCost;

    @Column(name = "projects_list")
    private String projectsList;

    @Column(name = "parts_total_amount")
    private String partsTotalAmount;

    @Column(name = "part_discount_amount")
    private String partDiscountAmount;

    @Column(name = "warranty_part")
    private String warrantyPart;

    @Column(name = "parts_cost")
    private String partsCost;

    @Column(name = "parts_list")
    private String partsList;

    @Column(name = "order_price")
    private String orderPrice;

    @Column(name = "prercv_amount")
    private String prercvAmount;

    @Column(name = "other_discount")
    private String otherDiscount;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "sa_phone")
    private String saPhone;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "appointment_tel_no")
    private String appointmentTelNo;

    @Column(name = "sales_tel_no")
    private String salesTelNo;

    @Column(name = "all_day_rescue_tel_no")
    private String allDayRescueTelNo;

    @Column(name = "svcin_name")
    private String svcinName;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public Long getConstructionOrderId() {
        return constructionOrderId;
    }

    public void setConstructionOrderId(Long constructionOrderId) {
        this.constructionOrderId = constructionOrderId;
    }

    public String getSaName() {
        return saName;
    }

    public void setSaName(String saName) {
        this.saName = saName;
    }

    public String getSrvTypeName() {
        return srvTypeName;
    }

    public void setSrvTypeName(String srvTypeName) {
        this.srvTypeName = srvTypeName;
    }

    public String getSrvOrderNo() {
        return srvOrderNo;
    }

    public void setSrvOrderNo(String srvOrderNo) {
        this.srvOrderNo = srvOrderNo;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getRegistNo() {
        return registNo;
    }

    public void setRegistNo(String registNo) {
        this.registNo = registNo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public String getTwcUserName() {
        return twcUserName;
    }

    public void setTwcUserName(String twcUserName) {
        this.twcUserName = twcUserName;
    }

    public Date getBuyCarDate() {
        return buyCarDate;
    }

    public void setBuyCarDate(Date buyCarDate) {
        this.buyCarDate = buyCarDate;
    }

    public String getInsurecomcode() {
        return insurecomcode;
    }

    public void setInsurecomcode(String insurecomcode) {
        this.insurecomcode = insurecomcode;
    }

    public Date getEffectiveDateStart() {
        return effectiveDateStart;
    }

    public void setEffectiveDateStart(Date effectiveDateStart) {
        this.effectiveDateStart = effectiveDateStart;
    }

    public Date getEffectiveDateEnd() {
        return effectiveDateEnd;
    }

    public void setEffectiveDateEnd(Date effectiveDateEnd) {
        this.effectiveDateEnd = effectiveDateEnd;
    }

    public Date getLatestSrvDate() {
        return latestSrvDate;
    }

    public void setLatestSrvDate(Date latestSrvDate) {
        this.latestSrvDate = latestSrvDate;
    }

    public String getLatestSrvTypeName() {
        return latestSrvTypeName;
    }

    public void setLatestSrvTypeName(String latestSrvTypeName) {
        this.latestSrvTypeName = latestSrvTypeName;
    }

    public String getLatestMileage() {
        return latestMileage;
    }

    public void setLatestMileage(String latestMileage) {
        this.latestMileage = latestMileage;
    }

    public String getTwcResult() {
        return twcResult;
    }

    public void setTwcResult(String twcResult) {
        this.twcResult = twcResult;
    }

    public String getProjectTotalAmount() {
        return projectTotalAmount;
    }

    public void setProjectTotalAmount(String projectTotalAmount) {
        this.projectTotalAmount = projectTotalAmount;
    }

    public String getProjectDiscountAmount() {
        return projectDiscountAmount;
    }

    public void setProjectDiscountAmount(String projectDiscountAmount) {
        this.projectDiscountAmount = projectDiscountAmount;
    }

    public String getWarrantyCost() {
        return warrantyCost;
    }

    public void setWarrantyCost(String warrantyCost) {
        this.warrantyCost = warrantyCost;
    }

    public String getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(String projectCost) {
        this.projectCost = projectCost;
    }

    public String getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(String projectsList) {
        this.projectsList = projectsList;
    }

    public String getPartsTotalAmount() {
        return partsTotalAmount;
    }

    public void setPartsTotalAmount(String partsTotalAmount) {
        this.partsTotalAmount = partsTotalAmount;
    }

    public String getPartDiscountAmount() {
        return partDiscountAmount;
    }

    public void setPartDiscountAmount(String partDiscountAmount) {
        this.partDiscountAmount = partDiscountAmount;
    }

    public String getWarrantyPart() {
        return warrantyPart;
    }

    public void setWarrantyPart(String warrantyPart) {
        this.warrantyPart = warrantyPart;
    }

    public String getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(String partsCost) {
        this.partsCost = partsCost;
    }

    public String getPartsList() {
        return partsList;
    }

    public void setPartsList(String partsList) {
        this.partsList = partsList;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPrercvAmount() {
        return prercvAmount;
    }

    public void setPrercvAmount(String prercvAmount) {
        this.prercvAmount = prercvAmount;
    }

    public String getOtherDiscount() {
        return otherDiscount;
    }

    public void setOtherDiscount(String otherDiscount) {
        this.otherDiscount = otherDiscount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSaPhone() {
        return saPhone;
    }

    public void setSaPhone(String saPhone) {
        this.saPhone = saPhone;
    }

    public String getAppointmentTelNo() {
        return appointmentTelNo;
    }

    public void setAppointmentTelNo(String appointmentTelNo) {
        this.appointmentTelNo = appointmentTelNo;
    }

    public String getSalesTelNo() {
        return salesTelNo;
    }

    public void setSalesTelNo(String salesTelNo) {
        this.salesTelNo = salesTelNo;
    }

    public String getAllDayRescueTelNo() {
        return allDayRescueTelNo;
    }

    public void setAllDayRescueTelNo(String allDayRescueTelNo) {
        this.allDayRescueTelNo = allDayRescueTelNo;
    }

    public String getSvcinName() {
        return svcinName;
    }

    public void setSvcinName(String svcinName) {
        this.svcinName = svcinName;
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

    public String getTwcTel1() {
        return twcTel1;
    }

    public void setTwcTel1(String twcTel1) {
        this.twcTel1 = twcTel1;
    }

    public String getTwcTel2() {
        return twcTel2;
    }

    public void setTwcTel2(String twcTel2) {
        this.twcTel2 = twcTel2;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
