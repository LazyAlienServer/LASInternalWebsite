package com.las.lasbackenduser3000.utils.md5;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author sunyinuo
 */
public class Md5Slat {

    public static String md5Slat(String text,Integer slat){
        return DigestUtils.md5Hex(text+slat);
    }

}
