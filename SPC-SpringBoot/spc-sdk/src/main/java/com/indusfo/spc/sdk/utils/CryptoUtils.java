package com.indusfo.spc.sdk.utils;

import com.indusfo.spc.sdk.common.exception.impl.InvalidParamException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/25 00:17
 */
public class CryptoUtils {

    public static final String DES = "DES";
    public static final String AES = "AES/ECB/PKCS5Padding";


    public static SecretKey getAESKey(byte[] bytes){
        return new SecretKeySpec(bytes, AES);
    }

    public static SecretKey getDESKey(byte[] bytes) throws Exception{

        /** 从原始密钥数据创建DESKeySpec对象 */
        /** DESedeKeySpec会帮你生成24位秘钥，key可以是任意长度 */
        DESKeySpec spec = new DESKeySpec(bytes);

        //DESedeKeySpec spec = new DESedeKeySpec(bytes);

        /** 创建一个DES密钥工厂类，将DESKeySpec转换成SecretKey并返回 */
        return SecretKeyFactory.getInstance(DES).generateSecret(spec);
    }

    public static SecureRandom getRandom(){
        /** 生成一个可信任的随机数源 */
        return new SecureRandom();
    }

    public static Cipher getCipher(String algorithm) throws Exception{
        return Cipher.getInstance(algorithm);
    }

    public static byte[] _cipher(byte[] data, byte[] key, AlgorithmEnum algorithm, int mode) throws Exception{

        String code;
        switch (algorithm){
            case AES: code = "AES"; break;
            case DES: code = "DES"; break;
            default: code = ""; throw new InvalidParamException("错误的加密算法参数： " + code);


        }

        Cipher cipher = Cipher.getInstance(code);

        SecretKey secretKey = new SecretKeySpec(key, code);

        cipher.init(mode, secretKey, getRandom());

        return cipher.doFinal(data);
    }
}
