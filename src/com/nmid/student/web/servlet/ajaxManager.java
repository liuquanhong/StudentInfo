package com.nmid.student.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nmid.student.domain.Student;
import com.nmid.student.service.studentService;

public class ajaxManager extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String number = request.getParameter("stdNumber");
		System.out.println(number);
		studentService service = new studentService();
		Student student = service.ajaxfindByNumber(number);
		if(student != null){
			response.getWriter().print(0);
		}
		
	}

}
