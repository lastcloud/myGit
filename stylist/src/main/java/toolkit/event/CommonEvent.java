package toolkit;

import org.springframework.context.ApplicationEvent;
import javax.jms.Message;
import javax.jms.Session;

public class CommonEvent extends ApplicationEvent {

    private static final long serialVersionUID = 5630833815851069438L;
    String cmd;
    Object data;

    Message message;
    Session session;



    public CommonEvent(String cmd, Object data){
        super(cmd);
        this.cmd = cmd;
        this.data = data;
    }
    public CommonEvent(String cmd){
       this(cmd, null);
    }

    public CommonEvent(String cmd, Object data, Message msg, Session session){
        super(cmd);
        this.cmd = cmd;
        this.data =data;
        this.message = msg;
        this.session = session;
    }

    public String getCmd(){return this.cmd;}
    public void setCmd(String cmd){this.cmd = cmd;}
    public Object getData(){return this.data;}
    public void setData(Object data){this.data = data;}
    public Message getMessage(){return this.message;}
    public void setMessage(Message message){this.message = message;}
    public Session getSession(){return this.session;}
    public void setSession(Session session){this.session = session;}
}
