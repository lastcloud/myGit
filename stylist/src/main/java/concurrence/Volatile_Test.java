package concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Volatile_Test {


        private static volatile int count = 0;

        private static void addCount() {
            for (int i = 0; i < 100; i++) {
                count++;
            }

            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }

        public static void main(String[] args) {
            ExecutorService executor = Executors.newCachedThreadPool();

            for (int i = 0; i < 100; i++) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Volatile_Test.addCount();
                    }
                });
            }

            executor.shutdown();
        }

}
