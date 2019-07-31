package concurrence;

/**
 *想要烹饪，但是没有食材，没有厨具。网上购买厨具比较方便，食材去超市买更放心。
 *
 *实现分析：在网上下订单配送厨具的期间，我们肯定不会闲着，可以去超市买食材。所以，在主线程里面另起一个子线程去网购厨具。
 *
 *但是，子线程执行的结果是要返回厨具的，而run方法是没有返回值的。所以，这才是难点，需要好好考虑一下。
 */
public class Cook_with_Thread {

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

    /**
     * 在线购物的类实现
     */
    static class OnlineShopping extends Thread{
        private Kitchenware kitchenware;

        @Override
        public void run(){
            System.out.println("第一步： 下单");
            System.out.println("第一步： 等待配送");
            try{
                Thread.sleep(5000);//模拟送货时间
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("第一步： 厨具送到");

            kitchenware = new Kitchenware();
        }
    }

    public static void main(String[] arguments) throws InterruptedException{

        long startTime = System.currentTimeMillis();
        /**
         * 第一步 网购厨具
         */
        OnlineShopping thread = new OnlineShopping();
        thread.start();

        /**
         * 第二步 去超市购买食材
         */
        Thread.sleep(2000);/**模拟食材购买时间*/

        Food food = new Food();
        System.out.println("第二步 食材到位");

        /**
         * 第三步 开始烹饪
         */
        thread.join();/**保证厨具送到*/
        System.out.println("第三步 开始展示厨艺");
        cook(thread.kitchenware, food);

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");


    }
}
