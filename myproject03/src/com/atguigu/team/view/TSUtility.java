package com.atguigu.team.view;

import java.util.*;

/**
 * 
 * @Description 项目中提供了TSUtility.java类,可用来方便地实现键盘访问
 * @author philo Email: 3424586889@qq.com 
 * @version
 * @date 2021年8月17日下午12:10:55
 */
public class TSUtility {

	private static Scanner scanner = new Scanner(System.in); 
	
	/**
	 * 
	 * @Description 该方法读取键盘，如果用户键入'1' - '4'中的任意字符，则方法返回，返回值为用户输入字符
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月17日下午12:14:26
	 * @return 选择的操作对应的字符
	 */
	public static char readMenuSelection() {
		char c;
		for(; ; ) {
			String str = readKeyBoard(1, false);
			c = str.charAt(0);
			if(c != '1' && c != '2' && 
					c != '3' && c != '4') {
					System.out.print("选择有误！请重新输入：");
			}else break;
		}
		return c;
	}
	
	
	/**
	 * 
	 * @Description 该方法提示并等待，直到用户按回车键后返回
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月17日下午12:25:09
	 */
	public static void readReturn() {
		System.out.print("按回车键继续...");
		readKeyBoard(100,true);
	}
	
	
	/**
	 * 
	 * @Description 该方法从键盘读取一个长度不超过2位的整数，并将其作为方法的返回值
 	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月17日下午12:32:14
	 * @return
	 */
	public static int readInt() {
		int n;
		for(; ; ) {
			String str = readKeyBoard(2,false);
			try {
				n = Integer.parseInt(str);
				break;
			}catch(NumberFormatException e) {
				System.out.print("数字输入有误，请重新输入！");
			}
		}
		return n;
	}
	
	
	/**
	 * 
	 * @Description 从键盘读取'Y'或'N'，并将其作为方法的返回值
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月17日下午12:42:07
	 * @return
	 */
	public static char readConfirmSelection() {
		char c;
		for(; ; ) {
			String str = readKeyBoard(1, false).toUpperCase();
			c = str.charAt(0);
			if(c == 'Y' || c == 'N') {
				break;
			}else {				
				System.out.println("选择有误，请重新选择：");
			}
		}
		return c;
	}

	private static String readKeyBoard(int limit, boolean blankReturn) {
		String line = "";
		
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			if(line.length() == 0) {
				if(blankReturn) return line;
				else continue;
			}
			
			if(line.length() < 1 || line.length() > limit) {
				System.out.println("输入长度（不大于" + limit + ")错误，请重新输入：");
				continue;
			}
			break;
		}
		return line;
	}
}




