package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author shkstart
 * @create 2019 上午 9:36
 */
public class CollectionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();

        coll.add(123);
        coll.add(456);
        coll.add(343);
        coll.add(343);

        coll.forEach(System.out::println);
    }


    //练习：在List内去除重复数字值，要求尽量简单
    public static List duplicateList(List list) {
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }
    @Test
    public void test2(){
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list2 = duplicateList(list);
        for (Object integer : list2) {
            System.out.println(integer);
        }
    }

    @Test
    public void test3(){
        //hashset先比较hashcode在比较equals
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");

        set.add(p1);
        set.add(p2);
        System.out.println(set);

        p1.name = "CC";
        set.remove(p1);
        System.out.println(set);
        /*开始的时候p1和p2计算出哈希值并按照对应的哈希值计算的索引存到数组中相应的位置上之后将p1位置上的name改成CC
                                  此时remove(p1)是按照name修改为CC的p1计算出对应的哈希值对应的索引在数组中寻找存储的位置
                                  而此位置与开始的时候name为AA的p1计算出的哈希值在数组中存储的位置不同
                                  因此后计算的哈希值对应的位置上并没有p1进行删除操作之后并没有删除开始位置上的p1 */

        set.add(new Person(1001,"CC"));
        System.out.println(set);//[Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}]
        /*
        同上，按照name修改为CC的p1计算出的哈希值对应的索引在数组中寻找存储的位置该位置上并没有元素
        */
        set.add(new Person(1001,"AA"));
        System.out.println(set);//Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}, Person{id=1001, name='AA'}
        /*
        * name为AA的p1计算出的哈希值在数组中存储的位置相同但是调用equals()方法发现name不同原来的AA改成了CC
        * */

    }


}
