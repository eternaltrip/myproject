package com.me.JavaWork.learn.Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class ListTest {
	
	public static void main(String[] args) {
		ListTest listTest = new ListTest();
		System.out.println(listTest.taskCount());
		
	}
	
	
	public String taskCount(){
		String personId = "";
		List<OSCaseTaskCount> taskCount = new ArrayList<>();
		OSCaseTaskCount temp = new OSCaseTaskCount();
		
		temp.setPersonId("11111");
		temp.setTaskCount(4);
		taskCount.add(temp);
		temp = new OSCaseTaskCount();
		temp.setPersonId("22222");
		temp.setTaskCount(4);
		taskCount.add(temp);
		temp = new OSCaseTaskCount();
		temp.setPersonId("33333");
		temp.setTaskCount(4);
		taskCount.add(temp);
		temp = new OSCaseTaskCount();
		temp.setPersonId("44444");
		temp.setTaskCount(4);
		taskCount.add(temp);
		temp = new OSCaseTaskCount();
		temp.setPersonId("55555");
		temp.setTaskCount(7);
		taskCount.add(temp);
		
		
	//由于结果是经过排序的，所以这里不会有前面的数据大于后面数据的情况
		
		Random random = new Random();
		if(taskCount.size() == 1){
			personId = taskCount.get(0).getPersonId();
		}else if(taskCount.size() > 1){
			List<OSCaseTaskCount> temp2 = new ArrayList<>();
			for (int i = 0; i < taskCount.size(); i++) {
				if(taskCount.get(i).getTaskCount() < taskCount.get(i + 1).getTaskCount() ){
					personId = taskCount.get(i).getPersonId();
					break;
				}else{
					if(temp2.size() == 0){
						temp2.add(taskCount.get(i));
						temp2.add(taskCount.get(i + 1));
					}else{
						if(i + 1 < taskCount.size()){
							temp2.add(taskCount.get(i + 1));
						}
					}
					if(i + 1 == taskCount.size() - 1){
						break;
					}
				}
			}
			if(temp2.size() != 0){
				personId =  temp2.get(random.nextInt(temp2.size())).getPersonId();
			}
		}
		return personId;
	}
	
	class OSCaseTaskCount{
		private String personId;//人员id
		private Integer taskCount;//目前的任务数量
		
		
		public String getPersonId() {
			return personId;
		}
		public void setPersonId(String personId) {
			this.personId = personId;
		}
		public Integer getTaskCount() {
			return taskCount;
		}
		public void setTaskCount(Integer taskCount) {
			this.taskCount = taskCount;
		}
	}
}
