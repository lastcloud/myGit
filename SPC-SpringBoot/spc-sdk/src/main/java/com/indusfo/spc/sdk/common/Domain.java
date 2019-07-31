package com.indusfo.spc.sdk.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/18 14:13
 */
public class Domain implements Serializable {

    private static final long  serialVersionUID = 1L;


    /**
     * @Decription 引入ToStringBuilder优化日志打印
     * @param
     * @Return java.lang.String
     * @Author JiWei
     * @Date 2019/07/18 16:36
     */
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

