package atguigu.java;

/**
 * @author philo
 * @Description
 *
 * 测试Thread类中的常用方法
 * 1.start():启动当前线程，调用当前线程的run()
 * 2.run():通常需要重写Thread类中的此方法
 * 3.currentThread():静态方法，返回执行当前代码的线程
 * 4.getName():获取当前线程的名字
 * 5.setName():设置当前线程的名字
 * 6.yield():释放当前cpu正在执行的线程的执行权,但是不排除cup执行的下一刻又会抢到该线程的执行权并执行该线程
 * 7.join():在线程a中调用线程b的join方法，此时线程a就进入阻塞状态，直到线程b完全执行完之后，线程a才结束阻塞状态
 * 8.stop():已过时，每当执行此方法时，强制结束当前线程。
 * 9.sleep(long millitime):让当前线程"睡眠"指定的millitime毫秒，在指定的millitime毫秒时间内，当前线程是阻塞状态
 * 10.isAlive()：判断当前线程是否还存活
 *
 *
 * 1.线程的优先级
 * MAX_PRIORITY: 10
 * MIN_PRIORITY: 1
 * NORM_PRIORITY: 5 -->默认的优先级
 * 2.如何获取和设置当前线程的优先级：
 * getPriority():获取线程的优先级
 * setPriority(int priority):设置线程的优先级
 *
 * 说明：高优先级的线程要抢占低优先级的线程CPU的执行权。但是只是从概率上讲，高优先级的线程高概率的情况下被执行
 *      并不意味着只有当高优先级的线程执行完之后，低优先级的线程才执行
 *
 *
 *
 * 线程通信：wait() / notify() / notifyAll() :此三个方法定义在Object类中的
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-21-10:02
 */

class HelloThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){

//                try {
//                    sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }

//            if(i % 20 == 0) {
//                Thread.currentThread().yield(); //this.yield();
//            }

        }
    }

    //调用父类中带参的构造器直接给线程命名
    public HelloThread(String name) {
        super(name);
    }

}

public class ThreadMethodTest {

    public static void main(String[] args) {

//        通过构造器给线程命名
        HelloThread h1 = new HelloThread("Thread: 1");

        //设置分线程的优先级
        h1.setPriority(Thread.MAX_PRIORITY);

//        h1.setName("线程一");
        h1.start();

        //给主线程命名
        Thread.currentThread().setName("主线程");
        //设置分线程的优先级
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }

//            if(i == 20){
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }

//        System.out.println(h1.isAlive());

    }
}
