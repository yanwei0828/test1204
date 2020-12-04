package com.gtmc.carapp.service.workorder.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.security.PrivateKey;
import java.util.*;

/**
 *
 * @author zhangkai
 */
public class SafetyTransferData {

    /**
     *
     * 签名参数 key 设定为 sign
     */
    private static final String SIGN_CODE = "signCode";

    private SafetyTransferData(){

    }

    /**
     *
     * 使用私钥解密
     *
     * @param data 待解密字符串
     * @param privateKeyStr base64编码的私钥
     * @return
     * @throws Exception
     */
    public static Map<String,Object> decodeSafetyTransferData(String data,String privateKeyStr )throws Exception{
        PrivateKey privateKey = RSAUtils.getPrivateKey(privateKeyStr);
        byte[] decodeData = RSAUtils.blockDecrypt(privateKey,Base64.getDecoder().decode(data) );
        data = new String(decodeData);
        System.out.println("解密后字符串："+data);
        return JSONObject.parseObject(data, Map.class);
    }




    /**
     *
     * 验签
     *
     * @param mapParameter
     * @return
     * @throws Exception
     */
    public static Boolean verifySign(Map<String,Object> mapParameter,String salt) throws Exception{
        String pendingSignatureStr = getPendingSignatureStrForJSON(mapParameter);
        System.out.println("待签串");
        System.out.println(pendingSignatureStr);
        String verifySign = MD5Utils.md5Hex(pendingSignatureStr);
        System.out.println("检验签名："+verifySign);
        if(verifySign.equals(salt)){
            return true;
        }
        return false;
    }


    /**
     *
     * 获取待签串
     *
     * @param mapParameter
     * @return
     */
    public static  String getPendingSignatureStrForJSON(Map<String, Object> mapParameter){
        List<String> parameterKey = new ArrayList<>();
        for (String key:mapParameter.keySet()){
            if(!SIGN_CODE.equals(key)){
                parameterKey.add(key);
            }
        }
        Collections.sort(parameterKey);
        StringBuffer pendingSignature = new StringBuffer();
        Integer index =0;
        for (String key:parameterKey){
            Object object = mapParameter.get(key);
            String value = "";
            if(object instanceof  String|| object instanceof Long|| object instanceof  Integer){
                value = String.valueOf(object);
            }else if(object instanceof JSONArray){
                value = ((JSONArray)object).toJSONString();
            }else if(object instanceof JSONObject){
                value = ((JSONObject)object).toJSONString();
            }

            if(StringUtils.isNotEmpty(value)){
                if(index!=0){
                    pendingSignature.append("&");
                }
                index++;
                pendingSignature.append(key);
                pendingSignature.append("=");
                pendingSignature.append(value);
            }
        }
        return pendingSignature.toString();
    }




}
