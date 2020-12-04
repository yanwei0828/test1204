package com.gtmc.carapp.service.workorder.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gtmc.carapp.service.workorder.common.BusinessTypeEnum;
import com.gtmc.carapp.service.workorder.dto.AuthCertificaInfoStatusDto;
import com.gtmc.carapp.service.workorder.dto.EsignBusinessParamDto;
import com.gtmc.carapp.service.workorder.dto.EsignBusinessRequestDto;
import com.gtmc.carapp.service.workorder.dto.SignFileDto;
import com.gtmc.carapp.service.workorder.entity.SignInfoVo;
import com.gtmc.carapp.service.workorder.entity.SignRecordVo;
import com.gtmc.carapp.service.workorder.util.*;
import com.gtmc.carapp.service.workorder.vo.SignVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
@Transactional
public class EsignBusinessService {

    private Logger logger = Logger.getLogger(this.getClass());

    private static String pattern = "yyyyMMddHHmmssSSS";

    @Autowired
    private Environment env;
    @Autowired
    private TtSystemCallLogService logService;

    @Autowired
    private EsignFileService esignFileService;
    
    /**
     * 电子签名业务处理
     */
    public SignVo getSignInfo(String businessId,String phone,Integer businessType,Integer sourceType){
        EsignBusinessRequestDto dto = new EsignBusinessRequestDto();
        dto.setBusinessId(businessId);
        dto.setBusinessType(businessType);
        dto.setPhone(phone);
        dto.setSourceType(sourceType);
        String reponse = "";
        //电子施工单
        if(businessType == BusinessTypeEnum.CONSTRUCTION_ORDER.getCode()){
        }
        //购车合同
        else if(businessType == BusinessTypeEnum.BUY_CAR_CONTRACT.getCode()){
            reponse = this.businessPost(BusinessTypeEnum.BUY_CAR_CONTRACT.getCode(),dto,"url.intention.getSignInfo");
        }
        //新能源补充协议
        else if(businessType == BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode()){
            reponse = this.businessPost(BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode(),dto,"url.intention.getSignInfo");
        }

        JSONObject jsonObject = JSONObject.parseObject(reponse);
        String data = jsonObject.getString("data");
        SignVo signVo = JSONObject.parseObject(data, SignVo.class);
        return signVo;
    }

    /**
     * 历史签署文件
     * @param businessId
     * @param businessType
     * @return
     */
    public List<SignRecordVo> getSignHistory(String businessId,Integer businessType) {
        EsignBusinessRequestDto dto = new EsignBusinessRequestDto();
        dto.setBusinessId(businessId);
        dto.setBusinessType(businessType);
        String reponse = "";
        //电子施工单
        if(businessType == BusinessTypeEnum.CONSTRUCTION_ORDER.getCode()){
        }
        //购车合同
        else if(businessType == BusinessTypeEnum.BUY_CAR_CONTRACT.getCode()){
            reponse = this.businessPost(BusinessTypeEnum.BUY_CAR_CONTRACT.getCode(),dto,"url.intention.getSignHistory");
        }
        //新能源补充协议
        else if(businessType == BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode()){
            reponse = this.businessPost(BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode(),dto,"url.intention.getSignHistory");
        }

        JSONObject jsonObject = JSONObject.parseObject(reponse);
        String data = jsonObject.getString("data");
        List<SignRecordVo> signRecordVo = JSONObject.parseArray(data, SignRecordVo.class);
        return signRecordVo;
    }

    /**
     * 用户同意意愿
     * @param businessId
     * @param businessType
     */
    public void saveWillingness(String businessId, Integer businessType) {
        EsignBusinessRequestDto dto = new EsignBusinessRequestDto();
        dto.setBusinessId(businessId);
        dto.setBusinessType(businessType);
        //电子施工单
        if(businessType == BusinessTypeEnum.CONSTRUCTION_ORDER.getCode()){
        }
        //购车合同
        else if(businessType == BusinessTypeEnum.BUY_CAR_CONTRACT.getCode()){
            this.businessPost(BusinessTypeEnum.BUY_CAR_CONTRACT.getCode(),dto,"url.intention.saveWillingness");
        }
        //新能源补充协议
        else if(businessType == BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode()){
            this.businessPost(BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode(),dto,"url.intention.saveWillingness");
        }
    }

