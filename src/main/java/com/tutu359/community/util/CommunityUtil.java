package com.tutu359.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CommunityUtil {

    //生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    //MD5加密
    public static String getMD5(String text){
        if(StringUtils.isBlank(text)){
            return null;
        }else{
            return DigestUtils.md5DigestAsHex(text.getBytes(StandardCharsets.UTF_8));
        }
    }
}
