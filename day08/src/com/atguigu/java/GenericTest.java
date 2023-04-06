package com.atguigu.java;

import org.junit.Test;

import java.util.*;

/**
 * @author philo
 * @Description
 *
 * 泛型的使用
 * 1.jdk 5.0新增的特性
 *
 * 2.在集合中使用泛型：
 *  总结：
 *  ① 集合接口或集合类在jdk5.0时都修改为带泛型的结构。
 *  ② 在实例化集合类时，可以指明具体的泛型类型
 *  ③ 指明完以后，在集合类或接口中凡是定义类或接口时，内部结构（比如：方法、构造器、属性等）使用到类的泛型的位置，都指定为实例化的泛型类型。
 *    比如：add(E e)  --->实例化以后：add(Integer e)
 *  ④ 注意点：泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，拿包装类替换
 *  ⑤ 如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型。
 *
 * 3.如何自定义泛型结构：泛型类、泛型接口；泛型方法。见 GenericTest1.java
 *
 * @email 3424586889@qq.com
 * @Date 2021-11-07-10:15
 */
public class GenericTest {

    //在集合中使用泛型之前的情况
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        //需求：存放学生的成绩
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);
        //由于集合list没有做任何的限制，在做add()操作时可以添加任何的Object类型
        //这就会导致添加一些非学生成绩的数据类型，没有类型检查 解决的方式是在添加操作时做一个类型的检查考虑用到泛型

        //问题一：类型不安全
        list.add("Tom");

        for (Object score : list) {
            //问题二：强转时，因为"Tom"是String类型的, 可能出现ClassCastException
            int stuScore = (Integer) score; //强制向下类型转换后 自动拆箱

            System.out.println(stuScore);
        }

    }

    //在集合中使用泛型的情况：以ArrayList为例
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>();//E extends Object

        list.add(78);
        list.add(87);
        list.add(99);
        list.add(65);
        //编译时，进行类型检查保证数据的安全
//        list.add("Tom");//报错 Required type: Integer

        //方式一：
//        for (Integer score : list) {
//            //避免了强转操作,类型转换异常
//            int stuScore = score;
//            System.out.println(stuScore);
//        }

        //方式二：
        Iterator<Integer> iterator = list.iterator();//Iterator接口在声明时就加了<E>
        while (iterator.hasNext()){
            int stuScore = iterator.next();
            System.out.println(stuScore);
        }
    }

    //在集合中使用泛型的情况：以HashMap为例
    @Test
    public void test3(){
//        HashMap<String, Integer> map = new HashMap<String, Integer>();//HashMap类在声明时就加了<K, V>
        //jdk7新特性：类型推断
        Map<String,Integer> map = new HashMap<>();

        map.put("Tom", 87);
        map.put("Jerry",87);
        map.put("Jack",67);

//        map.put(123,"ABC");
        //泛型的嵌套   Entry是定义在Map中的接口调用内部的结构需要指定Map接口 直调用Entry会报 Cannot resolve symbol 'Entry'
        Set<Map.Entry<String, Integer>> entry = map.entrySet();//put(key,value)整体作为一个Entrt<K,V>对象放在map中
                                                                // map.entrySet()又将一个个Entry<K,V>对象放在集合Set<E>中
                                                                //所以返回Set<map.Entry<K,V>>
        Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();
        //Iterator<E> iterator(); 定义在Collection<E>接口中 Set<E>继承于Collection<E> Set<E> E返回什么 则Iterator<E> E返回什么
        while(iterator.hasNext()){
            Map.Entry<String, Integer> e = iterator.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + "----" + value);
        }
    }


}
