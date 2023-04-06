package atguigu.exer;

/**
 * @author philo
 * @Description 创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 *
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-21-9:02
 */
public class ThreadDemo {
    public static void main(String[] args) {
        //方式一：创建Thread类的子类
//        MyThread1 m1 = new MyThread1();
//        MyThread2 m2 = new MyThread2();
//
//        m1.start();
//        m2.start();

        //方式二:创建Thread类的匿名子类的方式
        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){
                    if(i % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 100; i++  ){
                    if (i % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" +i);
                    }
                }
            }
        }.start();
    }
}


class MyThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}