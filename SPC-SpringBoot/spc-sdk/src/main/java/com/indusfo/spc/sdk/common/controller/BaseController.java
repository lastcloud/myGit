package com.indusfo.spc.sdk.common.controller;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/29 00:24
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String errorMsg = "";
    private String infoMsg = "";

    public String redirect(String uri) {
        return "redirect:" + uri;
    }

    public String forward(String uri){
        return "forward:" + uri;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getInfoMsg() {
        return infoMsg;
    }

    public void setInfoMsg(String infoMsg) {
        this.infoMsg = infoMsg;
    }

    public void setErrorMsg(String string, Map<Object, Object> model) {
        this.setErrorMsg(string);
        model.put("errorMsg", this.errorMsg);
    }

    public void setErrorMsg(String string, RedirectAttributes attr) {
        this.setErrorMsg(string);
        attr.addFlashAttribute("errorMsg", this.errorMsg);
    }

    public void setInfoMsg(String string, Map<Object, Object> model) {
        this.setInfoMsg(infoMsg);
        model.put("infoMsg", this.infoMsg);
    }

    public void setInfoMsg(String string, RedirectAttributes attr) {
        this.setInfoMsg(string);
        attr.addFlashAttribute("infoMsg", this.infoMsg);
    }

    public static JsonObject buildSuccess() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("succ", true);
        return jsonObject;
    }



    public static JsonObject buildSuccess(boolean flag) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("succ", flag);
        return jsonObject;
    }

    /** 格式化输出 */
    public static void printHeader(HttpServletRequest request) {
        Logger logger = LoggerFactory.getLogger(BaseController.class);

        Enumeration<String> headerNames = request.getHeaderNames();
        logger.info("=============println header start==============");
        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();
            logger.info(nextElement + "--" + request.getHeader(nextElement));
        }
        logger.info("=============println header end==============");
    }

    public static void printParam(HttpServletRequest request) {
        Logger logger = LoggerFactory.getLogger(BaseController.class);

        Enumeration<String> headerNames = request.getParameterNames();
        logger.info("=============println header start==============");
        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();
            logger.info(nextElement + "--" + request.getParameter(nextElement));
        }
        logger.info("=============println header end==============");
    }

    public static void print(HttpServletRequest request) {
        printParam(request);
        printHeader(request);
    }


}
