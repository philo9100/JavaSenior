package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Equipment;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;

import static com.atguigu.team.service.Data.*;
/**
 * 
 * @Description 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法
 * @author philos Email: 3424586889@qq.com 
 * @version
 * @date 2021年8月17日下午4:10:50
 */
public class NameListService {

	private Employee[] employees;
	
	/**
	 * 给employee这个数组及数组元素进行初始化
	 */
	public NameListService() {
	
		employees = new Employee[EMPLOYEES.length];
		
		for(int i = 0; i< employees.length; i++ ) {
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			
			//获取Employee的4个基本信息
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			
			Equipment equipment;
			double bonus;
			int stock;
			
			switch(type) {
			case EMPLOYEE:
				employees[i] = new Employee(id, name, age, salary);
				break;
			case PROGRAMMER:
				equipment = createEquipment(i);
				employees[i] = new Programmer(id, name, age, salary, equipment);
				break;
			case DESIGNER:
				equipment = createEquipment(i);
				bonus = Integer.parseInt(EMPLOYEES[i][5]);
				employees[i] = new Designer(id, name, age, salary, equipment, bonus);
				break;
			case ARCHITECT:
				equipment = createEquipment(i);
				bonus = Integer.parseInt(EMPLOYEES[i][5]);
				stock = Integer.parseInt(EMPLOYEES[i][6]);
				employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
				break;
			}
		}
	}
	
	/**
	 * 
	 * @Description 获取指定index位置上的员工的设备
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月17日下午7:57:18
	 * @param index
	 * @return
	 */
	private Equipment createEquipment(int index) {
		
		int key = Integer.parseInt(EQUIPMENTS[index][0]);
		
		String modelOrName = EQUIPMENTS[index][1];
		
		switch(key) {
		case PC: //21			
			String display = EQUIPMENTS[index][2];
			return new PC(modelOrName, display);
		case NOTEBOOK: //22
			double price = Double.parseDouble(EQUIPMENTS[index][2]);
			return new NoteBook(modelOrName, price);
		case PRINTER: //23
			String type = EQUIPMENTS[index][2];
			return new Printer(modelOrName, type);	
		}
		return null;
	}


	/**
	 * 
	 * @Description 获取当前所有员工
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月18日上午8:42:43
	 * @return
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}
	
	/**
	 * 
	 * @Description 获取指定ID的员工对象
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月18日上午8:44:53
	 * @param id
	 * @return
	 * @throws TeamException 
	 */
	public Employee getEmployee(int id) throws TeamException {
		for(int i = 0; i < employees.length; i++) {
			if(employees[i].getId() == id) {
				return employees[i];
			}
		}
		throw new TeamException("找不到指定的员工");
	}
}
