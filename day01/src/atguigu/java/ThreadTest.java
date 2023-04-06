package atguigu.java;

/**
 * @author philo
 * @Description 多线程
 *
 * 多线程的创建：方式一：继承于Thread类
 * 1.创建一个继承于Thread类的子类
 * 2.重写Thread类的run()方法 -->将此线程执行的操作声明在run()中
 * 3.创建Thread类的子类的对象
 * 4.通过对象调用start()方法
 *
 * 例子：遍历100以内所有的偶数
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-20-20:42
 */

//1.创建一个继承于Thread类的子类
class MyThread extends Thread{

    //2.重写Thread类的run()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) { //快捷键fori回车
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i); //快捷键 i.sout
            }
        }
    }
}

public class ThreadTest {

    public static void main(String[] args) {

        //3.创建Thread类的子类的对象
        MyThread t1 = new MyThread();

        //4.通过对象调用start()方法 :①启动当前线程 ②调用当前线程的run()
        t1.start();

        //问题一：不能通过直接调用run()方法启动线程
//        t1.run();
//        直接调run()方法没有体现多线程的特性，相当于是对象调用方法,run()方法执行结束后在执行后面的代码逻辑
//        可以通过Thread.currentThread().getName()的方法通过获取线程名来看是否为同一个线程

        //问题二：再启动一个线程，遍历100以内的偶数，不可以还让已经start()的线程去执行，会报IllegalThreadStateException
//        t1.start();
//        ctrl点击start方法查看start方法的源码
//        我们需要重新创建一个线程的对象
        MyThread t2 = new MyThread();
        t2.start();

        //如下的操作仍然是在main线程(主线程)中执行的
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + ":" + i + "********************");
            }
        }

    }
}

















