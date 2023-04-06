package com.atguigu.team.service;
/**
 * 
 * @Description 自定义的异常类
 * @author philos Email: 3424586889@qq.com 
 * @version
 * @date 2021年8月18日上午8:57:41
 */
public class TeamException extends Exception{

	 static final long serialVersionUID = -3387514229948L;
	
	 public TeamException() {
		 super();
	 }
	 
	 public TeamException(String msg) {
		 super(msg);
	 }
	 
}
