package com.atguigu.java1;

/**
 * @author philo
 * @Description
 *
 * 演示线程的死锁问题
 *
 * 1.死锁的理解：不同的线程分别占用对方需要的同步资源不放弃，
 * 都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 *
 * 2.说明：
 * 1）出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 * 2）我们使用同步时，要避免出现死锁。
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-23-19:50
 */
public class ThreadTest {

    public static void main(String[] args) {

        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();


        //Thread匿名子类的匿名对象创建线程a
        new Thread(){
            @Override
            public void run() {

                synchronized (s1){ //此时锁s1盯着线程a，直到线程a结束才会释放锁s1

                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    synchronized (s2){ //线程a结束需要锁s2盯着线程a，而s2正盯着线程b，则线程a无法结束
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }

            }
        }.start();


        //接口匿名实现类的匿名对象创建线程b
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2){ //此时锁s2盯着线程b，直到线程b结束才会释放锁s2

                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1){ //线程b结束需要锁s1盯着线程b，而s1正盯着线程a，则线程b无法结束
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }


            }
        }).start();

//    都需要对方线程先结束释放锁后自己才能结束，最终导致线程都无法结束
    }


}
