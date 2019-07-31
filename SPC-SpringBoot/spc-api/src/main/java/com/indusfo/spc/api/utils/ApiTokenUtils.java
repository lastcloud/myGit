package com.indusfo.spc.api.utils;

import com.google.common.io.ByteSource;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.indusfo.spc.sdk.utils.AlgorithmEnum;
import com.indusfo.spc.sdk.utils.AlgorithmUtils;
import com.indusfo.spc.sdk.utils.KVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/29 15:22
 */
@Slf4j
public class ApiTokenUtils {

    public static final String USER_REDIS_PRE_KEY = "user_api_token";

    static JsonParser parser = new JsonParser();

    public static String generateTokenAnd();

    private static KVO generateApiToken(Integer key, String pwd, String deviceId,)


    private static KVObj generateToken(Integer pk, String deviceId,
                                       String tokenKey) {
        JsonObject json = new JsonObject();
        // 主键
        json.addProperty("pk", pk);
        // 设备编号
        json.addProperty("deviceId", deviceId);
        // 创建时间
        json.addProperty("ct", System.currentTimeMillis() / 1000);
        String tokenSource = json.toString();

        try {
            String tokenEncry = AlgorithmUtils.encode(
                    AlgorithmUtils.encrypt(tokenSource, tokenKey),
                    AlgorithmEnum.MD5);
            return new KVObj(tokenEncry, tokenSource + "||" + pk);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("生成token失败:{},将使用base64加密!", e.getLocalizedMessage());
        }

        return new KVObj(AlgorithmUtils.enBase64(tokenSource), tokenSource
                + "||" + pk);
    }


    /**
     * @Description :生成userToken
     * @Param [vcMobile, type]
     * @Return java.lang.String
     * @author: Yujn
     * @date: 2019/3/28 9:18 AM
     */
    public static String getUserToken(String vcMobile, String type){
        String hashAlgorithmName = "MD5";
        Object credentials =vcMobile;
        Object salt = ByteSource.Util.bytes(vcMobile);
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        String userToken = type+"_"+simpleHash.toString();
        return userToken;
    }


}
