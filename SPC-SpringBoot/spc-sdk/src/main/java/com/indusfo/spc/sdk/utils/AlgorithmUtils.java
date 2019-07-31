package com.indusfo.spc.sdk.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;



/**
 * @Descirption  一些常用的算法，加密
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/23 11:08
 */
public class AlgorithmUtils {

    private final static Logger log = LoggerFactory.getLogger(AlgorithmUtils.class);

    private static final String UTF_8 = "UTF-8";

    private static Base64 base64 = new Base64(true);


    /** BASE64 编码 */
    public static String enBase64(byte[] bytes) { return base64.encodeAsString(bytes); }
    public static String enBase64(String s) {

        try {

            return enBase64(s.getBytes(UTF_8));

        } catch (UnsupportedEncodingException e) {

            log.error("deBase64 error occurred.", e);

        }

        return null;
    }

    /** BASE64 解码 */
    public static String deBase64(String s){

        try {
            return new String(base64.decode(s), UTF_8);
        }catch (UnsupportedEncodingException e){
            log.error("Decode错误！" + e);
        }

        return null;
    }

    public static String deBase64(byte[] bytes) {

        try {

            return new String(base64.decode(bytes), UTF_8);

        } catch (UnsupportedEncodingException e) {

            log.error("deBase64 error occurred.", e);
        }

        return null;
    }
    /**
     * 单向加密算法
     *
     * @param password   原始密码
     * @param encryption 加密算法类型，如：MD5/SHA
     * @return String 加密后的值
     */

    public static String encode(String password, AlgorithmEnum algorithm) {

        byte[] bytes;
        try {
            bytes = password.getBytes(UTF_8);
        } catch (UnsupportedEncodingException e) {
            bytes = null;
            log.error("转换字节流发生错误", e);
        }

        MessageDigest md;

        String code;

        switch (algorithm) {

            case MD5:
                code = "MD5";
                break;

            case SHA:
                code = "SHA";
                break;

            default:
                code = "MD5";
                log.info("加密算法不匹配，重设置为默认算法MD5");
                break;

        }

        try {
            /** first create an instance, given the provider */
            md = MessageDigest.getInstance(code);
        } catch (Exception e) {
            log.error("获取加密算法时发生错误！Exception: " + e);
            return password;
        }
        md.reset();
        md.update(bytes);

        byte[] encryptedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encryptedPassword.length; i++) {

            if ((encryptedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(encryptedPassword[i] & 0xff, 16));
        }
        return buf.toString();
    }


    private static byte[] getKey(String str, int len){

        byte[] key = str.getBytes();
        byte[] secretKey = new byte[len];

        for(int i = 0; i < key.length && i < secretKey.length; i++){
            secretKey[i] = key[i];
        }

        return secretKey;
    }

    private static byte[] getKey(String key){ return getKey(key, 8 ); }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */

    public static String encrypt(String data, String key) {
        return encrypt(data, key, AlgorithmEnum.DES);
    }
    public static String encrypt(String data, String key, AlgorithmEnum algorithm) {

        try {
            return enBase64(_encrypt(data, key, algorithm));
        } catch (Exception e) {
            log.error("Error Occurred When doing encryption!", e);
        }
        return null;
    }

    private static String _encrypt(String data, String key, AlgorithmEnum algorithm) {
        try {
            int radix = getSecretKeyRadix(algorithm);

            return HexUtils.byte2HexStr(CryptoUtils._cipher(data.getBytes(), getKey(key, radix),algorithm, Cipher.ENCRYPT_MODE));

        } catch (Exception e) {
            log.error("加密过程发生错误！", e);
        }
        return null;
    }

    public static String decrypt(String data, String key) {
        return decrypt(data, key, AlgorithmEnum.DES);
    }

    public static String decrypt(String data, String key, AlgorithmEnum algorithm) {

        try {

            return _decrypt(deBase64(data), key, algorithm);
        } catch (Exception e) {
            log.error("Error Occurred When doing decryption!", e);
        }
        return null;
    }

    private static int getSecretKeyRadix(AlgorithmEnum algorithm){
        switch (algorithm){
            case DES:return 8;
            case AES:return 24;
            default:return 8;
        }
    }

    private static String _decrypt(String data, String key, AlgorithmEnum algorithm) throws Exception {
        try {

            int radix = getSecretKeyRadix(algorithm);
            return new String(CryptoUtils._cipher(HexUtils.hexStr2Byte(data), getKey(key, radix),algorithm, Cipher.DECRYPT_MODE));

        } catch (Exception e) {
            log.error("解密过程发生错误！", e);
        }
        return null;

    }

    /*public static void main(String args[]) {


        String s = "{季威的加密算法EncryptionUtils.java} = /2019.07.25/";

        String url = "http://github.com/lastcloud";

        String pwd = "cwd";

        byte[] bt = {-99, 109};

        try {

            System.out.println(enBase64(url));
            System.out.println(deBase64(enBase64(url)));
            System.out.println(url.equals(deBase64(enBase64(url))));

            String code = encrypt(url, pwd);

            //code = deBase64(code);
            System.out.println("密钥： " + code);
            System.out.println("密钥长度：" + code.length());

            System.out.println(decrypt("NGFlYWI1M2RjMzRhYTJiNTViMzE0MmQwMjZmNThkOGJlYTUwZmNiNzMwMGQ2ZTllYmEzYjkzZTgw\n" +
                    "ODQ4OWI3MA", pwd));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}



