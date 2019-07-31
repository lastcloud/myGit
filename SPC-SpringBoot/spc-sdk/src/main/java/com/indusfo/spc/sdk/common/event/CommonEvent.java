package com.indusfo.spc.sdk.common.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

import javax.jms.Message;
import javax.jms.Session;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/22 13:47
 */

public class CommonEvent extends ApplicationEvent {

    private static final long serialVersionUID = -7070505578176446988L;

    String cmd;
    Object obj;

    Message message;
    Session session;

    public CommonEvent(String cmd) {
        this(cmd, null);
    }

    public CommonEvent(String cmd, Object obj) {
        super(cmd);

        this.cmd = cmd;
        this.obj = obj;
    }

    public CommonEvent(String cmd, Object obj, Message message, Session session) {
        super(cmd);

        this.cmd = cmd;
        this.obj = obj;

        this.message = message;
        this.session = session;
    }

}
