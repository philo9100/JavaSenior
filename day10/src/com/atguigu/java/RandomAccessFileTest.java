package com.atguigu.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 *
 * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
 *
 * 4. 可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
 *
 * @author shkstart
 * @create 2019 上午 11:18
 */
public class RandomAccessFileTest {

    //对下面test的测试
    @Test
    public void exerTest(){
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("1.jpg"),"r");
            raf2 = new RandomAccessFile(new File("2.jpg"),"rw");

            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
     }

     @Test
     public void exerTest2() throws IOException {

        File file = new File("hello.txt");
        RandomAccessFile raf1 = new RandomAccessFile(file, "rw");

        raf1.seek(file.length());
        raf1.write("xyzoooooooo".getBytes());

        raf1.close();
     }


     //
    @Test
    public void test1() {

        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            //1.
            raf1 = new RandomAccessFile(new File("爱情与友情.jpg"),"r");
            raf2 = new RandomAccessFile(new File("爱情与友情1.jpg"),"rw");
            //2.
            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Test
    public void test2() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//将指针调到角标为3的位置
        raf1.write("xyz".getBytes());//

        raf1.close();

    }

    //对下面test的练习
    @Test
    public void exerTest3(){
        RandomAccessFile raf1 = null;
        try {
            //造随机输入输出流
            raf1 = new RandomAccessFile("hello1.txt", "rw");
            //将指针调到要插入操作的位置
            raf1.seek(3);
            //保存指针后面要被覆盖的内容
            StringBuilder builder = new StringBuilder((int) new File("hello1.txt").length());
            byte[] buffer = new byte[20];
            int len;
            while ( (len = raf1.read(buffer)) != -1){ //将字节内容读到buffer当中，并将每次读的长度赋给len，如果读到末尾返回-1
                builder.append(new String(buffer,0,len));
            }
            //将指针调会要插入的位置
            raf1.seek(3);
            raf1.write("666Philo9100郭子豪".getBytes());//写入要插入的内容

            raf1.write(builder.toString().getBytes());//写入之前被覆盖的内容，
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
    使用RandomAccessFile实现数据的插入效果
     */
    @Test
    public void test3() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//将指针调到角标为3的位置
        //保存指针3后面的所有数据到StringBuilder中
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());//将Builder的底层数组长度设置成文件的长度
        byte[] buffer = new byte[20];
        int len;
        while((len = raf1.read(buffer)) != -1){
            builder.append(new String(buffer,0,len)) ;
        }
        //调回指针，写入“xyz”
        raf1.seek(3);
        raf1.write("xyz".getBytes());

        //将StringBuilder中的数据写入到文件中
        raf1.write(builder.toString().getBytes());

        raf1.close();

        //思考：将StringBuilder替换为ByteArrayOutputStream
    }
}
