package com.nmid.student.domain;

import java.util.List;

public class pageBean<T> {
	private int currentpage;
	private int recordpage;
	private int totalpage;
	private int totalrecord;
	private List<T> beanList;
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getRecordpage() {
		return recordpage;
	}
	public void setRecordpage(int recordpage) {
		this.recordpage = recordpage;
	}
	public int getTotalpage() {
		if(totalrecord % recordpage == 0){
			totalpage = totalrecord / recordpage;
		}else{
			totalpage = (totalrecord / recordpage) + 1;
		}
		return totalpage;
	}
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
}
