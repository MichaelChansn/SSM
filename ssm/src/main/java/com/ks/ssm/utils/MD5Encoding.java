package com.ks.ssm.utils;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MD5Encoding {
	
	private static Log    log = LogFactory.getLog(MD5Encoding.class);

    public static String MD5Encode(String str)  {
        
        try {
            if (CommonUtils.isBlank(str)) {
                return null;
            }
            MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            byte tmp[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                sb.append(String.format("%02x", tmp[i]));
                
            }
            return sb.toString();
        } catch (Exception e) {
           log.error("MD5 error",e);
            return null;
        }
        } 

}
