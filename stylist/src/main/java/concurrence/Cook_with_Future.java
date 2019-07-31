package concurrence;


import toolkit.Println;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cook_with_Future {

    /**
     * 食材类
     */
    static class Food{};

    /**
     * 厨具类
     */
    static class Kitchenware{};

    /**
     * 烹饪方法
     */
    static void cook(Kitchenware kitchenware, Food food){};

    public static void  main(String[] args) throws InterruptedException, ExecutionException {

        long startTime = System.currentTimeMillis();

        Callable<Kitchenware> onlineShopping = new Callable<Kitchenware>() {
            @Override
            public Kitchenware call() throws Exception {
                System.out.println("第一步 下单");
                Println.println("第一步 等待送货");
                Thread.sleep(5000);
                System.out.println("第一步 厨具送到");

                return new Kitchenware();
            }
        };

        FutureTask<Kitchenware> task = new FutureTask<>(onlineShopping);
        new Thread(task).start();

        /**
         * 第二步 去超市购买食材
         */
        Thread.sleep(2000);
        Food food = new Food();
        System.out.println("第二步 食材到位");

        /**
         * 烹饪
         */
        if(!task.isDone()){
            System.out.println("第三步 厨具还没到。心情好就等着");
        }
        Kitchenware kitchenware = task.get();
        System.out.println("厨具到位，开始烹饪");
        cook(kitchenware, food);


        MyCallable callable1 = new MyCallable(1000);
        MyCallable callable2 = new MyCallable(10000);


        FutureTask<String> futureTask1 = new FutureTask<>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<>(callable2);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(futureTask1);
        executor.execute(futureTask2);

        while (true){
            try{

                if(futureTask1.isDone() && futureTask2.isDone()){
                    System.out.println("Done");
                    executor.shutdown();
                    return;
                }

                if(!futureTask1.isDone()){
                    System.out.println("FutureTask1 output = " + futureTask1.get());
                }

                System.out.println("Waiting for FutureTask2 to complete");

                String s = futureTask2.get(2L, TimeUnit.SECONDS);

                if (s!=null){
                    System.out.println("FutureTask2 output = " + s);
                }

            }catch (InterruptedException e){
                e.printStackTrace();
                Logger.getLogger(MyCallable.class.getName()).log(Level.SEVERE, null, e);
            }catch (TimeoutException e){
                //e.printStackTrace();
                System.out.println("Timeout2 for FutureTask2");
            }
        }





    }

    static class MyCallable implements Callable<String>{

        private long  waitTime;

        public MyCallable(int timeInMillis){
            this.waitTime = timeInMillis;
        }

        @Override
        public String call() throws Exception{
            Thread.sleep(waitTime);
            //return the thread name exucuting this callable task
            return Thread.currentThread().getName();
        }

    }
}
