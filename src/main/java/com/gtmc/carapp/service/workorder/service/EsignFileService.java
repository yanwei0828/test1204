package com.gtmc.carapp.service.workorder.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gtmc.carapp.service.workorder.dto.*;
import com.gtmc.carapp.service.workorder.entity.TtSystemCallLog;
import com.gtmc.carapp.service.workorder.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

@Service
@Transactional
public class EsignFileService {

    private Logger logger = Logger.getLogger(this.getClass());

    private static String pattern = "yyyyMMddHHmmssSSS";

    @Autowired
    private Environment env;
    @Autowired
    private TtSystemCallLogService logService;

    /**
     * 调用电子签名 单方签署文件接口，返回签署后的文件路径
     * @param signFileDto
     */
    public Map<String,Object> signFileUnilateral(SignFileDto signFileDto){
        return submitSignFile2Esign(signFileDto);
    }

    public Map<String,Object> submitSignFile2Esign(SignFileDto signFileDto){
        Map<String,Object> resultMap = new HashMap<>();
        EsignRequestDto dto = new EsignRequestDto();
        dto.setAcctType("PERSON");
        dto.setSignatoryType("UNILATERAL");

        //获取鉴权的个人信息
        Map<String,Object> personMap = getAuthenticationInfo(signFileDto.getPhone());
        if(personMap != null && 200 == Integer.parseInt(String.valueOf(personMap.get("resultCode")))){
            PersonAcctBo personAcct = new PersonAcctBo();
            personAcct.setPersonName(String.valueOf(personMap.get("personName")));
            personAcct.setPersonEmail(String.valueOf(personMap.get("personEmail")));
            personAcct.setPersonMobile(String.valueOf(personMap.get("personMobile")));
            personAcct.setIdNo(String.valueOf(personMap.get("idNo")));
            personAcct.setIdType(String.valueOf(personMap.get("idType")));
            personAcct.setPersonAddress(String.valueOf(personMap.get("personAddress")));
            dto.setPersonAcct(personAcct);

            SealDataBo sealData = new SealDataBo();
            String base64Data = null;
            try {
                base64Data = FileUtil.url2Base64(signFileDto.getSignatureFile());
            } catch (Exception e) {
                logger.error("文件url转base64失败>>>>",e);
                resultMap.put("resultCode",900);
                resultMap.put("errMsg", "文件url转base64失败>>>signatureFile");
                return resultMap;
            }
            sealData.setSealDataStr(base64Data);//签名图片 base64数据
            dto.setSealData(sealData);

            SignFileBo signFile = new SignFileBo();
            signFile.setContractId(signFileDto.getBusinessId());
            String base64Data2 = null;
            try {
                base64Data2 = FileUtil.url2Base64(signFileDto.getTemplateFile());
            } catch (Exception e) {
                logger.error("文件url转base64失败>>>>",e);
                resultMap.put("resultCode",900);
                resultMap.put("errMsg", "文件url转base64失败>>>templateFile");
                return resultMap;
            }
            signFile.setBytes(base64Data2);//待签署文件 base64数据
            signFile.setFileSuffix("pdf");
            signFile.setSignType("SINGLE");//签章类型 SINGLE:单页签章,MULTI:多页签章,EDGES:签骑缝章,KEY:关键字签
            List<SignPosBo> signPosList = new ArrayList<>();
            SignPosBo signPos = new SignPosBo();
            signPos.setPosType("COORDINATE");//定位类型 COORDINATE-坐标定位,KEYWORD-关键字定位,默认COORDINATE,SignType为关键字签署的时候,必须为KEYWORD
            String posPage = "1";
            if(signFileDto.getSignPageNo() != null && signFileDto.getSignPageNo() > 0){
                posPage = String.valueOf(signFileDto.getSignPageNo());
            }
            signPos.setPosPage(posPage);
            signPos.setPosX(signFileDto.getPosX());
            signPos.setPosY(signFileDto.getPosY());
            signPosList.add(signPos);
            signFile.setSignPos(signPosList);
            dto.setSignFile(signFile);
            Map<String,Object> signFileResult = signFileWithEsign(dto);
            if(signFileResult != null && 200 == Integer.parseInt(String.valueOf(signFileResult.get("resultCode")))){
                resultMap.put("resultCode",200);
                resultMap.put("signedFileUrl", String.valueOf(signFileResult.get("data")));
            }else{
                resultMap.put("resultCode",900);
                resultMap.put("errMsg", String.valueOf(signFileResult.get("errMsg")));
            }
            return resultMap;
        }
        else{
            logger.info("调用鉴权接口获取身份证信息失败>>>>[businessId=" + signFileDto.getBusinessId() + ",phone=" +signFileDto.getPhone() + "]");
            resultMap.put("resultCode",900);
            resultMap.put("errMsg", String.valueOf(personMap.get("errMsg")));
            return resultMap;
        }
    }