    /**
     * 根据业务上传图片到各自分类的图片文件夹
     * @param bytes
     * @param businessType
     * @return
     */
    public String uploadFile2Server(byte[] bytes,Integer businessType,String suffix) throws Exception {
        String filePathName;
        //电子施工单
        if(businessType == BusinessTypeEnum.CONSTRUCTION_ORDER.getCode()){
            filePathName = BusinessTypeEnum.CONSTRUCTION_ORDER.getFilePath();
        }
        //购车合同
        else if(businessType == BusinessTypeEnum.BUY_CAR_CONTRACT.getCode()){
            filePathName = BusinessTypeEnum.BUY_CAR_CONTRACT.getFilePath();
        }
        //新能源补充协议
        else if(businessType == BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode()){
            filePathName = BusinessTypeEnum.BUY_CAR_PROTOCOL.getFilePath();
        }else{
            filePathName = "/common";
        }
        if(StringUtils.isNotBlank(filePathName)){
            InputStream in = new ByteArrayInputStream(bytes);
//            BufferedImage bufferedImage = ImageIO.read(in);
//            // 调用图片旋转工具类，旋转图片
//            BufferedImage rotateImage = RotateImageUtil.rotateImage(bufferedImage, 270);//顺时针转270度：270 或者逆时针旋转90度：-90
//            InputStream inputStream = FileUtil.bufferedImageToInputStream(rotateImage);
            FileFtpUtil ftp = new FileFtpUtil();
            String name = MyDateUtil.formatDate(new Date(), pattern) + suffix;
            String pathname = env.getProperty("spring.ftp.file.path.esign") + filePathName;
            boolean uploadFlag = ftp.uploadFile(pathname, name, in);
            //上传成功返回文件路径
            if(uploadFlag){
//                String filePath = env.getProperty("spring.ftp.file.prefix") + pathname + "/" + name;
                String filePath = pathname + "/" + name;
                return filePath;
            }
        }
        return "";
    }



    /**
     * 文件签署提交
     * @param businessId
     * @param signatrueFile
     * @param phone
     * @param businessType
     * @return
     */
    public Integer signCommit(String businessId,String signatrueFile,String phone,Integer businessType,
    		float posX,float posY,String templateFile,Integer signPageNo) {
        EsignBusinessRequestDto dto = new EsignBusinessRequestDto();
        dto.setBusinessId(businessId);
        dto.setBusinessType(businessType);
        dto.setPhone(phone);
        signatrueFile = env.getProperty("spring.ftp.file.prefix") + signatrueFile;
        logger.info("signatrueFile>>>>" + signatrueFile);
        dto.setSignatrueFile(signatrueFile);
        String esignBusinessId = "";
        //电子施工单
        if(businessType == BusinessTypeEnum.CONSTRUCTION_ORDER.getCode()){
        	esignBusinessId = BusinessTypeEnum.CONSTRUCTION_ORDER.getCode() + "_"+businessId;
        }
        //购车合同
        else if(businessType == BusinessTypeEnum.BUY_CAR_CONTRACT.getCode()){
        	esignBusinessId = BusinessTypeEnum.BUY_CAR_CONTRACT.getCode() + "_"+businessId;
        	if(signPageNo == null || signPageNo < 1){
                signPageNo = 1;
            }
        }
        //新能源补充协议
        else if(businessType == BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode()){
        	esignBusinessId = BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode() + "_"+businessId;
            if(signPageNo == null || signPageNo < 1){
                signPageNo = 2;
            }
        }
        
        SignFileDto signFileDto = new SignFileDto();
        signFileDto.setBusinessId(esignBusinessId);
        signFileDto.setPhone(phone);
        signFileDto.setSignatureFile(signatrueFile);
        signFileDto.setTemplateFile(templateFile);
        signFileDto.setPosX(posX);
        signFileDto.setPosY(posY);
        signFileDto.setSignPageNo(signPageNo);
        Map<String, Object> map = esignFileService.submitSignFile2Esign(signFileDto);
        logger.info("调用单方签署文件接口生成文件返回结果》》》》"+JSON.toJSONString(map));
        String signedFileUrl = String.valueOf(map.get("signedFileUrl"));
        if(StringUtils.isEmpty(signedFileUrl) || signedFileUrl.equals("null") ){
        	logger.info("调用单方签署文件接口生成文件失败----》》》》》》》》》》》》》");
        	return 2;
        }
        dto.setSignedFile(signedFileUrl);
        String reponse = "";
        //电子施工单
        if(businessType == BusinessTypeEnum.CONSTRUCTION_ORDER.getCode()){
        
        }
        //购车合同
        else if(businessType == BusinessTypeEnum.BUY_CAR_CONTRACT.getCode()){
            reponse = this.businessPost(BusinessTypeEnum.BUY_CAR_CONTRACT.getCode(),dto,"url.intention.signCommit");
        }
        //新能源补充协议
        else if(businessType == BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode()){
            reponse = this.businessPost(BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode(),dto,"url.intention.signCommit");
        }

        JSONObject jsonObject = JSONObject.parseObject(reponse);
        String data = jsonObject.getString("data");
        Integer result = JSONObject.parseObject(data, Integer.class);
        return result;
    }


