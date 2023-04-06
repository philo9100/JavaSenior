package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author philo
 * @Description
 *
 * 自定义泛型类
 *
 * @email 3424586889@qq.com
 * @Date 2021-11-07-15:20
 */
public class Order<T> {

    String orderName;
    int orderId;

    //类的内部结构就可以使用类的泛型

    T orderT;

    public Order(){
        //编译不通过
//        T[] arr = new T[10];//new 的对象是个具体的类的对象，此时的T相当于一个变量代替了一个具体的类
        //编译通过
        T[] arr = (T[]) new Object[10];
    }

    public Order(String orderName, int orderId, T OrderT){
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    //如下的三个方法都不是泛型方法
    public T getOrderT(){
        return orderT;
    }

    public void setOrderT(T orderT){
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    //静态方法中不能使用类的泛型，从面向对象的角度来讲类的泛型在实例化类的对象时赋值，而静态结构早于对象的创建
//    public static void show(T orderT){ //'com.atguigu.java.Order.this' cannot be referenced from a static context
//        System.out.println(orderT);
//    }

    public void show(){
        //编译不通过
//        try{
//
//        }catch(T t){//Required type: Throwable
//
//        }
    }

    //泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系
    //换句话说，泛型方法所属的的类是不是泛型类都没有关系
    //调用泛型方法前传入的参数类型不确定，再调用泛型方法后传入确定的形参类型，此时的泛型变量为确定的数据类型
    //泛型方法，可以声明为静态的，原因：泛型参数是在调用方法时确定的，并非在实例化类时确定
    public static <E> List<E> copyFromArrayToList(E[] arr){//在不确定形参类型之前又希望调用方法后传入唯一数据类型的方法可加入泛型

        ArrayList<E> list = new ArrayList<>();

        for (E e : arr) {
            list.add(e);
        }
        return list;
    }



}
