package atguigu.java;

/**
 * @author philo
 * @Description
 * 例子：创建三个窗口卖票，总票数为100张
 *
 * 存在线程的安全问题，待解决
 *
 * @email 3424586889@qq.com
 * @Date 2021-08-21-14:32
 */

class Window extends Thread{

    private static int ticket = 100;

    @Override
    public void run() {
        int sum = 0;
        while(true){

            if (ticket > 0) {
                System.out.println(getName() + ":卖票，票号为" + ticket--);
                sum++;
            }else{
                break;
            }
        }
        System.out.println(getName() + "卖票数：" + sum);

    }

    public Window(String name){
        super(name);
    }
}


public class WindowTest {

    public static void main(String[] args) {

        Window t1 = new Window("窗口1");
        Window t2 = new Window("窗口2");
        Window t3 = new Window("窗口3");

//        t1.setName("窗口1");
//        t2.setName("窗口2");
//        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }

}
