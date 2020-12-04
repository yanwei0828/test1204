package com.gtmc.carapp.service.workorder.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;

public class MD5Utils {

    private MD5Utils(){

    }

    public static String md5Hex(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String md5Hex(String str,String salt){
        salt =new String(Base64.getDecoder().decode(salt.getBytes()));
        return DigestUtils.md5Hex(str+salt);
    }



}
