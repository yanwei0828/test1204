package com.gtmc.carapp.service.workorder.controller;

import com.alibaba.fastjson.JSONObject;
import com.gtmc.carapp.service.workorder.common.CommonConstants;
import com.gtmc.carapp.service.workorder.dto.EsignBusinessParamDto;
import com.gtmc.carapp.service.workorder.dto.OpenSignDto;
import com.gtmc.carapp.service.workorder.dto.SignFileDto;
import com.gtmc.carapp.service.workorder.dto.UploadFileDto;
import com.gtmc.carapp.service.workorder.service.*;
import com.gtmc.carapp.service.workorder.util.FileFtpUtil;
import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.beans.ResultBean;

import com.yonyou.cloud.common.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = CommonConstants.BASE_PATH + "/exo")
@Api(tags = "第三方调用")
public class ExoController {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(ExoController.class);

    @Autowired
    private EsignFileService esignFileService;

    @Autowired
    private EsignBusinessService esignBusinessService;
    
    @Autowired
    private TtEsignAccountService ttEsignAccountService;

    @Autowired
    private TtEsignConstructionOrderService ttEsignConstructionOrderService;
    
    @YcApi
    @ApiOperation(value = "单方签署文件")
    @RequestMapping(value = "signFileUnilateral", method = RequestMethod.POST)
    public RestResultResponse<?>  signFileUnilateral(@RequestBody SignFileDto signFileDto) {
        logger.info("单方签署文件入参>>>>>>>>" + JSONObject.toJSONString(signFileDto));
        if(StringUtils.isBlank(signFileDto.getPhone())){
            throw new BizException(ResultBean.VALID_FAILD,"手机号不能为空");
        }else if(StringUtils.isBlank(signFileDto.getBusinessId())){
            throw new BizException(ResultBean.VALID_FAILD,"业务id不能为空");
        }else if(StringUtils.isBlank(signFileDto.getTemplateFile())){
            throw new BizException(ResultBean.VALID_FAILD,"签署文件不能为空");
        }else if(StringUtils.isBlank(signFileDto.getSignatureFile())){
            throw new BizException(ResultBean.VALID_FAILD,"签名图片不能为空");
        }
        Map<String,Object> result = esignFileService.signFileUnilateral(signFileDto);
        return new RestResultResponse<String>().success(true).data(result);
    }

    
    @YcApi
    @ApiOperation(value = "记录用户签署意愿(小程序)")
    @RequestMapping(value = "/wechat/saveWillingness", method = RequestMethod.POST)
    public RestResultResponse<?> saveWillingness(@RequestBody EsignBusinessParamDto dto)  {
        logger.info("记录用户签署意愿(小程序)请求参数信息:==============》" + JSONObject.toJSONString(dto));
        //参数非空校验
        if (StringUtils.isBlank(dto.getBusinessId()) || StringUtils.isBlank(dto.getPhone()) || dto.getBusinessType() == null) {
            throw new BizException(ResultBean.VALID_FAILD,"参数businessId,businessType不能为空");
        }
        try {
            esignBusinessService.saveWillingness(dto.getBusinessId(),dto.getBusinessType());
            return new RestResultResponse<>().success(true);
        } catch (Exception e) {
            logger.error("记录用户签署意愿(小程序)失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"记录用户签署意愿失败");
        }
    }

    
    @YcApi
    @ApiOperation(value = "获取签字页面详情(小程序)")
    @RequestMapping(value = "/wechat/getSignInfo", method = RequestMethod.POST)
    public RestResultResponse<?>  getSignInfo(@RequestBody EsignBusinessParamDto dto) {
        logger.info("获取签字页面详情(小程序)请求参数信息:==============》" + JSONObject.toJSONString(dto));
        //参数非空校验
        if (StringUtils.isBlank(dto.getBusinessId()) || StringUtils.isBlank(dto.getPhone()) || dto.getBusinessType() == null) {
            throw new BizException(ResultBean.VALID_FAILD,"参数businessId,businessType,phone不能为空");
        }
        try {
            return new RestResultResponse<>().success(true).data(esignBusinessService.getSignInfo(dto.getBusinessId(),dto.getPhone(),dto.getBusinessType(),2));
        } catch (Exception e) {
            logger.error("获取签字页面详情(小程序)失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"获取签字页面详情失败");
        }
    }
    
    
    @YcApi
    @ApiOperation(value = "获取历史签署文件(小程序)")
    @RequestMapping(value = "/wechat/getSignHistory", method = RequestMethod.POST)
    public RestResultResponse<?>  getSignHistory(@RequestBody EsignBusinessParamDto dto) {
        logger.info("获取历史签署文件(小程序)请求参数信息:==============》" + JSONObject.toJSONString(dto));
        //参数非空校验
        if (StringUtils.isBlank(dto.getBusinessId()) || StringUtils.isBlank(dto.getPhone()) || dto.getBusinessType() == null) {
            throw new BizException(ResultBean.VALID_FAILD,"参数businessId,businessType不能为空");
        }
        try {
            return new RestResultResponse<>().success(true).data(esignBusinessService.getSignHistory(dto.getBusinessId(),dto.getBusinessType()));
        } catch (Exception e) {
            logger.error("获取历史签署文件(小程序)失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"获取历史签署文件失败");
        }
    }

    @ApiOperation(value = "图片上传(小程序)")
    @RequestMapping(value = "/wechat/uploadFile", method = RequestMethod.POST)
    @YcApi
    public RestResultResponse<?> uploadFile(@RequestBody UploadFileDto dto) {
        try {
            logger.info("图片上传(小程序)>>>>:" + JSONObject.toJSONString(dto));
            byte[] fileBytes = Base64.decodeBase64(dto.getFile());
            String suffix = "." + FileFtpUtil.FILE_SUFFIX_PNG;
            String fileUrl = esignBusinessService.uploadFile2Server(fileBytes,dto.getBusinessType(), suffix);
            Map<String,Object> result = new HashMap<>();
            result.put("fileUrl",fileUrl);
            return new RestResultResponse<String>().success(true).data(result);
        } catch (Exception e) {
            logger.error("电子签名图片上传(小程序)失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"图片上传(小程序)失败");
        }
    }

    @YcApi
    @ApiOperation(value = "文件签署提交(小程序)")
    @RequestMapping(value = "/wechat/signCommit", method = RequestMethod.POST)
    public RestResultResponse<?>  signCommit(@RequestBody EsignBusinessParamDto dto) {
        logger.info("文件签署提交(小程序)请求参数信息:==============》" + JSONObject.toJSONString(dto));
        //参数非空校验
        if (StringUtils.isBlank(dto.getBusinessId()) || StringUtils.isBlank(dto.getPhone()) || dto.getBusinessType() == null || StringUtils.isBlank(dto.getSignatrueFile())) {
            throw new BizException(ResultBean.VALID_FAILD,"参数businessId,businessType,phone,signatrueFile不能为空");
        }
        try {
        	return new RestResultResponse<>().success(true).data(esignBusinessService.signCommit(dto.getBusinessId(),dto.getSignatrueFile(),dto.getPhone(),
            		dto.getBusinessType(),dto.getxAxis(),dto.getyAxis(),dto.getTemplateFile(),dto.getSignPageNo()));
        } catch (Exception e) {
            logger.error("文件签署提交(小程序)失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"文件签署提交失败");
        }
    }

    
    @YcApi
    @ApiOperation(value = "查询用户是否开通签名服务(小程序)")
    @RequestMapping(value = "/wechat/isOpenSign", method = RequestMethod.POST)
    public RestResultResponse<?> isOpenSign(@RequestBody OpenSignDto openSignDto)  {
        logger.info("查询用户是否开通签名服(小程序) start>>>>" + JSONObject.toJSONString(openSignDto));
        //参数非空校验
        if (StringUtils.isBlank(openSignDto.getUserId()) || StringUtils.isBlank(openSignDto.getPhone())) {
            throw new BizException(ResultBean.VALID_FAILD,"参数userId,phone不能为空");
        }
        try {
            return new RestResultResponse<>().data(ttEsignAccountService.isOpenSign(openSignDto.getPhone(),openSignDto.getUserId())).success(true);
        } catch (Exception e) {
            logger.error("查询用户是否开通签名服(小程序)失败：", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"查询用户是否开通签名服失败");
        }
    }
    @YcApi
    @ApiOperation(value = "保存电子签名服务协议(小程序)")
    @RequestMapping(value = "/wechat/saveAuthorizedServices", method = RequestMethod.POST)
    public RestResultResponse<?>  saveAuthorizedServices(@RequestBody OpenSignDto openSignDto) {
        logger.info("保存电子签名服务协议(小程序) start>>>>" + JSONObject.toJSONString(openSignDto));
        if(StringUtils.isBlank(openSignDto.getPhone())){
            throw new BizException(ResultBean.VALID_FAILD,"参数phone不能为空");
        }
        try {
            ttEsignConstructionOrderService.saveAuthorizedServices(openSignDto.getPhone(), Integer.parseInt(openSignDto.getUserId()));
            return new RestResultResponse<>().success(true);
        } catch (Exception e) {
            logger.error("保存电子签名服务协议(小程序)失败", e);
            throw new BizException(ResultBean.ERROR_UNKNOWN,"保存电子签名服务协议失败");
        }
    }
}
