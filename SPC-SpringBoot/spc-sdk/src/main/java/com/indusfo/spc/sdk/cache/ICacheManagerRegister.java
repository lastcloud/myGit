package com.indusfo.spc.sdk.cache;

import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/22 09:05
 */
public interface ICacheManagerRegister {

    CacheManager cacheManager(ApplicationContext context);

}

