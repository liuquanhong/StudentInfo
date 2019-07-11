package com.nmid.student.service;

import com.nmid.student.dao.studentDao;
import com.nmid.student.domain.Score;
import com.nmid.student.domain.Student;

import java.sql.SQLException;
import java.util.List;




public class studentService {
	studentDao dao = new studentDao();
	public void add(Student student)  {
		try {
			dao.add(student);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void delete(String number) {
		Student student;
			student = dao.delete(number);
			if(student == null){
				throw new NullObjectException("不存在该学号对应的学生");
			}
		
		
	}
	public void update(String number,Student stu) {
		Student student;
		student = dao.update(number,stu);
		if(student == null)
			throw new NullObjectException("不存在该学号对应的学生");
		
	}
	/*public pageBean<Student> query(String name,String number,int recordpage,int currentpage){
		pageBean<Student> pagebeanList = dao.query(name,number,recordpage,currentpage);
		if(pagebeanList == null){
			throw new NullObjectException("û�в�ѯ����ѧ��");
		}
		return pagebeanList;
	}*/
	public List<Student> query(String name,String number){
		List<Student> list = dao.query(name, number);
		if(list.size() == 0)
			throw new NullObjectException("不存在该学号对应的学生");
		return list;
	}
	public Student ajaxfindByNumber(String number) {
		Student student = dao.findByNumber(number);
		return student;
	}

	public Score queryScore(String scoreName){
		Score score = dao.queryScore(scoreName);
		return score;
	}
}
