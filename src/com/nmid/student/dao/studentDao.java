package com.nmid.student.dao;

import com.nmid.student.domain.Score;
import com.nmid.student.domain.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import tools.TxQueryRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class studentDao {
	QueryRunner runner = new TxQueryRunner();
	public void add(Student student) throws SQLException {
		String sql = "insert into student values(?,?,?,?,?,?,?,?,?)";
		Object[] paramters = {student.getId(),student.getName(),student.getGender(),student.getAge(),
							  student.getStdNumber(),student.getMajor(),Double.parseDouble(student.getComputerScore()),Double.parseDouble(student.getEnglishScore()),
								Double.parseDouble(student.getMathScore())};
		runner.update(sql, paramters);
		}
	public Student findByNumber(String number) {
		String sql = "select * from student where stdNumber=?";
		Student student;
		try {
			student = runner.query(sql, new BeanHandler<Student>(Student.class), number);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return student;
	}
	public Student update(String number,Student stu)  {
		Student student;
		student = this.findByNumber(number);
		if(student == null)
			return null;
		String sql = "update student set stdNumber="+number;
		StringBuilder builder = new StringBuilder(sql);
		ArrayList<Object> params = new ArrayList<Object>();
		System.out.println(stu.getAge()+":"+stu.getGender()+":"+stu.getMajor()+":"+stu.getName());
		if(stu.getAge() != null){
			builder.append(",age=?");
			params.add(stu.getAge());
		}
		if(stu.getName() != null){
			builder.append(",name=?");
			params.add(stu.getName());
		}
		if(stu.getMajor() != null){
			builder.append(",major=?");
			params.add(stu.getMajor());
		}
		if(stu.getGender() != null){
			builder.append(",gender=?");
			params.add(stu.getGender());
		}
		builder.append(" where stdNumber=?");
		sql = builder.toString();
		System.out.println(sql);
		params.add(stu.getStdNumber());
		Object[] param = this.toArray(params);
		try {
			runner.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return student;
	}
	/*public pageBean<Student> query(String name,String number,int recordpage,int currentpage){
		String sql = "select * from student where 1=1";
		StringBuilder builder1 = new StringBuilder("select count(*) from student");
		StringBuilder builder = new StringBuilder(sql);
		StringBuilder sqlbuilder = new StringBuilder("select * from student");
		StringBuilder limitbuilder = new StringBuilder(" limit ?,?");
		ArrayList<Object>params = new ArrayList<Object>();
		if(name != null && number != null){
			builder.append(" and name=? and stdNumber=?");
			params.add(name);
			params.add(number);
		}else if(name != null){
			builder.append(" and name=?");
			params.add(name);
		}else if(number != null){
			builder.append(" and stdNumber=?");
			params.add(number);
		}
		pageBean<Student> beanList = new pageBean<Student>();
		List<Student> list;
		beanList.setCurrentpage(currentpage);
		beanList.setRecordpage(recordpage);
		try {
			Object[] param = this.toArray(params);
			sql = builder.toString();
			Number num = (Number) runner.query(builder.append(builder1).toString(), new ScalarHandler());
			int totalrecord = num.intValue();
			beanList.setTotalrecord(totalrecord);
			params.add((currentpage-1)*recordpage);
			params.add(recordpage);
			list= runner.query(builder.append(limitbuilder).toString(), new BeanListHandler<Student>(Student.class), param);
			beanList.setBeanList(list);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if(beanList == null){
			return null;
		}
		return beanList;
	}*/
	public List<Student> query(String name,String number){
		String sql = "select * from student where 1=1";
		StringBuilder builder = new StringBuilder(sql);
		ArrayList<Object>params = new ArrayList<Object>();
		if(name.length() == 0 && number.length() == 0){
			builder.append(" and name=? and stdNumber=?");
			params.add(name);
			params.add(number);
		}else if(name.length() != 0){
			builder.append(" and name=?");
			params.add(name);
		}else if(number.length() != 0){
			builder.append(" and stdNumber=?");
			params.add(number);
		}
		
		List<Student> list;
		try {
			Object[] param = this.toArray(params);
			sql = builder.toString();
			list= runner.query(sql, new BeanListHandler<Student>(Student.class), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		/*if(beanList == null){
			return null;
		}*/
		return list;
	}
	public Student delete(String number){
		Student student = null;
		student = this.findByNumber(number);
		if(student == null){
			return null;
		}else{
			String sql = "delete from student where stdNumber=?";
			try {
				runner.update(sql, student.getStdNumber());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return student;
		}
	}

	public Score queryScore(String scoreName){
		Score score;
		if(scoreName.equals("englishScore"))
			 score = queryenglishScore();
		else if(scoreName.equals("mathScore"))
			score = querymathScore();
		else
			score = querycomputerScore();
		return score;
	}

	private Score queryenglishScore() {
		String sql = "select max(englishScore) from student";
		Score score = new Score();
		try {
			double max = (double) runner.query(sql,new ScalarHandler());
			score.setMaxScore(max);
			String sqll = "select min(englishScore) from student";
			double min = (double)runner.query(sqll,new ScalarHandler());
			score.setMinScore(min);
			String sqlll = "select avg(englishScore) from student";
			double avg = (double)runner.query(sqlll,new ScalarHandler());
			score.setAvgScore(avg);
			System.out.println(score.getMaxScore());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return score;
	}

	private Score querymathScore() {
		String sql = "select max(mathScore) from student";
		Score score = new Score();
		try {
			double max = (double) runner.query(sql,new ScalarHandler());
			score.setMaxScore(max);
			String sqll = "select min(mathScore) from student";
			double min = (double)runner.query(sqll,new ScalarHandler());
			score.setMinScore(min);
			String sqlll = "select avg(mathScore) from student";
			double avg = (double)runner.query(sqlll,new ScalarHandler());
			score.setAvgScore(avg);
			System.out.println(score.getMaxScore());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return score;
	}

	private Score querycomputerScore() {
		String sql = "select max(computerScore) from student";
		Score score = new Score();
		try {
			double max = (double) runner.query(sql,new ScalarHandler());
			score.setMaxScore(max);
			String sqll = "select min(computerScore) from student";
			double min = (double)runner.query(sqll,new ScalarHandler());
			score.setMinScore(min);
			String sqlll = "select avg(computerScore) from student";
			double avg = (double) runner.query(sqlll,new ScalarHandler());
			score.setAvgScore(avg);
			System.out.println(score.getMaxScore());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return score;
	}

	public Object[] toArray(List obj){
		int len = obj.size();
		Object[] params = new Object[len];
		for(int i = 0; i < obj.size(); i++){
			params[i] = obj.get(i);
		}
		return params;
	}
}
	

