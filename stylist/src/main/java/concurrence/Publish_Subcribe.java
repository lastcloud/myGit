package concurrence;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

interface IPublisher<V>{
    /**
     * 发布者接口
     */
     void publish(IMessage msg);
}
interface ISubcriber<V>{

    void subcribe();
    void unsubcribe();
    void update();
}
interface IMessage{}

class MessageQueue<V extends IMessage>{
    private String name;
    private V msg;
    final int QUEUE_CAPACITY = 20;
    //消息传输的存储队列
    private BlockingQueue<IMessage> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    //订阅者管理
    public List<ISubcriber> subcribers = new ArrayList<>();


    /**
     * @author lastcloud
     * 构造方法
     */
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    /*
    public MessageQueue(V msg, String name){
        this.msg =msg;
        this.name =name;
    }
    */


}
/*
class MyPublisher<V> implements IPublisher{

    private String name;
    private MessageQueue<IMessage> queue = new MessageQueue<>();
    public MyPublisher(String name){
        super();
        this.name = name;

    }
    public void publish(IMessage msg, String name){};

    public void subcribe(ISubcriber subcriber){
        queue.subcribers.add(subcriber);
    }
    public void unsubcribe(ISubcriber subcriber){
        queue.subcribers.remove(subcriber);
    }
}
*/
public class Publish_Subcribe {

    static class MyMessage implements IMessage{}

    static class MySubcriber<V> implements ISubcriber{
       public void subcribe(){};
       public void unsubcribe(){};
       public void update(){};
    }
}
