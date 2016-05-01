package com.ks.ssm.utils;
import java.security.MessageDigest;

/**
 * 
 * @author yunfu.liu
 * @author qiyi.wxc
 * @version $Id: CommonUtils.java, v 0.1 2014 3:06:47 qiyi.wxc Exp $
 */
public class CommonUtils {

    /**
     * check string is empty
     * 
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     * 
     * @param str
     * 
     * @return
     */
    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * check string is not empty
     * 
     * <pre>
     * StringUtil.isBlank(null)      = false
     * StringUtil.isBlank("")        = false
     * StringUtil.isBlank(" ")       = false
     * StringUtil.isBlank("bob")     = true
     * StringUtil.isBlank("  bob  ") = true
     * </pre>
     * 
     * @param str
     * 
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * check string is all zero
     * 
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("00000000000000")     = true
     * StringUtil.isBlank("  0000  ") = true
     * </pre>
     * 
     * @param str
     * 
     * @return
     */
    public static boolean isZero(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i)) && str.charAt(i) != '0') {
                return false;
            }
        }

        return true;
    }

    /**
     *  SHA1 digest
     * 
     * @param str
     * @return
     */
    public static String sha1ByString(String str) {
        try {
            if (isBlank(str)) {
                return null;
            }
            MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes("UTF-8"));
            byte tmp[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tmp.length; i++) {
                sb.append(String.format("%02x", tmp[i]));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
