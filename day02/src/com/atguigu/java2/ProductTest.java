package com.atguigu.java2;

/**
 * @author philo
 * @Description
 * 线程通信的应用：经典例题：生产者/消费者问题
 *
 * 生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员
 * 会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品
 * 了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 *
 * 分析：
 * 1. 是否是多线程问题？是，生产者线程，消费者线程
 * 2. 是否有共享数据？是，店员（或产品）
 * 3. 如何解决线程的安全问题？同步机制,有三种方法
 * 4. 是否涉及线程的通信？是
 *
 * 多线程操作共享数据，操作共享数据的的代码逻辑与共享数据写在在同一个类里， 涉及操作共享数据的的代码逻辑封装在同步方法中，同步监视器就是该类的对象，同时也是线程通信时，方法的调用者
 * 通过线程类调用操作共享数据的同步方法，多个线程共用同一个同步监视器
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-24-10:32
 */
class Clerk{

    private int productCount = -10;

    //生产产品
    public synchronized void produceProduct() {

        if(productCount < 20){
            productCount++;
            System.out.println(Thread.currentThread().getName() + ":开始生产第" + productCount + "个产品");

            this.notify(); //clerk.notify() 唤醒被clerk.wait()的一个线程，因为消费者被clerk.wait()阻塞，因此可以唤醒消费者线程

        }else{
            //等待
            try {
                this.wait(); //clerk.wait()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //消费产品
    public synchronized void consumeProduct() {
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName() + ":开始消费第" + productCount + "个产品");
            productCount--;

            this.notify(); //clerk.notify() 唤醒被clerk.wait()的一个线程，因为生产者被clerk.wait()阻塞，因此可以唤醒生产者线程
        }else{
            //等待
            try {
                this.wait(); //clerk.wait()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Producer extends Thread{//生产者

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":开始生产产品.....");

        while(true){

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.produceProduct();
        }

    }
}

class Consumer extends Thread{//消费者

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":开始消费产品.....");

        while(true){

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.consumeProduct();
        }
    }
}

public class ProductTest {

    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        Producer p1 = new Producer(clerk);
        p1.setName("生产者1");

        Consumer c1 = new Consumer(clerk);
        c1.setName("消费者1");
        Consumer c2 = new Consumer(clerk);
        c2.setName("消费者2");

        p1.start();
        c1.start();
        c2.start();

    }
}
