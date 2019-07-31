package com.indusfo.spc.sdk.json;

import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/23 09:09
 */
@Component
public class GsonUtils {

    public static JsonElement parse(String str){

        try{

            return new JsonParser().parse(str);

        }catch (JsonSyntaxException e){

            e.printStackTrace();

        }

        return null;

    }

    public static JsonArray parseJsonArray(String jsonArray){
        if(StringUtils.isBlank(jsonArray)){
            return null;
        }
        JsonElement element = parse(jsonArray);
        if(element != null && element.isJsonArray()){
            return element.getAsJsonArray();
        }
        return null;
    }

    public static JsonObject parseJsonObject(String json) {
        if(StringUtils.isBlank(json)) {
            return null;
        }

        JsonElement element = parse(json);
        if(element != null && element.isJsonObject()) {
            return element.getAsJsonObject();
        }
        return null;
    }
}
