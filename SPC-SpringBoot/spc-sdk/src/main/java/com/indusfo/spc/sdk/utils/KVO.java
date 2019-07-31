package com.indusfo.spc.sdk.utils;

import javafx.beans.binding.ObjectExpression;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/30 00:15
 */
public class KVO {

    public String key;
    public Object value;

    public KVO(){}
    public KVO(String key, Object value){
        this.key = key;
        this.value = value;
    }

}
