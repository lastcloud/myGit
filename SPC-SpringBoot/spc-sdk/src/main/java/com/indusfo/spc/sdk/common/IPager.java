package com.indusfo.spc.sdk.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Descirption 分页实体类接口
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/22 14:48
 */
public interface IPager extends Serializable {

    JsonElement json(Gson gson);

    String getPagerSerializedString();

    /**
     * @Decription 获取每页显示多少条
     * @Author JiWei
     * @Date 2019/07/22 15:05
     */
    int getPageSize();

    /**
     * @Decription 设置每页显示多少条
     * @param pageSize
     * @Author JiWei
     * @Date 2019/07/22 15:07
     */
    void setPageSize(int pageSize);

    int getCurrentPage();

    void setCurrentPage(Integer currentPage);

    /**
     * @Decription 如果有setTotalCount,通过此方法计算获得总页数
     * @Author JiWei
     * @Date 2019/07/22 16:03
     */
    int getTotalPages();


    Map<String, Object> getSearchMap();

    void setSearchMap(Map<String, Object> searchMap);

    <T> List<T> getItemList();

    void setItemList(List<?> itemList);

    int getTotalCount();

    void setTotalCount(int totalCount);

    long getCostTime();

    void setCostTime(long costTime);

}
