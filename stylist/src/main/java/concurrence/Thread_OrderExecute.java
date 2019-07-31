package concurrence;

import sun.util.resources.ms.CalendarData_ms_MY;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 设定一个orderNum，每个线程执行结束之后，更新orderNum，指明下一个要执行的线程。并且唤醒所有的等待线程。
 *
 * 在每一个线程的开始，要while判断orderNum是否等于自己的要求值。不是，则wait，是则执行本线程。
 *
 * @author Lastcloud
 */
public class Thread_OrderExecute {

    public static void main(String[] args){
        Lock lock = new Lock(0);
        myThread[] threads = new myThread[10];

        for(int i =0; i < 10; i++){
            threads[i] = new myThread(lock, 9-i);
            threads[i].start();
        }
    }


    static class myThread extends Thread{

        private Lock lock;
        private int num = 0;

        public myThread(Lock lock, int num){
            this.lock = lock;
            this.num = num;
        }

        @Override
        public void run(){

            //判断资源是否占用
            synchronized (lock){
                //如果资源空闲，则判断是否已经打印完成
                while(lock.orderNum <= lock.MAX_VALUE){

                    if(lock.orderNum == num){
                        System.out.println("Thread" + lock.orderNum + " is executed");
                        lock.orderNum++;
                        if(lock.orderNum > lock.MAX_VALUE){
                            System.out.println("执行完毕");
                        }

                        //打印完毕后，唤醒其他所有的线程
                        lock.notifyAll();
                    }else{
                        try{
                            lock.wait();
                        }catch (InterruptedException e){
                            Logger.getLogger(myThread.class.getName()).log(Level.SEVERE, null, e);
                        }

                    }

                }

            }
        }
    }

    //锁对象
    static class Lock{
        int orderNum = 0;
        public final static int MAX_VALUE = 9;

        public Lock(int orderNum){
            this.orderNum = orderNum;
        }
    }


}






