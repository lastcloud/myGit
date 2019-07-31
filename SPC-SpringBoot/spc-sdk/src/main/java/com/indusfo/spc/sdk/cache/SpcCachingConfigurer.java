package com.indusfo.spc.sdk.cache;

import com.indusfo.spc.sdk.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/22 09:07
 */
@Configuration
@ComponentScan
@EnableCaching(proxyTargetClass = true)
public class SpcCachingConfigurer implements CachingConfigurer, ApplicationContextAware {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String Cache_Map = "Map";
    public static final String Cache_Simple = "Simple";
    public static final String Cache_ConcurrentMap = "ConcurrentMap";

    CompositeCacheManager _cacheManager;
    Collection<CacheManager> _cacheList = new ArrayList<>();

    public CompositeCacheManager initCacheManager(){

        if(_cacheManager == null){
            CompositeCacheManager compositeCacheManager = new CompositeCacheManager();
            compositeCacheManager.setCacheManagers(_cacheList);
            //compositeCacheManager.setFallbackToNoOpCache(true);
            _cacheManager = compositeCacheManager;
        }

        return _cacheManager;

    }

    @Override
    public CacheManager cacheManager() {
        return initCacheManager();
    }

    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(cacheManager());
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, ICacheManagerRegister> beansOfType = applicationContext.getBeansOfType(ICacheManagerRegister.class);

        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager(Cache_ConcurrentMap, Cache_Map, Cache_Simple);

        _cacheList.add(concurrentMapCacheManager);

        if(CollectionUtils.isNotEmpty(beansOfType)){

            for(Map.Entry<String, ICacheManagerRegister> entry : beansOfType.entrySet()){

                logger.info("注册缓存： " + entry.getValue());

                CacheManager manager = entry.getValue().cacheManager(applicationContext);

                if(manager != null){
                    _cacheList.add(manager);
                }else{
                    logger.warn("CacheManager is Null.", entry.getKey());
                }

            }
        }

        if(this._cacheManager != null){
           _cacheManager.setCacheManagers(_cacheList);
        }



    }
}
