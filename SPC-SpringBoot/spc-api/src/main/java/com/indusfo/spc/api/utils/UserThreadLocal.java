package com.indusfo.spc.api.utils;

import com.indusfo.spc.sdk.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.Oneway;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/29 01:21
 */
public class UserThreadLocal extends ThreadLocal<User> {

    public final Logger logger  = LoggerFactory.getLogger(this.getClass());



    ThreadLocal<Integer> uidLocal = new InheritableThreadLocal();

    public UserThreadLocal() {
    }
    @Override
    public User get(){
        Thread t = Thread.currentThread();

        User user = super.get();

        if(user == null){
            logger.warn("get到空的用户");
        }

        return user;
    }

    @Override
    protected User initialValue(){
        Integer user_id = uidLocal.get();


    }

    /*

    @Override
    protected Collocation initialValue() {
        Integer collocationId = collocationIdThreadLocal.get();
        if (NumberUtils.isNotValid(collocationId)) {
            return null;
        }

        CollocationService collocationService = SpringUtils
                .getBean(CollocationService.class);
        Collocation collocation = collocationService
                .getCollocation(collocationId);
        if (collocation == null) {
            logger.warn("不存在的搭配师:" + collocationIdThreadLocal);
        }
        return collocation;
    }

    @Override
    public void remove() {
        Thread t = Thread.currentThread();
        logger.debug("getThreadLocalPartner.remove:" + t);
        super.remove();
        this.collocationIdThreadLocal.remove();
    }

    public void resetCollocationId(Integer collocationId) {
        this.remove();
        this.collocationIdThreadLocal.set(collocationId);
    }
    */



}