    /**
     * 调用电子签名接口，返回签署后的文件流
     * 并上传文件到服务器，接口返回文件路径
     * @param dto
     * @return
     */
    private Map<String,Object> signFileWithEsign(EsignRequestDto dto){
        Map<String,Object> resultMap = new HashMap<>();
        String signFileUrl = env.getProperty("url.esign.signFile.uncertified");
        String signFileAppId = env.getProperty("esign.appId");
        String signFileToken = env.getProperty("esign.token");
        TtSystemCallLog log = new TtSystemCallLog();
        log.setStartTime(new Date());
        log.setType(0);
        log.setCallUrl(signFileUrl);
        try {
            Map<String,String> headers = new HashMap<>();
            headers.put("appId",signFileAppId);
            headers.put("token",signFileToken);
            String params = JSON.toJSONString(dto);
            logger.info("文件签署请求参数:"+params);
            log.setSyncParam(params);
            String response = HttpUtil.postWithHeaders(signFileUrl, params, headers);
            logger.info("文件签署返回结果:"+response);
            log.setEndTime(new Date());
            log.setCallStatus(1);
            log.setResponseResult(response);
            logService.insert(log);
            if(StringUtils.isNotBlank(response)){
                String headerStr = JSONObject.parseObject(response).getString("header");
                if(StringUtils.isNotBlank(headerStr)){
                    Integer code = JSONObject.parseObject(headerStr).getInteger("code");
                    if(code == 10000000){
                        String bodyStr = JSONObject.parseObject(response).getString("body");
                        if(StringUtils.isNotBlank(bodyStr)){
                            SignUncertifiedRspBo bodyObj = JSONObject.parseObject(bodyStr,SignUncertifiedRspBo.class);
                            String signedUrl = uploadFile2Server(bodyObj.getBytes());
                            resultMap.put("resultCode",200);
                            resultMap.put("data", signedUrl);
                            return resultMap;
                        }
                    }else{
                        resultMap.put("resultCode",900);
                        resultMap.put("errMsg", JSONObject.parseObject(headerStr).getString("message"));
                        return resultMap;
                    }
                }else{
                    resultMap.put("resultCode",900);
                    resultMap.put("errMsg", "参数不符合格式,调用电签服务失败");
                    return resultMap;
                }
            }
        } catch (Exception e) {
           logger.error("电子签名文件签署异常>>>>",e);
            log.setResponseResult("失败");
            log.setEndTime(new Date());
            log.setCallStatus(0);
            logService.insert(log);
        }
        resultMap.put("resultCode",900);
        resultMap.put("errMsg", "调用电子签名服务获取文件失败");
        return resultMap;
    }

    private String uploadFile2Server(byte[] bytes){
        InputStream in = new ByteArrayInputStream(bytes);
        FileFtpUtil ftp = new FileFtpUtil();
        String name = MyDateUtil.formatDate(new Date(), pattern) + "." + FileFtpUtil.FILE_SUFFIX_PDF;//电子签名接口返回固定pdf类型
        String pathname = env.getProperty("spring.ftp.file.path.esign");
        boolean uploadFlag = ftp.uploadFile(pathname, name, in);
        //上传成功返回文件路径
        if(uploadFlag){
            String filePath = env.getProperty("spring.ftp.file.prefix") + pathname + "/" + name;
            return filePath;
        }
        return "";
    }

