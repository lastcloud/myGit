package core.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import toolkit.event.CommonEvent;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class ListenerWithAnnotation {

    @EventListener
    public void listen(CommonEvent event){
        Message message = event.getMessage();
        Session session = event.getSession();
        String cmd = event.getCmd();
        Object data = event.getData();
    }
}
