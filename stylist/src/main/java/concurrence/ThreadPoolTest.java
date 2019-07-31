package concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {


    public static void main(String[] args){

        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("CachedThreadPool Test");
        for(int i=0; i<6; i++){

            final int index = i;

            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

/*
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        System.out.println("FixedThreadPool Test");

        for (int i = 0; i < 20; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }

*/
        //创建一个定长线程池，支持定时及周期性任务执行。
        ExecutorService scheduledThreadPoolPool = Executors.newScheduledThreadPool(3);

        ((ScheduledExecutorService) scheduledThreadPoolPool).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("ScheduledThreadPool Test: Delay 10 seconds.");
            }
        }, 10, TimeUnit.SECONDS);


        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    }



}
