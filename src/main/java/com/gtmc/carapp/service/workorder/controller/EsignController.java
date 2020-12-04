package com.gtmc.carapp.service.workorder.controller;

import com.alibaba.fastjson.JSONObject;
import com.gtmc.carapp.service.workorder.common.CommonConstants;
import com.gtmc.carapp.service.workorder.dto.*;
import com.gtmc.carapp.service.workorder.service.*;
import com.gtmc.carapp.service.workorder.util.FileFtpUtil;
import com.gtmc.carapp.service.workorder.util.FileUtil;
import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.beans.ResultBean;
import com.yonyou.cloud.common.exception.BizException;
import com.yonyou.cloud.common.filter.UserLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

@RestController
@RequestMapping(value = CommonConstants.BASE_PATH + "/esign")
@Api(tags = "电子签名")
public class EsignController {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(EsignController.class);

    @Autowired
    private TtEsignAccountService ttEsignAccountService;
    @Autowired
    private ConstructionOrderService constructionOrderService;
    @Autowired
    private TtEsignConstructionOrderService ttEsignConstructionOrderService;
    @Autowired
    private EsignBusinessService esignBusinessService;

    @YcApi
    @ApiOperation(value = "查询用户是否开通签名服务")
    @RequestMapping(value = "isOpenSign", method = RequestMethod.GET)
    public RestResultResponse<?> isOpenSign(@RequestParam("userId") String userId, @RequestParam("phone") String phone)  {
        logger.info("查询用户是否开通签名服 start>>>>[userId=" + userId +",phone=" + phone + "]");
        //参数非空校验
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(phone)) {
            throw new BizException(ResultBean.VALID_FAILD,"参数userId,phone不能为空");
        }
        //安全校验
        String localPhone = UserLocal.getLocalUser().getTelPhone();
        if (!StringUtils.equals(phone,localPhone)){
            throw new BizException(ResultBean.VALID_FAILD,"当前登陆者手机号和入参手机号不一致");
        }
        try {
            //根据手机号码查找用户存在购车合同
            return new RestResultResponse<>().success(true).data(ttEsignAccountService.isOpenSign(phone,userId));
        } catch (Exception e) {
            logger.error("查询用户是否开通签名服务失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"查询用户是否开通签名服务失败");
        }
    }

//    @YcApi
//    @ApiOperation(value = "获取电子施工单详情历史数据")
//    @RequestMapping(value = "selectConstructionOrderInfo", method = RequestMethod.GET)
//    public RestResultResponse<?> selectConstructionOrderInfo(@RequestParam("phone") String phone,
//                                                             @RequestParam("orderNumber") String orderNumber)  {
//        logger.info("获取电子施工单详情历史数据 start>>>>[orderNumer=" + orderNumber +",phone=" + phone + "]");
//        //参数非空校验
//        if (StringUtils.isBlank(phone) || StringUtils.isBlank(orderNumber)){
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"参数orderNumer,phone不能为空");
//            throw new BizException("参数orderNumer,phone不能为空");
//        }
//        //安全校验
//        String localPhone = UserLocal.getLocalUser().getTelPhone();
//        if (!StringUtils.equals(phone,localPhone)){
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"当前登陆者手机号和入参手机号不一致");
//            throw new BizException("当前登陆者手机号和入参手机号不一致");
//        }
//        try {
//            return new RestResultResponse<>().success(true).data(ttEsignConstructionOrderService.selectConstructionOrderInfo(orderNumber));
//        } catch (Exception e) {
//            logger.error("获取电子施工单详情历史数据失败：", e);
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"系统错误");
//            throw new BizException("获取电子施工单详情历史数据失败");
//        }
//    }

    @YcApi
    @ApiOperation(value = "同步电子施工单信息")
    @RequestMapping(value = "syncConstructionOrder", method = RequestMethod.POST)
    public RestResultResponse<?> syncConstructionOrder(@RequestBody ConstructionOrderDto dto)  {
        logger.info("同步电子施工单信息 start>>>>: " + JSONObject.toJSONString(dto));
        //参数非空校验
        if (StringUtils.isBlank(dto.getSrvOrderNo())) {
            throw new BizException(ResultBean.VALID_FAILD,"参数srvOrderNo不能为空");
        }
        try {
            constructionOrderService.syncConstructionOrder(dto);
            return new RestResultResponse<>().success(true);
        } catch (Exception e) {
            logger.error("同步电子施工单信息失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"同步电子施工单信息失败");
        }
    }

//    @YcApi
//    @ApiOperation(value = "获取施工单状态")
//    @RequestMapping(value = "getOrderStatus", method = RequestMethod.GET)
//    public RestResultResponse<?> getOrderStatus(@RequestParam("orderNumber") String orderNumber)  {
//        logger.info("获取施工单状态 start>>>>[orderNumber=" + orderNumber + "]");
//        //参数非空校验
//        if (StringUtils.isBlank(orderNumber)) {
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"参数orderNumber不能为空");
//            throw new BizException("参数orderNumber不能为空");
//        }
//        try {
//            //根据工单号获取施工单状态
//            return new RestResultResponse<>().success(true).data(ttEsignConstructionOrderService.getOrderStatus(orderNumber));
//        } catch (Exception e) {
//            logger.error("获取施工单状态失败：", e);
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"系统错误");
//            throw new BizException("获取施工单状态失败");
//        }
//    }