    private String businessPost(Integer businessType, EsignBusinessRequestDto dto, String urlProperty){
        String url = "";
        String key = "";
        //电子施工单
        if(businessType == BusinessTypeEnum.CONSTRUCTION_ORDER.getCode()){
//            dto.setAppId(env.getProperty("intention.appId"));
//            url = env.getProperty("url.authentication.certificaInfoStatus");
//            key = env.getProperty("authentication.key");
        }
        //购车合同
        else if(businessType == BusinessTypeEnum.BUY_CAR_CONTRACT.getCode()){
            dto.setAppId(env.getProperty("intention.appId"));
            url = env.getProperty(urlProperty);
            key = env.getProperty("intention.key");
        }
        //新能源补充协议
        else if(businessType == BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode()){
            dto.setAppId(env.getProperty("intention.appId"));
            url = env.getProperty(urlProperty);
            key = env.getProperty("intention.key");
        }
        if(StringUtils.isNotBlank(url)){
            logger.info("电子签名业务请求url>>>>>:" + url);
            Date now = new Date();
            Random random = new Random();
            int ranNum = random.nextInt(9000) + 1000;
            String nonce = String.valueOf(ranNum);
            String timestamp = String.valueOf(now.getTime());
            dto.setTimestamp(timestamp);
            dto.setNonce(nonce);
            String sign = key + nonce + timestamp;
            logger.info("加密前签名：" + sign);
            sign = CiphertextUtil.passAlgorithmsCiphering(sign, CiphertextUtil.SHA_1);
            logger.info("加密后签名：" + sign);
            dto.setSignature(sign);
            String response = HttpUtil.post(url,JSON.toJSONString(dto));
            logger.info("电子签名业务返回结果>>>>>:" + response);
            return response;
        }
        return "";
    }

    /**
     * 数据流传输
     * @param businessId 业务id（合同号/协议主键ID/施工单号）
     * @param businessType 业务类型
     *              1：合同详情页
     *              2：协议详情页
     *              3：合同历史签署文件页
     * @param businessParam 1：合同历史签署文件页——页码
     * @param phone 登陆者手机号
     * @return 需转流的URL
     */
    public String dataStreamTransmission(String businessId,Integer businessType,Integer interfaceType,Integer businessParam,String phone) {
        logger.info("1");
        EsignBusinessRequestDto dto = new EsignBusinessRequestDto();
        dto.setBusinessId(businessId);
        dto.setBusinessType(businessType);
        dto.setBusinessParam(businessParam);
        dto.setInterfaceType(interfaceType);
        dto.setPhone(phone);
        String reponse = "";
        //电子施工单
        if(businessType == BusinessTypeEnum.CONSTRUCTION_ORDER.getCode()){
            logger.info("2");

        }
        //购车合同 || 新能源补充协议
        else if(businessType == BusinessTypeEnum.BUY_CAR_CONTRACT.getCode() || businessType == BusinessTypeEnum.BUY_CAR_PROTOCOL.getCode()
                || (businessType == BusinessTypeEnum.BUY_CAR_CONTRACT.getCode() && interfaceType == 1)){
            logger.info("3");
            reponse = this.businessPost(BusinessTypeEnum.BUY_CAR_CONTRACT.getCode(),dto,"url.intention.getStreamUrl");
        }

        logger.info("reponse = "+reponse);
        JSONObject jsonObject = JSONObject.parseObject(reponse);
        String data = jsonObject.getString("data");
        return data;
    }

}
