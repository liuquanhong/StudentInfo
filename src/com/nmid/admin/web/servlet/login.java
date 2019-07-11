package com.nmid.admin.web.servlet;

import com.nmid.admin.domain.admin;
import com.nmid.admin.service.adminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class login extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String,String> errors = new HashMap<String,String>();
		String name = request.getParameter("uuid");
		if(name.trim().isEmpty() || name == null){
			errors.put("umsg", "姓名不能为空");
			
		}
		String password = request.getParameter("password");
		if(password.trim().isEmpty() || password == null){
			errors.put("pmsg", "密码不能为空");
			
		}
		if(errors.size() != 0 && errors != null){
			request.setAttribute("error", errors);
			request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
			}
		if(errors.size() == 0 && errors != null){
				adminService service = new adminService();
				admin adminer = service.login(name);
				if(adminer != null){
					if(!adminer.getPassword().equals(password)){
					errors.put("pmsg", "密码错误");
					request.setAttribute("error", errors);
					request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
					}else{
						request.getRequestDispatcher("/jsps/houtai.jsp").forward(request, response);
					}
			}else{
				request.setAttribute("emsg", "");
				request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
			}
		}
	}
}
	
