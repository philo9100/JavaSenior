package com.atguigu.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 *
 * 将第三方的jar包导入工程 当前工程下new directory 添加jar包 add as directory
 * 使用第三方的jar包 实现 io的读写等等操作
 *
 * @author shkstart
 * @create 2019 上午 11:58
 */
public class FileUtilsTest {

    public static void main(String[] args) {
        File srcFile = new File("day10\\1.jpg");
        File destFile = new File("day10\\3.jpg");

        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
