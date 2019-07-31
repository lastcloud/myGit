package com.indusfo.spc.api.controller.impl;

import com.google.gson.JsonElement;
import com.indusfo.spc.api.controller.ILoginController;
import com.indusfo.spc.sdk.domain.User;
import org.springframework.http.HttpRequest;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/28 21:31
 */
public class LoginControllerimpl implements ILoginController {

    JsonResult<String> result = new JsonResult<>();
        try {
        result = userSsoService.login(vcUserMobile, vcUserPwd, type);
        if (!result.success) {
            return result;
        }

        return result;
    } catch (GlobalException e) {
        log.info("GlobalException", e);
        return result.failure(e.getMessage());
    } catch (Exception e) {
        log.error(Msg.UNKOWN_EXCEPTION, e);
        return result.failure(Msg.UNKOWN_EXCEPTION);
    }


    @Override
    public JsonElement login(String contact, String pwd, String loginType, String deviceID, String invitationCode, HttpRequest request) {

        User user =
    }
}
