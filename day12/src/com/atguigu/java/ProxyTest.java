package com.atguigu.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 动态代理的举例
 *
 * @author shkstart
 * @create 2019 上午 10:18
 */

/*
反射的应用--动态代理 原理同静态代理一样

代理模式--接口定义了一套规范，有一个类实现了该接口称其为被代理类，另一个类也实现了该接口称其为代理类
    代理类封装被代理类的结构，由代理类的对象取代被代理类的对象，因此由之前通过被代理类的对象调用其结构的方式改为
    由代理类的对象来调用，代理类的对象决定是否以及何时使用原被代理类的的结构 由于代理类要封装被代理类因此代理类要求和被代理类一样要实现同一接口
    像是明星与经纪人，购房者与中介。。。

  静态代理是编译期 一个被代理类对应一个确定的代理类，在编译时期就确定下来了，因此不利于程序的扩展，一个代理类只能为一个接口服务，因此在开发过程中必然会产生过多的代理
  因为代理类的功能就是对被代理类进行封装功能相似，希望有一个通用的代理类完成这样的功能，而此通用代理类在编译期又不能被确定下来 代理某一个就不能代理另一个，只有在运行期才确定代理哪一个加载的被代理类
  加载哪个类就创建对应的代理类 运行期获得被代理类实现的接口 同时代理类实现这些接口 获得被代理类的结构就需要使用反射去做
 */

interface Human{

    String getBelief();

    void eat(String food);

}
//被代理类
class SuperMan implements Human{


    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}

class HumanUtil{

    public void method1(){
        System.out.println("====================通用方法一====================");

    }

    public void method2(){
        System.out.println("====================通用方法二====================");
    }

}

/*
要想实现动态代理，需要解决的问题？
问题一：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象。
问题二：当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。


 */

class ProxyFactory{
    //调用此方法，返回一个代理类的对象。解决问题一
    public static Object getProxyInstance(Object obj){//obj:被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }

}

class MyInvocationHandler implements InvocationHandler {

    private Object obj;//需要使用被代理类的对象进行赋值

    public void bind(Object obj){
        this.obj = obj;
    }

    //当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        HumanUtil util = new HumanUtil();
        util.method1();

        //method:即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj:被代理类的对象
        Object returnValue = method.invoke(obj,args);

        util.method2();

        //上述方法的返回值就作为当前类中的invoke()的返回值。
        return returnValue;

    }
}

public class ProxyTest {

    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        //proxyInstance:代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("四川麻辣烫");

        System.out.println("*****************************");

        NikeClothFactory nikeClothFactory = new NikeClothFactory();

        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);

        proxyClothFactory.produceCloth();

    }
}

