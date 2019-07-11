<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教室登录系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="UTF-8">
    <title>系统登录</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        form {
            width: 400px;
            height: 240px;
            border: 1px solid #ccc;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -120px;
            margin-left: -200px;
            text-align: center;
        }
        h2 {
            position: absolute;
            width: 120px;
            height: 40px;
            line-height: 40px;
            top: -20px;
            left: 30px;
            background-color: white;
        }
        input, select {
            display: block;
            border-radius: 5px;
            background-color: white;
            border: 1px solid #ccc;
            margin: 30px 25px;
            height: 35px;
            font-size: 18px;
            text-indent: 1em;
            width: 350px;
        }
        button {
            display: block;
            width: 150px;
            text-align: center;
            height: 40px;
            line-height: 40px;
            color: white;
            background-color: rgb(54,133,250);
            font-size: 18px;
            border: 0;
            border-radius: 5px;
            margin: 0 auto;
        }
    </style>
  </head>
  
  <body>
  <h1>${emsg }</h1>
    <form action="<c:url value="/login"/>" method="post">
        <h2>欢迎登录</h2>
        <input type="text" name="uuid" placeholder="用户名" id="uid"/><span id="suid"></span>
        <input type="password" name="password" placeholder="用户密码" id="pid" /><span id="spid"></span>
        <button type="submit">确认登录</button>
    </form>
  </body>
  <script type="text/javascript">
  	window.onload=function(){
  		function passwordCheck(tag,span){
  			tag.onblur = function(){
  				var text = tag.value;
  				if(text == ""){
  					span.innerHTML = "密码不能为空";
  				}else{
  					span.innerHTML = "";
  				}
  			};
  		}
        function nameCheck(tag,span){
  		    tag.onblur = function () {
              var text = tag.value;
              if(text == "")
                  span.innerHTML = "用户名不能为空";
              else
                  span.innerHTML = "";
            };
        }
  		var utag = document.getElementById("uid");
  		var uspan = document.getElementById("suid");
  		nameCheck(utag,uspan);
  		
  		var ptag = document.getElementById("pid");
  		var pspan = document.getElementById("spid");
  		passwordCheck(ptag,pspan);
  	}
  </script>
</html>
