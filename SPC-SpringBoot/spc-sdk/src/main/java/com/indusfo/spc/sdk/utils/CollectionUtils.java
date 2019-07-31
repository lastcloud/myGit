package com.indusfo.spc.sdk.utils;

import java.util.*;

/**
 * @Descirption 一些常用的Java容器有关的帮助方法
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/22 10:06
 */
public class CollectionUtils {

    /**
     * @Decription 判断集合是否为空
     * @param collection
     * @Return boolean
     * @Author JiWei
     * @Date 2019/07/22 10:08
     */
    public static boolean isEmpty(Collection<?> collection){return collection == null || collection.isEmpty();}

    public static boolean isEmpty(Map<?, ?> map){return map == null || map.size() < 1;}

    public static boolean isEmpty(Object[] o){return o == null || o.length < 1;}

    public static boolean isNotEmpty(Collection<?> collection){return collection != null && !collection.isEmpty();}

    public static boolean isNotEmpty(Map<?, ?> map){return map != null && map.size() > 0;}

    public static boolean isNotEmpty(Object[] o){return !isEmpty(o);}

    /**
     * @Decription 去除List中的元素
     * @param coll
     * @param o
     * @Return java.util.List<T>
     * @Author JiWei
     * @Date 2019/07/22 10:51
     */
    public static <T> List<T> removeListElement(List<T> list, Object o){

        if(CollectionUtils.isEmpty(list)){
            return list;
        }

        if(null == o){
            return list;
        }
        List<T> result = new ArrayList<T>();
        for(Object e : list ){
            if(!o.equals(e)){

                result.add((T)e);
            }
        }
        return result;
    }

    public static Collection<?> convert(Object o) {
        if(o instanceof Collection) {
            return (Collection<?>) o;
        }

        if(o instanceof Object[]) {
            return Arrays.asList((Object[]) o);
        }

        throw new RuntimeException("cannot convert to Collections.Object:" + o);
    }

    /**
     * @Decription 两个list比较找出相对与后者来说不同的部分
     * @param aList
     * @param bList
     * @Return java.util.List<T> 差异元素组成的List
     * @Author JiWei
     * @Date 2019/07/22 10:56
     */
    public static <T> List<T> compareLatterFindDiff(List<T> aList, List<T> bList) {
        
        if(aList == bList) {
            return null;
        }

        if(bList == null) {
            return null;
        }
        if(aList == null) {
            return bList;
        }

        List<T> diff = new ArrayList<T>();
        
        for (T bObj : bList) {
            boolean isFind = false;
            for (T aObj : aList) {

                if(bObj == aObj || bObj.equals(aObj)) {
                    isFind = true; break;
                }
            }

            if(!isFind) { //删除
                diff.add(bObj);
            }
        }

        return diff;
    }


}
