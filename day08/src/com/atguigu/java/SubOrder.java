package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author philo
 * @Description
 * @email 3424586889@qq.com
 * @Date 2021-11-07-15:49
 */
public class SubOrder extends Order<Integer>{//SubOrder：不再是泛型类

    public static <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;
    }

}
