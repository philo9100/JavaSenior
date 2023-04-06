package com.atguigu.java1;

/**
 * @author philo
 * @Description
 *
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-23-19:11
 */
public class BankTest {

}

//同步代码块
class Bank{

    private Bank(){}

    private static Bank instance = null;

    public static Bank getInstance(){

        //方式一：效率稍差
//        synchronized (Bank.class) {
//            if(instance == null){
//
//                instance = new Bank();
//            }
//            return instance;
//        }

        //方式二：效率更高
        //如果第一个线程先进去创建对象了，instance就不是null了，后面的线程就不用再等着了
        // 直接return 第一个线程创建好的instance就可以了
        if(instance == null){

            synchronized (Bank.class) {
                if(instance == null){

                    instance = new Bank();
                }

            }
        }
        return instance;
    }
}

//同步方法
//class Bank{
//
//    private BanK();
//
//    private static Bank instance = null;
//
//    public static synchronized Bank getInstance() {
//
//        if(instance == null){
//            instance = new Bank();
//        }
//        return instance;
//    }
//}