//    @YcApi
//    @ApiOperation(value = "H5获取施工单模板数据")
//    @RequestMapping(value = "getConOrderInfo", method = RequestMethod.GET)
//    public RestResultResponse<?> getConOrderInfo(@RequestParam("conOrderNumber") String conOrderNumber)  {
//        logger.info("H5获取施工单模板数据 start>>>>[conOrderNumber=" + conOrderNumber + "]");
//        //参数非空校验
//        if (StringUtils.isBlank(conOrderNumber)) {
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"参数conOrderNumber不能为空");
//            throw new BizException("参数conOrderNumber不能为空");
//        }
//        try {
//            return new RestResultResponse<>().success(true).data(ttEsignConstructionOrderService.getConOrderInfo(conOrderNumber));
//        } catch (Exception e) {
//            logger.error("H5获取施工单模板数据失败：", e);
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"系统错误");
//            throw new BizException("H5获取施工单模板数据失败");
//        }
//    }

//    @YcApi
//    @ApiOperation(value = "获取电子施工单详情")
//    @RequestMapping(value = "selectConOrderInfo", method = RequestMethod.GET)
//    public RestResultResponse<?> selectConOrderInfo(@RequestParam("phone") String phone,@RequestParam("orderNumber") String orderNumber)  {
//        logger.info("获取电子施工单详情 start>>>>[phone=" + phone + ",orderNumber=" + orderNumber + "]");
//        //参数非空校验
//        if (StringUtils.isBlank(phone) || StringUtils.isBlank(orderNumber)) {
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"参数phone,orderNumer不能为空");
//            throw new BizException("参数phone,orderNumer不能为空");
//        }
//        //安全校验
//        String localPhone = UserLocal.getLocalUser().getTelPhone();
//        if (!StringUtils.equals(phone,localPhone)){
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"当前登陆者手机号和入参手机号不一致");
//            throw new BizException("当前登陆者手机号和入参手机号不一致");
//        }
//        try {
//            return new RestResultResponse<>().success(true).data(ttEsignConstructionOrderService.selectConOrderInfo(orderNumber));
//        } catch (Exception e) {
//            logger.error("获取电子施工单详情失败：", e);
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"系统错误");
//            throw new BizException("获取电子施工单详情失败");
//        }
//    }

