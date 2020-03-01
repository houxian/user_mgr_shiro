package com.qiku.usermgr.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author houxianyong
 * @date 2020-2-26
 */
public class SHA1Util {

    private static Logger log = LoggerFactory.getLogger(SHA1Util.class);
    public static final int bufferSize = 1024;
    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String SHA1EncodeHex(byte[] data) {
        return byteArrayToHexString(SHA1Encode(data));
    }

    public static String SHA1EncodeFileHex(String fileName) {
        return byteArrayToHexString(SHA1EncodeFile(fileName));
    }

    public static byte[] SHA1EncodeFile(String fileName) {
        FileInputStream fis = null;
        DigestInputStream dis = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            fis = new FileInputStream(fileName);
            dis = new DigestInputStream(fis, md);
            byte[] data = new byte[1024];
            while (dis.read(data) != -1) ;
            return md.digest();
        } catch (Exception e) {
            return null;
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (Exception localException5) {
                }
            if (dis != null)
                try {
                    dis.close();
                } catch (Exception localException6) {
                }
        }
    }

    public static byte[] SHA1Encode(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            return md.digest(data);
        } catch (Exception e) {
            log.error("SHA1Encode error!",e);
        }
        return null;
    }

    public static String byteArrayToHexString(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(byteToHexString(data[i]));
        }
        return sb.toString();
    }

    /**
     * SHA加密
     *
     * @param strSrc
     *            明文
     * @return 加密之后的密文
     */
    public static String shaEncrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts
     *            数据源
     * @return 16进制字符串
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static void main(String[] args) {
        String pwd = shaEncrypt("admin");
        System.out.print(pwd);
    }

}
