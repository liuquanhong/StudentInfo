package com.nmid.student.web.servlet;

import com.nmid.student.domain.Score;
import com.nmid.student.domain.Student;
import com.nmid.student.service.studentService;
import org.apache.commons.beanutils.BeanUtils;
import tools.BaseServlet;
import tools.randomUUIDFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operation extends BaseServlet{

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		studentService service = new studentService();
		Map<String,String> errormap = new HashMap<String,String>();
		String name = request.getParameter("name");
		if(name == null || name.trim().isEmpty()){
			errormap.put("nmsg", "姓名不能为空");
		}
		String sex = request.getParameter("gender");
		if(sex == null){
			errormap.put("gmsg", "性别不能为空");
		}
		String age = request.getParameter("age");
		if(age == null || age.trim().isEmpty()){
			errormap.put("amsg", "年龄不能为空");
		}
		String number = request.getParameter("stdNumber");
		if(number.trim().isEmpty() || number == null){
			errormap.put("bmsg", "学号不能为空");
		}else if(number.length() != 10){
			errormap.put("bmsg", "学号程度只能为10");
		}else{
			if(service.ajaxfindByNumber(number) != null){
				errormap.put("bmsg", "学号重复");
			}
		}
		String major = request.getParameter("major");
		if(major == null){
			errormap.put("jmsg","专业为空");
		}
			Student student = new Student();
			BeanUtils.populate(student, request.getParameterMap());
			student.setId(randomUUIDFactory.toUUID());

		  if(errormap != null && errormap.size() != 0){
			request.setAttribute("errors",errormap);
			return "f:/jsps/houtai.jsp";
		}
			service.add(student);
			request.setAttribute("smsg", "添加成功");
			return "f:/jsps/houtai.jsp";

	}
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		String number = request.getParameter("stdNumber");
		if(number == null || number.trim().isEmpty()){
			request.setAttribute("nmsg","学号不能为空");
			return "f:/jsps/houtai.jsp";
		}
			studentService service = new studentService();
			try{
			service.delete(number);
			}catch(Exception e){
				request.setAttribute("nmsg", "删除失败");
				return "f:/jsps/houtai.jsp";
			}
			request.setAttribute("suss", "删除成功");
			return "f:/jsps/houtai.jsp";
	}
	public String update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
			String number = request.getParameter("stdNumber");
			if(number == null || number.trim().isEmpty()){
				request.setAttribute("unmsg","学号不能为空");
				return "f:/jsps/houtai.jsp";
			}else{
				try{
				Integer.parseInt(number);
				}catch(NumberFormatException e){
					request.setAttribute("unmsg", "学号必须全是数字");
					return "f:/jsps/houtai.jsp";
				}
				if(number.length() != 10){
					request.setAttribute("unmsg", "学号程度必须是10");
					return "f:/jsps/houtai.jsp";
				}
				Student form = new Student();
				BeanUtils.populate(form, request.getParameterMap());
				studentService service = new studentService();
				try{
				 service.update(form.getStdNumber(),form);
				}catch(Exception e){
				request.setAttribute("unmsg", e.getMessage());
				return "f:/jsps/houtai.jsp";
				}
				request.setAttribute("succ","更新学生信息成功");
				return "f:/jsps/houtai.jsp";
				
			}
	}
	/*public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		String name = request.getParameter("name");
		String number = request.getParameter("stdNumber");
		int recordpage = 10;
		int currentpage = getcurrentPage(request);
		studentService service = new studentService();
		pageBean<Student> pagebean;
		if((name == null || name.trim().isEmpty()) && (number == null || number.trim().isEmpty())){
			request.setAttribute("fmsg", "ѧ�ź���������һ��");
			return "f:/jsps/houtai.jsp";
		//}else{
			try{
				pagebean = service.query(name, number,recordpage,currentpage);
			}catch(Exception e){
				request.setAttribute("fmsg","��ѯ������Ӧ��Ϣ��ѧ��");
				return "f:/jsps/houtai.jsp";
			}
			request.setAttribute("page", pagebean);
			return "f:/jsps/houtai.jsp";	
			//}
	}*/
	public String query(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException{
		String name = request.getParameter("name");
		String number = request.getParameter("stdNumber");
		studentService service = new studentService();
		List<Student> list;
		try{
			list = service.query(name,number);
			
		}catch(Exception e){
			request.setAttribute("fmsg", "查询失败");
			return "f:/jsps/houtai.jsp";
		}
		request.setAttribute("pb", list);
		return "f:/jsps/houtai.jsp";
	}
	public int getcurrentPage(HttpServletRequest request){
		if(request.getParameter("page") == null || request.getParameter("page").trim().isEmpty())
			return 1;
		else
		return Integer.parseInt(request.getParameter("page"));
	}

	public String queryScore(HttpServletRequest request,HttpServletResponse response){
		String scoreName = request.getParameter("scoreName");
		studentService service = new studentService();
		Score score = service.queryScore(scoreName);
		request.setAttribute("scorebean",score);
		return "f:/jsps/houtai.jsp";
	}
}
