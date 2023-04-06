package com.atguigu.java2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author philo
 * @Description
 * 创建线程的方式三：实现Callable接口。 --- JDK 5.0新增
 *
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式强大？
 * 1. call()可以有返回值的。
 * 2. call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3. Callable是支持泛型的
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-24-11:29
 */

/*
之前的Runnable接口里的run()不能有返回值这就给需要有返回值的线程带来了局限性，目前线程有返回值的需求
那就需要一个新的接口来提供一个能有返回值的方法就是Callable接口里的call(),Callable接口的实现类实现call()后可以有返回值
但是没有办法启动线程（Callable接口实现类的对象重写的call()），而Runnable接口的实现类Thread类里的start()可以启动线程
Thread类的构造器里可以放参数为Runnable接口实现类的对象
那么就需要一个类同时实现Runnable接口和Callable接口既满足线程（方法）有返回值也可启动线程（方法）
那就是作为中间桥梁的FutureTask类同时实现Runnable接口和Callable接口，FutureTask类的构造器可以放
参数为Callable接口实现类的对象来使线程（方法）能有返回值，FutureTask类的对象futureTask作为参数（Runnable接口实现类的对象）
放到Thread类的构造器里通过该构造器创建的Thread类的对象调用start()启动线程（方法）
FutureTask类里的get()可以获取FutureTask构造器参数Callable实现类重写的call()的返回值。
 */

//1.创建一个实现Callable的实现类
class NumThread implements Callable {

    //2.实现call方法，将此线程需要执行的操作声明在call()中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }

}


public class ThreadNew {

    public static void main(String[] args) {

        //3.创建Callable接口实现类的对象
        NumThread numThread = new NumThread();
        //4.将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTask(numThread);
        //5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(futureTask).start();

        try {
            //6.获取Callable中call方法的返回值
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}