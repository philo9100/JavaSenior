package atguigu.java;

/**
 * @author philo
 * @Description
 *
 * 例子：创建三个窗口卖票，总票数为100张，使用实现Runnable接口的方式
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-21-19:58
 */
class Window1 implements Runnable{

    private int ticket = 100;


    @Override
    public void run() {
        int sum = 0 ;
        while(true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket--);
                sum++;
            }else{
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + "卖票数:" + sum);
    }
}

public class WindowTest1 {

    public static void main(String[] args) {

        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

        //对于只需要一个线程的操作，可以使用匿名子类的匿名对象的方式创建线程
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("线程操作的代码逻辑.......");
//                    }
//                }
//        ).start();


    }

}
