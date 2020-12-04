package com.gtmc.carapp.service.workorder.dto;

public class ConstructionOrderDto {

    private String srvOrderNo;//施工单号
    private String srvTypeName;//工单类型
    private String vin;//车架号
    private String saName;//服务顾问
    private String saPhone;//服务电话
    private String twcUserName;//送修人
    private String twcTel1;//送修人电话1
    private String twcTel2;//送修人电话2
    private String svcinName;//入库店名称
    private String orderPrice;//工单合计金额
    private String totalPrice;//估算金额
    private String twcResult;//上次维修建议
    private String projectDiscountAmount;//项目折扣金额
    private String partDiscountAmount;//零件折扣金额
    private String regidtNo;//车牌号
    private String warrantyCost;//保修费用
    private String warrantyPart;//保修零件

    //V4.4 电子施工单字段
    private String customerType;//客户类型
    private String customerName;//客户姓名
    private String customerPhone;//客户电话
    private String customerAddress;//客户地址
    private String carModelName;//车型名称
    private String buyCarDate;//购车日
    private String insurecomcode;//保险公司
    private String effectiveDateStart;//有效期开始
    private String effectiveDateEnd;//有效期结束
    private String latestSrvDate;//上次维修日期
    private String latestMileage;//上次维修里程
    private String latestSrvTypeName;//上次维修类型
    private String prercvAmount;//预付金额
    private String otherDiscount;//其他折扣
    private String detailAddress;//地址
    private String appointmentTelNo;//预约电话
    private String salesTelNo;//销售电话
    private String allDayRescueTelNo;//24小时救援电话
    private String projectTotalAmount;//维修费合计
    private String projectCost;//应付维修费
    private String partsTotalAmount;//零件费合计
    private String partsCost;//应付零件费
    private String settlementOnlineFlag;//线上/线下反结算标识
    private String reservationOrder;//工单对应的线上预约单号
    private String constructionStatus;//电子施工单状态

    private String projectsList;//维修项目明细
    private String partsList;//维修零件明细

    public String getSrvOrderNo() {
        return srvOrderNo;
    }

    public void setSrvOrderNo(String srvOrderNo) {
        this.srvOrderNo = srvOrderNo;
    }

    public String getSrvTypeName() {
        return srvTypeName;
    }

    public void setSrvTypeName(String srvTypeName) {
        this.srvTypeName = srvTypeName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSaName() {
        return saName;
    }

    public void setSaName(String saName) {
        this.saName = saName;
    }

    public String getSaPhone() {
        return saPhone;
    }

    public void setSaPhone(String saPhone) {
        this.saPhone = saPhone;
    }

    public String getTwcUserName() {
        return twcUserName;
    }

    public void setTwcUserName(String twcUserName) {
        this.twcUserName = twcUserName;
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

    public String getSvcinName() {
        return svcinName;
    }

    public void setSvcinName(String svcinName) {
        this.svcinName = svcinName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTwcResult() {
        return twcResult;
    }

    public void setTwcResult(String twcResult) {
        this.twcResult = twcResult;
    }

    public String getProjectDiscountAmount() {
        return projectDiscountAmount;
    }

    public void setProjectDiscountAmount(String projectDiscountAmount) {
        this.projectDiscountAmount = projectDiscountAmount;
    }

    public String getPartDiscountAmount() {
        return partDiscountAmount;
    }

    public void setPartDiscountAmount(String partDiscountAmount) {
        this.partDiscountAmount = partDiscountAmount;
    }

    public String getRegidtNo() {
        return regidtNo;
    }

    public void setRegidtNo(String regidtNo) {
        this.regidtNo = regidtNo;
    }

    public String getWarrantyCost() {
        return warrantyCost;
    }

    public void setWarrantyCost(String warrantyCost) {
        this.warrantyCost = warrantyCost;
    }

    public String getWarrantyPart() {
        return warrantyPart;
    }

    public void setWarrantyPart(String warrantyPart) {
        this.warrantyPart = warrantyPart;
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

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public String getBuyCarDate() {
        return buyCarDate;
    }

    public void setBuyCarDate(String buyCarDate) {
        this.buyCarDate = buyCarDate;
    }

    public String getInsurecomcode() {
        return insurecomcode;
    }

    public void setInsurecomcode(String insurecomcode) {
        this.insurecomcode = insurecomcode;
    }

    public String getEffectiveDateStart() {
        return effectiveDateStart;
    }

    public void setEffectiveDateStart(String effectiveDateStart) {
        this.effectiveDateStart = effectiveDateStart;
    }

    public String getEffectiveDateEnd() {
        return effectiveDateEnd;
    }

    public void setEffectiveDateEnd(String effectiveDateEnd) {
        this.effectiveDateEnd = effectiveDateEnd;
    }

    public String getLatestSrvDate() {
        return latestSrvDate;
    }

    public void setLatestSrvDate(String latestSrvDate) {
        this.latestSrvDate = latestSrvDate;
    }

    public String getLatestMileage() {
        return latestMileage;
    }

    public void setLatestMileage(String latestMileage) {
        this.latestMileage = latestMileage;
    }

    public String getLatestSrvTypeName() {
        return latestSrvTypeName;
    }

    public void setLatestSrvTypeName(String latestSrvTypeName) {
        this.latestSrvTypeName = latestSrvTypeName;
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

    public String getProjectTotalAmount() {
        return projectTotalAmount;
    }

    public void setProjectTotalAmount(String projectTotalAmount) {
        this.projectTotalAmount = projectTotalAmount;
    }

    public String getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(String projectCost) {
        this.projectCost = projectCost;
    }

    public String getPartsTotalAmount() {
        return partsTotalAmount;
    }

    public void setPartsTotalAmount(String partsTotalAmount) {
        this.partsTotalAmount = partsTotalAmount;
    }

    public String getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(String partsCost) {
        this.partsCost = partsCost;
    }

    public String getSettlementOnlineFlag() {
        return settlementOnlineFlag;
    }

    public void setSettlementOnlineFlag(String settlementOnlineFlag) {
        this.settlementOnlineFlag = settlementOnlineFlag;
    }

    public String getReservationOrder() {
        return reservationOrder;
    }

    public void setReservationOrder(String reservationOrder) {
        this.reservationOrder = reservationOrder;
    }

    public String getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(String constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public String getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(String projectsList) {
        this.projectsList = projectsList;
    }

    public String getPartsList() {
        return partsList;
    }

    public void setPartsList(String partsList) {
        this.partsList = partsList;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
