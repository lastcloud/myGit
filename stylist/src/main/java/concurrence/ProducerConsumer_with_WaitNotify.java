package concurrence;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Vector;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 在同步块中调用wait()和notify()实现生产者/消费者模式
 * @author Lastcloud
 */
public class ProducerConsumer_with_WaitNotify {

    public static void main(String[] args){
        Vector sharedQueue = new Vector();
        int size = 4;
        Thread producerThread = new Thread(new Producer(sharedQueue, size), "Producer");
        Thread consumerThread = new Thread(new Consumer(sharedQueue, size), "Consumer");

        producerThread.start();
        consumerThread.start();
    }


    static class Producer implements Runnable{
        private final Vector sharedQueue;
        private final int SIZE;

        public Producer(Vector sharedQueue, int size){
            this.sharedQueue = sharedQueue;
            this.SIZE = size;
        }

        @Override
        public void run(){
            for(int i = 0; i < 7; i++){
                System.out.println("Produced: " + i);
                try{
                    produce(i);
                }catch (InterruptedException e){
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        private void produce(int i) throws InterruptedException{

            //wait if queue is full
            while (sharedQueue.size() == SIZE ){
                synchronized (sharedQueue){
                    System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting, size: " + sharedQueue.size());

                    sharedQueue.wait();
                }

            }

            //produce elements and notify consumers
            synchronized (sharedQueue){
                sharedQueue.add(i);
                sharedQueue.notifyAll();
            }

        }
    }

    static class Consumer implements Runnable{
        private final Vector sharedQueue;
        private final int SIZE;

        public Consumer(Vector sharedQueue, int size){
            this.sharedQueue = sharedQueue;
            this.SIZE = size;
        }

        public void run(){

            while(true){
                try{
                    System.out.println("Consumed: " + consume());
                    Thread.sleep(50);
                }catch (InterruptedException e){
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        private int consume() throws InterruptedException{

            //wait if queue is empty
            while (sharedQueue.isEmpty()){
                synchronized(sharedQueue){
                    System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting, size: " + sharedQueue.size());

                    sharedQueue.wait();
                }
            }

            //otherwise consume element and notify waiting producer
            synchronized (sharedQueue){
                sharedQueue.notifyAll();
                return (Integer) sharedQueue.remove(0);
            }
        }
    }
}
