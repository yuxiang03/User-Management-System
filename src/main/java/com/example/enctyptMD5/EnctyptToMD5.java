package com.example.enctyptMD5;

import org.apache.commons.codec.digest.DigestUtils;

public class EnctyptToMD5 {
    public static String enctyptToMD5(String str){
        return DigestUtils.md2Hex(str);
    }
}
