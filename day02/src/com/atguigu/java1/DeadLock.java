package com.atguigu.java1;
//死锁的演示
class A {
	public synchronized void foo(B b) { //同步监视器：A类的对象：a
		System.out.println("当前线程名: " + Thread.currentThread().getName()
				+ " 进入了A实例的foo方法"); // ①
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
		System.out.println("当前线程名: " + Thread.currentThread().getName()
				+ " 企图调用B实例的last方法"); // ③
		b.last();
	}

	public synchronized void last() {//同步监视器：A类的对象：a
		System.out.println("进入了A类的last方法内部");
	}
}

class B {
	public synchronized void bar(A a) {//同步监视器：b
		System.out.println("当前线程名: " + Thread.currentThread().getName()
				+ " 进入了B实例的bar方法"); // ②
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
		System.out.println("当前线程名: " + Thread.currentThread().getName()
				+ " 企图调用A实例的last方法"); // ④
		a.last();
	}

	public synchronized void last() {//同步监视器：b
		System.out.println("进入了B类的last方法内部");
	}
}

public class DeadLock implements Runnable {
	A a = new A();
	B b = new B();

	public void init() {
		Thread.currentThread().setName("主线程");
		// 调用a对象的foo方法
		a.foo(b);
		System.out.println("进入了主线程之后");
	}

	public void run() {
		Thread.currentThread().setName("副线程");
		// 调用b对象的bar方法
		b.bar(a);
		System.out.println("进入了副线程之后");
	}

	public static void main(String[] args) {
		DeadLock dl = new DeadLock();

		new Thread(dl).start();

		dl.init();
	}
}

/*
开始的时候 new Thread(dl).start(); 是副线程调用run() 接着调b的bar()（同步方法，同步监视器b） 此时同步监视器b盯副线程
dl.init(); 是主线程调用 a的foo()（同步方法，同步监视器a） 此时同步监视器a盯主线程
bar()（同步方法） 需要调a的last()（同步方法，同步监视器a）才能结束 也就是需要同步监视器a盯副线程
foo()（同步方法） 需要调b的last()（同步方法，同步监视器b）才能结束 也就是需要同步监视器b盯主线程
因为a在盯主线程没法盯副线程（或者说是b在盯副线程没法盯主线程 所以foo() 没法结束）所以bar() 没法结束同步监视器b需要一直盯副线程，没法盯主线程
那么foo() 就没法结束，同步监视器a就需要一直盯主线程就没法盯副线程，bar() 就没法结束陷入死锁
 */
