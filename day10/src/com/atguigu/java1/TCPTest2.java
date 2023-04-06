package com.atguigu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * 实现TCP的网络编程
 * 例题2：客户端发送文件给服务端，服务端将文件保存在本地。
 *
 * @author shkstart
 * @create 2019 下午 3:53
 */
public class TCPTest2 {

    /*
    这里涉及到的异常，应该使用try-catch-finally处理
     */
    @Test
    public void client() throws IOException {
        //1.造本地客户端的socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);
        //2.本地客户端socket向外传输数据
        OutputStream os = socket.getOutputStream();
        //3.读入本地客户端要向外传输的数据  读到内存（程序）当中
        FileInputStream fis = new FileInputStream(new File("1.jpg"));
        //4.
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        //5.
        fis.close();
        os.close();
        socket.close();
    }

    /*
    这里涉及到的异常，应该使用try-catch-finally处理
     */
    @Test
    public void server() throws IOException {
        //1.造本地服务器的socket
        ServerSocket ss = new ServerSocket(9090);
        //2.获取客户端socket传来的数据
        Socket socket = ss.accept();
        //3.客户端socket传来的数据读到本地服务器的内存（程序）上
        InputStream is = socket.getInputStream();
        //4.将读来的数据写入本地服务器硬盘上
        FileOutputStream fos = new FileOutputStream(new File("beautifulSky.jpg"));
        //5.
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        //6.
        fos.close();
        is.close();
        socket.close();
        ss.close();

    }
}
