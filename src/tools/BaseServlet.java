package tools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		
		if(methodName == null || methodName.trim().isEmpty()){
			throw new RuntimeException("方法名没有传递无法确定你要调用的方法");
		}
		Class clazz = this.getClass();
		Method method = null;
		try{
		method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
		}catch(Exception e){
			throw new RuntimeException("你传入的方法名："+methodName+"不存在");
		}
		
		String result = null;
		try {
			result = (String)method.invoke(this,request,response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		if(result == null || result.trim().isEmpty()){
			return;
		}else{
			if(result.contains(":")){
				int index = result.indexOf(":");
				String prefix = result.substring(0, index);
				String suffix = result.substring(index+1);
				if(prefix.equalsIgnoreCase("f")){
					request.getRequestDispatcher(suffix).forward(request, response);
				}else if(prefix.equalsIgnoreCase("r")){
					response.sendRedirect(request.getContextPath()+suffix);
				}else{
					throw new RuntimeException(""+prefix+"");
				}
			}else{
				request.getRequestDispatcher(result).forward(request, response);
			}
		}
		
		
	}
	
}

