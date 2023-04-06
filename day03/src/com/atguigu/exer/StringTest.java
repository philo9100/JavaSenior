package com.atguigu.exer;

/**
 * 一道面试题
 * @author shkstart
 * @create 2019 上午 11:32
 */
public class StringTest {

    String str = new String("good");  //成员变量str加载在堆中
    char[] ch = { 't', 'e', 's', 't' };

    public void change(String str, char ch[]) {
        str = "test ok";//字面量的方式进行赋值 在字符串常量池  局部变量str加载在栈中
        ch[0] = 'b';
    }
    public static void main(String[] args) {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.ch);
        System.out.println(ex.str);//good  String的不可变性
        System.out.println(ex.ch);//best

    }

}

