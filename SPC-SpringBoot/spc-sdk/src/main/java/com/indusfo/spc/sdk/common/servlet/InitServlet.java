package com.indusfo.spc.sdk.common.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/27 05:11
 */
public class InitServlet extends HttpServlet {
    
    private static final long serialVersionUID = -127883069200665138L;


    /**描述*/
    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        servletContext.setAttribute("ctx", servletContext.getContextPath());
        
        /** 暂时注释掉 */
        /*String suffix = DictionarySettingUtils.getParameterValueWithDefault("baseconfig.url_suffix", "");//网站url的后缀
        servletContext.setAttribute("suffix", suffix);*/
        
        super.init(config);
    }
    
}
