package com.indusfo.spc.sdk.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;

import java.text.DateFormat;

/**
 * @Descirption Encapsulated Gson Builder
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/23 08:34
 */
public class SpcGsonBuilder {

    private static final GsonBuilder instance = new GsonBuilder();

    static{
        instance.disableHtmlEscaping();/** 防止特殊字符出现乱码 */
        //instance.excludeFieldsWithoutExposeAnnotation();/** 不对没有用@Expose注解的属性进行操作 */
        instance.enableComplexMapKeySerialization();/** Map-key为复杂对象时需要开启此方法 */
        instance.setDateFormat(DateFormat.LONG);/** 设置时间格式 */
        //.setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
        instance.setPrettyPrinting();/** 输出格式化 */
    }

    public static Gson create() { return instance.create(); }
}
