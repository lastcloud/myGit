package core.listener;

import org.springframework.context.ApplicationListener;
import toolkit.event.CommonEvent;

import javax.jms.Message;
import javax.jms.Session;

public class CommonEventListener implements ApplicationListener<CommonEvent> {

    @Override
    public void onApplicationEvent(final CommonEvent event){
        Message message = event.getMessage();
        Session session = event.getSession();
        String cmd = event.getCmd();
        Object data = event.getData();
    }


}
