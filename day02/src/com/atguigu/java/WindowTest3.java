package com.atguigu.java;

/**
 * @author philo
 * @Description
 *
 * 使用同步方法解决实现Runnable接口的线程安全问题
 *
 * 关于同步方法的总结：
 * 1. 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
 *
 * 2. 非静态的同步方法，同步监视器是：this
 *    静态的同步方法，同步监视器是：当前类本身
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-22-14:47
 */
class Window3 implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while (true) {

            show(); //this.show();  Window3 w = new Window3(); w即是this 只有一个对象
                    //本质上就是将操作共享数据的代码逻辑提出后封装成一个方法将该方法用synchronized标识为同步
        }
    }

    private synchronized void show() {//同步监视器：this 因为是this调用了show(),Window3 w = new Window3(); w即是this
        //synchronized (this){

        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
            ticket--;
        }
        //}
    }
}

public class WindowTest3 {

    public static void main(String[] args) {

        Window3 w = new Window3();//针对Runnable接口的实现类只有一个对象

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