    /**
     * 根据手机号获取鉴权信息
     * @param phone
     * @return
     */
    private Map<String,Object> getAuthenticationInfo(String phone){
        Map<String,Object> resultMap = new HashMap<>();
        AuthCertificaInfoStatusDto authCertificaInfoStatusDto = new AuthCertificaInfoStatusDto();
        authCertificaInfoStatusDto.setPhone(phone);
        authCertificaInfoStatusDto.setParticleId("1");//默认查身份证信息

        String certificaInfo = getCertificaInfoFromAuth(authCertificaInfoStatusDto);

        String idCardNum = null;
        String idCardName = null;

        if(StringUtils.isNotBlank(certificaInfo)){
            JSONObject jsonObject = null;
            try {
                jsonObject = JSON.parseObject(certificaInfo);
            } catch (Exception e) {
                logger.error("调用鉴权服务获取身份证信息,JSON解析异常",e);
                resultMap.put("resultCode",900);
                resultMap.put("errMsg","调用鉴权服务获取身份证信息失败");
                return resultMap;
            }
            boolean parseResult = false;
            if (200 ==jsonObject.getInteger("resultCode")) {
                String dataStr = jsonObject.getString("data");
                if(StringUtils.isNotBlank(dataStr)){
                    JSONObject dataJson = null;
                    try {
                        dataJson = JSON.parseObject(dataStr);
                    } catch (Exception e) {
                        logger.error("调用鉴权服务获取身份证信息,JSON-DATA解析异常",e);
                        resultMap.put("resultCode",900);
                        resultMap.put("errMsg","调用鉴权服务获取身份证信息失败");
                        return resultMap;
                    }
                    if(StringUtils.equals("2",dataJson.getString("authenticateStatus"))){
                        idCardNum = dataJson.getString("idCardNum");
                        idCardName = dataJson.getString("idCardName");
                        if(StringUtils.isNotBlank(idCardNum) && StringUtils.isNotBlank(idCardName)){
                            parseResult = true;
                        }
                    }else{
                        logger.info("调用鉴权服务获取身份证信息>>>>>>认证未通过");
                        resultMap.put("resultCode",900);
                        resultMap.put("errMsg",dataJson.getString("authErrorMessage"));
                        return resultMap;
                    }
                }
            }
            if(!parseResult){
                resultMap.put("resultCode",900);
                resultMap.put("errMsg","调用鉴权服务获取身份证信息失败");
                return resultMap;
            }
        }
        resultMap.put("resultCode",200);
        resultMap.put("personName",idCardName);
        resultMap.put("personEmail","");
        resultMap.put("personMobile",phone);
        resultMap.put("idNo",idCardNum);
        resultMap.put("idType","MAINLAND");//默认中国大陆身份证
        resultMap.put("personAddress","");
        return resultMap;
    }

    /**
     * 调用鉴权服务获取身份证信息
     * @param dto
     * @return
     */
    public String getCertificaInfoFromAuth(AuthCertificaInfoStatusDto dto) {
        String result = null;
        //加密
        try {
            Date now = new Date();
            Random random = new Random();
            int ranNum = random.nextInt(9000) + 1000;
            String nonce = String.valueOf(ranNum);
            dto.setAppId(env.getProperty("authentication.appId"));
            String timestamp = String.valueOf(now.getTime());
            dto.setTimestamp(timestamp);
            dto.setNonce(nonce);
            String sign = env.getProperty("authentication.key") + nonce + timestamp;
            logger.info("加密前签名：" + sign);
            sign = CiphertextUtil.passAlgorithmsCiphering(sign, CiphertextUtil.SHA_1);
            logger.info("加密后签名：" + sign);
            dto.setSignature(sign);
            result = HttpUtil.post(env.getProperty("url.authentication.certificaInfoStatus"), JSON.toJSONString(dto));
            logger.info("service-authentication: result >>>> "+result);
        } catch (Exception e) {
            logger.error("调用鉴权服务获取身份证信息失败>>>>",e);
        }
        return result;
    }

}