//    @YcApi
//    @ApiOperation(value = "电子施工单签名提交")
//    @RequestMapping(value = "submitSignFile", method = RequestMethod.POST)
//    public RestResultResponse<?> submitSignFile(@RequestBody SubmitSignFileDto submitSignFileDto)  {
//        logger.info("电子施工单签名提交 start>>>>>>>" + JSONObject.toJSONString(submitSignFileDto));
//        //参数非空校验
//        if (StringUtils.isBlank(submitSignFileDto.getPhone())) {
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"手机号不能为空");
//            throw new BizException("手机号不能为空");
//        }
//        if (StringUtils.isBlank(submitSignFileDto.getSrvOrderNo())) {
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"工单号不能为空");
//            throw new BizException("工单号不能为空");
//        }
//        //安全校验
//        String localPhone = UserLocal.getLocalUser().getTelPhone();
//        if (!StringUtils.equals(submitSignFileDto.getPhone(),localPhone)){
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"当前登陆者手机号和入参手机号不一致");
//            throw new BizException("当前登陆者手机号和入参手机号不一致");
//        }
//        try {
//            return new RestResultResponse<>().success(true).data(constructionOrderService.submitSignFile(submitSignFileDto));
//        } catch (Exception e) {
//            logger.error("电子施工单签名提交失败", e);
////            return RestResultResponseUtil.responseError(false,ResultBean.ERROR_UNKNOWN,"系统错误");
//            throw new BizException("电子施工单签名提交失败");
//        }
//    }

    @YcApi
    @ApiOperation(value = "保存电子签名服务协议")
    @RequestMapping(value = "saveAuthorizedServices", method = RequestMethod.GET)
    public RestResultResponse<?>  saveAuthorizedServices(@RequestParam("phone") String phone,
                                                         @RequestParam("userId") int userId) {
        logger.info("保存电子签名服务协议 Controller start>>>>[phone=" + phone + ",userId=" + userId + "]");
        if(StringUtils.isBlank(phone) || userId == 0){
            throw new BizException(ResultBean.VALID_FAILD,"参数phone,userId不能为空");
        }
        //安全校验
        String localPhone = UserLocal.getLocalUser().getTelPhone();
        if (!StringUtils.equals(phone,localPhone)){
            throw new BizException(ResultBean.VALID_FAILD,"当前登陆者手机号和入参手机号不一致");
        }
        try {
            ttEsignConstructionOrderService.saveAuthorizedServices(phone, userId);
            return new RestResultResponse<>().success(true);
        } catch (Exception e) {
            logger.error("保存电子签名服务协议失败", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"保存电子签名服务协议失败");
        }
    }

    @YcApi
    @ApiOperation(value = "图片上传")
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public RestResultResponse<?>  uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam(required = false, name = "phone") String phone,
                                             @RequestParam(required = false, name = "businessType") Integer businessType,
                                             @RequestParam(required = false, name = "appVersion") String appVersion) {
        //安全校验
        String localPhone = UserLocal.getLocalUser().getTelPhone();
        if (!StringUtils.equals(phone, localPhone)){
            throw new BizException(ResultBean.VALID_FAILD,"当前登陆者手机号和入参手机号不一致");
        }
        try {
            logger.info("图片上传size:" + file.getSize());
            String suffix = "." + FileFtpUtil.FILE_SUFFIX_PNG;
            String fileUrl = esignBusinessService.uploadFile2Server(file.getBytes(),businessType, suffix);
            Map<String,Object> result = new HashMap<>();
            result.put("fileUrl",fileUrl);
            return new RestResultResponse<String>().success(true).data(result);
        } catch (Exception e) {
            logger.error("电子签名图片上传失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"图片上传失败");
        }
    }

    @YcApi
    @ApiOperation(value = "图片上传base64")
    @RequestMapping(value = "uploadFileBase64", method = RequestMethod.POST)
    public RestResultResponse<?>  uploadFileBase64(@RequestParam("file") String file,
                                             @RequestParam(required = false, name = "phone") String phone,
                                             @RequestParam(required = false, name = "businessType") Integer businessType,
                                             @RequestParam(required = false, name = "appVersion") String appVersion) {
        //安全校验
        String localPhone = UserLocal.getLocalUser().getTelPhone();
        if (!StringUtils.equals(phone, localPhone)){
            throw new BizException(ResultBean.VALID_FAILD,"当前登陆者手机号和入参手机号不一致");
        }
        try {
            byte[] fileBytes = Base64.decodeBase64(file);
            String suffix = "." + FileFtpUtil.FILE_SUFFIX_PNG;
            String fileUrl = esignBusinessService.uploadFile2Server(fileBytes,businessType, suffix);
            Map<String,Object> result = new HashMap<>();
            result.put("fileUrl",fileUrl);
            return new RestResultResponse<String>().success(true).data(result);
        } catch (Exception e) {
            logger.error("电子签名图片上传base64失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"图片上传base64失败");
        }
    }

    @YcApi
    @ApiOperation(value = "记录用户签署意愿")
    @RequestMapping(value = "saveWillingness", method = RequestMethod.POST)
    public RestResultResponse<?> saveWillingness(@RequestBody EsignBusinessParamDto dto)  {
        logger.info("记录用户签署意愿请求参数信息:==============》" + JSONObject.toJSONString(dto));
        //参数非空校验
        if (StringUtils.isBlank(dto.getBusinessId()) || dto.getBusinessType() == null) {
            throw new BizException(ResultBean.VALID_FAILD,"参数businessId,businessType不能为空");
        }
        //安全校验
        String localPhone = UserLocal.getLocalUser().getTelPhone();
        if (!StringUtils.equals(dto.getPhone(), localPhone)){
            throw new BizException(ResultBean.VALID_FAILD,"当前登陆者手机号和入参手机号不一致");
        }
        try {
            esignBusinessService.saveWillingness(dto.getBusinessId(),dto.getBusinessType());
            return new RestResultResponse<>().success(true);
        } catch (Exception e) {
            logger.error("记录用户签署意愿失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"记录用户签署意愿失败");
        }
    }

    @YcApi
    @ApiOperation(value = "获取签字页面详情")
    @RequestMapping(value = "getSignInfo", method = RequestMethod.POST)
    public RestResultResponse<?>  getSignInfo(@RequestBody EsignBusinessParamDto dto) {
        logger.info("获取签字页面详情请求参数信息:==============》" + JSONObject.toJSONString(dto));
        //参数非空校验
        if (StringUtils.isBlank(dto.getBusinessId()) || dto.getBusinessType() == null) {
            throw new BizException(ResultBean.VALID_FAILD,"参数businessId,businessType不能为空");
        }
        //安全校验
        String localPhone = UserLocal.getLocalUser().getTelPhone();
        if (!StringUtils.equals(dto.getPhone(),localPhone)){
            throw new BizException(ResultBean.VALID_FAILD,"当前登陆者手机号和入参手机号不一致");
        }
        try {
            return new RestResultResponse<>().success(true).data(esignBusinessService.getSignInfo(dto.getBusinessId(),dto.getPhone(),dto.getBusinessType(),1));
        } catch (Exception e) {
            logger.error("获取签字页面详情失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"获取签字页面详情失败");
        }
    }

    @YcApi
    @ApiOperation(value = "获取历史签署文件")
    @RequestMapping(value = "getSignHistory", method = RequestMethod.POST)
    public RestResultResponse<?>  getSignHistory(@RequestBody EsignBusinessParamDto dto) {
        logger.info("获取历史签署文件请求参数信息:==============》" + JSONObject.toJSONString(dto));
        //参数非空校验
        if (StringUtils.isBlank(dto.getBusinessId()) || dto.getBusinessType() == null) {
            throw new BizException(ResultBean.VALID_FAILD,"参数businessId,businessType不能为空");
        }
        //安全校验
        String localPhone = UserLocal.getLocalUser().getTelPhone();
        if (!StringUtils.equals(dto.getPhone(), localPhone)){
            throw new BizException(ResultBean.VALID_FAILD,"当前登陆者手机号和入参手机号不一致");
        }
        try {
            return new RestResultResponse<>().success(true).data(esignBusinessService.getSignHistory(dto.getBusinessId(),dto.getBusinessType()));
        } catch (Exception e) {
            logger.error("获取历史签署文件失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"获取历史签署文件失败");
        }
    }

    @YcApi
    @ApiOperation(value = "文件签署提交")
    @RequestMapping(value = "signCommit", method = RequestMethod.POST)
    public RestResultResponse<?>  signCommit(@RequestBody EsignBusinessParamDto dto) {
        logger.info("文件签署提交请求参数信息:==============》" + JSONObject.toJSONString(dto));
        //参数非空校验
        if (StringUtils.isBlank(dto.getBusinessId()) || StringUtils.isBlank(dto.getSignatrueFile())) {
            throw new BizException(ResultBean.VALID_FAILD,"参数businessId,signatrueFile不能为空");
        }
        //安全校验
        String localPhone = UserLocal.getLocalUser().getTelPhone();
        if (!StringUtils.equals(dto.getPhone(), localPhone)){
            throw new BizException(ResultBean.VALID_FAILD,"当前登陆者手机号和入参手机号不一致");
        }
        try {
            return new RestResultResponse<>().success(true).data(esignBusinessService.signCommit(dto.getBusinessId(),dto.getSignatrueFile(),dto.getPhone(),
            		dto.getBusinessType(),dto.getxAxis(),dto.getyAxis(),dto.getTemplateFile(),dto.getSignPageNo()));
        } catch (Exception e) {
            logger.error("文件签署提交失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"文件签署提交失败");
        }
    }


    @YcApi
    @ApiOperation(value = "数据流传输")
    @RequestMapping(value = "dataStreamTransmission", method = RequestMethod.GET)
    public ResponseEntity<FileSystemResource> dataStreamTransmission(@RequestParam("appVersion") String appVersion,
                                                                     @RequestParam("businessId") String businessId,
                                                                     @RequestParam("businessType") int businessType,
                                                                     @RequestParam(value = "businessParam",required = false) Integer businessParam,
                                                                     @RequestParam("phone") String phone,
                                                                     @RequestParam("userId") int userId,
                                                                     @RequestParam(value = "interfaceType",required = false) Integer interfaceType) {
        logger.info("dataStreamTransmission controller start");
        URL url = null;
        byte[] getData = null;
        try {

            String pdfUrl = esignBusinessService.dataStreamTransmission(businessId,businessType,interfaceType,businessParam,phone);
            logger.info("dataStreamTransmission getUrl ："+pdfUrl);

            if (null != pdfUrl && ("-2").equals(pdfUrl)){
                logger.info("数据流传输 业务ID有误");
                throw new BizException(ResultBean.ERROR_UNKNOWN,"数据流传输失败");
            }


            url = new URL(pdfUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");

            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            getData = FileUtil.readInputStream(inputStream);

            File file =  new File(currentTimeMillis()+".pdf");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);

            if (file == null) {
                return null;
            }
            fos.close();
            logger.info("dataStreamTransmission 设置请求头 start");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", "attachment; filename=" + file.getName());
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Last-Modified", new Date().toString());
            headers.add("ETag", String.valueOf(currentTimeMillis()));
            headers.add("contentLength", Long.toString(file.length()));
            logger.info("dataStreamTransmission 设置请求头 end");
            return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
        }catch (Exception e) {
            logger.info("dataStreamTransmission error"+e.getMessage());
            logger.info("dataStreamTransmission error"+e);
            e.printStackTrace();
        }
        return null;
    }

}
