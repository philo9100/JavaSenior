package com.atguigu.team.junit;

import org.junit.Test;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;

/**
 * 
 * @Description NameListService类的测试
 * @author philos Email: 3424586889@qq.com 
 * @version
 * @date 2021年8月18日上午9:11:14
 */
public class NameListServiceTest {

	@Test	
	public void testGetAllEmployees() {
		NameListService service = new NameListService();
		
		Employee[] employees = service.getAllEmployees();
		for(int i = 0; i < employees.length; i++) {
			System.out.println(employees[i].toString());
		}	
	}
	
	@Test
	public void testGetEmployee() {
		NameListService service = new NameListService();
		int id = 1;
		 id = 101;
		try {
			Employee employee = service.getEmployee(id);
			System.out.println(employee.toString());
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
