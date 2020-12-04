package com.gtmc.carapp.service.workorder.service;

import com.gtmc.carapp.service.workorder.common.ConstructionOrderStatusEnum;
import com.gtmc.carapp.service.workorder.dto.*;
import com.gtmc.carapp.service.workorder.entity.TtEsignConstructionOrder;
import com.gtmc.carapp.service.workorder.entity.TtEsignConstructionOrderDetail;
import com.gtmc.carapp.service.workorder.mapper.TtEsignConstructionOrderDetailMapper;
import com.gtmc.carapp.service.workorder.mapper.TtEsignConstructionOrderMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ConstructionOrderService {

    private Logger logger = Logger.getLogger(this.getClass());

    private static Integer FLAG_NORMAL = 0;
    private static Integer FLAG_DELETE = 1;

    private static String pattern = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private Environment env;
    @Resource
    private TtEsignConstructionOrderMapper constructionOrderMapper;
    @Resource
    private TtEsignConstructionOrderDetailMapper constructionOrderDetailMapper;
    @Autowired
    private EsignFileService esignFileService;

    public void syncConstructionOrder(ConstructionOrderDto dto){

        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("srvOrderNo",dto.getSrvOrderNo());
        queryParams.put("deleteFlag",FLAG_NORMAL);
        //查询电子施工单是否存在
        TtEsignConstructionOrder order = constructionOrderMapper.queryConstructionOrder(queryParams);
        if(order != null){
            //已存在的电子施工单，如果状态为0-待生成或者1-待签字，则更新
            if(order.getStatus() == 0 || order.getStatus() == 1){

            }
            //否则新增
            else{
                Long constructionOrderId = insertMainData(dto);
                logger.info("2新增电子施工单id" + constructionOrderId);
                insertDetailData(dto,constructionOrderId);
            }
        }
        //电子施工单不存在则新增
        else{
            Long constructionOrderId = insertMainData(dto);
            logger.info("3新增电子施工单id" + constructionOrderId);
            insertDetailData(dto,constructionOrderId);
        }

    }

    private Long insertMainData(ConstructionOrderDto dto){
        TtEsignConstructionOrder data = new TtEsignConstructionOrder();
        if(dto == null){
            return null;
        }
        data.setSrvOrderNo(dto.getSrvOrderNo());
        data.setDeleteFlag(FLAG_NORMAL);
        data.setCreateDate(new Date());
        data.setStatus(ConstructionOrderStatusEnum.NOT_GENERATE.getCode());
        constructionOrderMapper.addConstructionOrder(data);
        logger.info("1新增电子施工单id:" + data.getId());
        return data.getId();
    }

    private void insertDetailData(ConstructionOrderDto dto,Long constructionOrderId){
        TtEsignConstructionOrderDetail data = new TtEsignConstructionOrderDetail();
        if(constructionOrderId == null){
            logger.info("缺乏电子施工单关联id");
            return;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            data.setConstructionOrderId(constructionOrderId);
            data.setSrvOrderNo(dto.getSrvOrderNo());
            data.setSrvTypeName(dto.getSrvTypeName());
            data.setSaName(dto.getSaName());
            data.setSaPhone(dto.getSaPhone());
            data.setCustomerName(dto.getCustomerName());
            data.setCustomerPhone(dto.getCustomerPhone());
            data.setCustomerType(dto.getCustomerType());
            data.setCustomerAddress(dto.getCustomerAddress());
            data.setRegistNo(dto.getRegidtNo());
            data.setVin(dto.getVin());
            data.setCarModelName(dto.getCarModelName());
            data.setTwcUserName(dto.getTwcUserName());
            data.setTwcTel1(dto.getTwcTel1());
            data.setTwcTel2(dto.getTwcTel2());
            data.setBuyCarDate(dto.getBuyCarDate()!=null?format.parse(dto.getBuyCarDate()):null);
            data.setInsurecomcode(dto.getInsurecomcode());
            data.setEffectiveDateStart(dto.getEffectiveDateStart()!=null?format.parse(dto.getEffectiveDateStart()):null);
            data.setEffectiveDateEnd(dto.getEffectiveDateEnd()!=null?format.parse(dto.getEffectiveDateEnd()):null);
            data.setLatestSrvDate(dto.getLatestSrvDate()!=null?format.parse(dto.getLatestSrvDate()):null);
            data.setLatestSrvTypeName(dto.getLatestSrvTypeName());
            data.setLatestMileage(dto.getLatestMileage());
            data.setTwcResult(dto.getTwcResult());
            data.setProjectTotalAmount(dto.getProjectTotalAmount());
            data.setProjectDiscountAmount(dto.getProjectDiscountAmount());
            data.setWarrantyCost(dto.getWarrantyCost());
            data.setProjectCost(dto.getProjectCost());
            data.setProjectsList(dto.getProjectsList());
            data.setPartsTotalAmount(dto.getPartsTotalAmount());
            data.setPartDiscountAmount(dto.getPartDiscountAmount());
            data.setWarrantyPart(dto.getWarrantyPart());
            data.setPartsCost(dto.getPartsCost());
            data.setPartsList(dto.getPartsList());
            data.setOrderPrice(dto.getOrderPrice());
            data.setPrercvAmount(dto.getPrercvAmount());
            data.setOtherDiscount(dto.getOtherDiscount());
            data.setTotalPrice(dto.getTotalPrice());
            data.setDetailAddress(dto.getDetailAddress());
            data.setAppointmentTelNo(dto.getAppointmentTelNo());
            data.setSalesTelNo(dto.getSalesTelNo());
            data.setAllDayRescueTelNo(dto.getAllDayRescueTelNo());
            data.setSvcinName(dto.getSvcinName());
            data.setCreateDate(new Date());
            constructionOrderDetailMapper.insert(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件签署提交
     * @param submitSignFileDto
     */
    public String submitSignFile(SubmitSignFileDto submitSignFileDto){

//        String unsignFileUrl = createPdfFromHtml(submitSignFileDto.getTemplateFile());
        SignFileDto signFileDto = new SignFileDto();

        esignFileService.submitSignFile2Esign(signFileDto);
//        Date now = new Date();
//        TtEsignConstructionOrder updateParams = new TtEsignConstructionOrder();
//        updateParams.setId(submitSignFileDto.getConstructionOrderId());
//        updateParams.setUnsignFileUrl(unsignFileUrl);
//        updateParams.setSignedFileUrl(signedFileUrl);
//        updateParams.setSignDate(now);
//        updateParams.setStatus(ConstructionOrderStatusEnum.SIGNED_ONLINE.getCode());
//        updateParams.setUpdateDate(now);
//        constructionOrderMapper.updateConstructionOrder(updateParams);

        return null;

    }

    private static String createPdfFromHtml(String fileName){

        return "https://carapptest.gtmc.com.cn/fs01/appfiles/maintainJob/test.pdf";
    }

    public static void main(String[] args) {
//        SubmitSignFileDto submitSignFileDto = new SubmitSignFileDto();
//        submitSignFileDto.setConstructionOrderId(Long.valueOf(123411));
//        ConstructionOrderService.submitSignFile2Esign(submitSignFileDto);
    }

}
