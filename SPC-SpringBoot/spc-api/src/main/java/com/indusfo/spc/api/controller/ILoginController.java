package com.indusfo.spc.api.controller;

import com.google.gson.JsonElement;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/27 15:41
 */
public interface ILoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PostMapping(value = "/login")
    @ResponseBody
    JsonElement login(@RequestParam String contact, @RequestParam String pwd, @RequestParam String loginType, @RequestParam(required = false)String deviceID, @RequestParam(required = false)String invitationCode, HttpRequest request);



}
