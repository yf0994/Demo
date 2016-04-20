package com.example.fengyin.plugin_framework;

import java.security.MessageDigest;

/**
 * Created by fengyin on 16-4-19.
 */
public class Util {
    public static String getPswMD5Str(String rawPsw) {
        final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = rawPsw.getBytes();
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(btInput);
            byte[] md = digest.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byteZ = md[i];
                str[k++] = hexDigits[byteZ >>> 4 & 0xf];
                str[k++] = hexDigits[byteZ & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
