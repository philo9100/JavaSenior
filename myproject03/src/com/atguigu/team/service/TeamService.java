package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;

/**
 * 
 * @Description 关于开发团队成员的管理，添加，删除等。
 * @author philos Email: 3424586889@qq.com 
 * @version
 * @date 2021年8月18日上午10:43:46
 */
public class TeamService {

	private static int counter = 1; //给memberId赋值使用
	private final int MAX_MEMBER = 5; //限制开发团队的人数
	private Programmer[] team = new Programmer[MAX_MEMBER]; //保存开发团队成员 
	private int total; //记录开发团队中实际的人数
	
	public TeamService() {
		super();
	}
	
	/**
	 * 
	 * @Description 获取开发团队中的所有成员
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月18日上午11:14:20
	 * @return
	 */
	public Programmer[] getTeam() {
		Programmer[] team = new Programmer[total];
		for(int i = 0; i < team.length; i++ ) {
			team[i] = this.team[i];
		}
		return team;
	}
	
	/**
	 * 
	 * @Description 将指定的员工添加到开发团队中
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月18日上午11:23:06
	 * @param e
	 * @throws TeamException 
	 */
	public void addMember(Employee e) throws TeamException {
//		成员已满，无法添加
		if(total >= MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加");
		}
//		该成员不是开发人员，无法添加
		if(!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}
//		该员工已在本开发团队中
		if(isExist(e)) {
			throw new TeamException("该员工已在本开发团队中");
		}
//		该员工已是某团队成员
//		该员工正在休假
		Programmer p = (Programmer) e; //一定不会出现ClassCastException
//		if("BUSY".equals(p.getStatus().getNAME())) {
//			throw new TeamException("该员工已是某团队成员");
//		}else if("VOCATION".equals(p.getStatus().getNAME())) {
//			throw new TeamException("该员工正在休假，无法添加");
//		} //修改Status类为enum关键字修饰，只定义了三个状态的对象，没有再定义属性，用不到getName了
		switch(p.getStatus()){//byte/short/char/String/枚举类对象
			case BUSY:
				throw new TeamException("该员工已是某团队人员");
			case VOCATION:
				throw new TeamException("该员工正在休假，无法添加");
		}

//		if(!("FREE".equals(p.getStatus().getNAME()))) {
//			throw new TeamException("该员工已是某团队成员或该员工正在休假");
//		}
		
//		团队中至多有一名架构师
//		团队中至多有两名设计师
//		团队中至多有三名程序员
		//获取team团队已有成员中架构师，设计师，程序员的人数
		int numOfArch = 0, numOfDes = 0, numOfProg = 0;
		for(int i= 0; i < total; i++) {
			if(team[i] instanceof Architect) {
				numOfArch++;
			}else if(team[i] instanceof Designer) {
				numOfDes++;
			}else if(team[i] instanceof Programmer) {
				numOfProg++;
			}
		}
		
		if(p instanceof Architect) {
			if(numOfArch >= 1) {
				throw new TeamException("团队中至多有一名架构师");
			}
		}else if(p instanceof Designer) {
			if(numOfDes >= 2) {
				throw new TeamException("团队中至多有两名设计师");
			}
		}else if(p instanceof Programmer) {
			if(numOfProg >= 3) {
				throw new TeamException("团队中至多有三名程序员");
			}
		}
		//将p (或e)添加到现有的team中
		team[total++] = p;
		//p的状态转换
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
	}
	
	/**
	 * 
	 * @Description 判断指定的员工是否已经存在于现有的开发团队中
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月18日上午11:52:46
	 * @param e
	 * @return
	 */
	private boolean isExist(Employee e) {

		for(int i = 0 ;i < total; i++) {
//			if(team[i].getId() == e.getId()) {
//				return true;
//			}
			return team[i].getId() == e.getId();
		}
		return false;
	}

	/**
	 * 
	 * @Description 从团队中删除成员
	 * @author philos Email: 3424586889@qq.com 
	 * @date 2021年8月18日上午11:23:11
	 * @param memberId
	 * @throws TeamException 
	 */
	public void removeMember(int memberId) throws TeamException {
		int i = 0;
//		boolean isflag = true;
		for(; i < total; i++) {
			if(team[i].getMemberId() == memberId) {
				team[i].setStatus(Status.FREE);
//				isflag = false;
				break;
			}
		}
		
		//未找到指定memberId的情况
//		if(isflag) {throw new TeamException("找不到指定memberId的员工，删除失败");}
		if(i == total) {
			throw new TeamException("找不到指定memberId的员工，删除失败");
		}
		
		//找到指定memberId的情况
		//后一个元素覆盖前一个元素，实现删除操作
		for(int j = i + 1; j < total; j++) {
			team[j - 1] = team[j];
		}
		
		//写法1
//		team[total - 1] = null;
//		total--;
		//或2
		team[--total] = null;
		
	}
}
