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
    
    <title>后台系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta charset="UTF-8">
    <title>学生信息管理</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        h1 {
            height: 59px;
            line-height: 59px;
            border-bottom: 1px solid #ccc;
            box-shadow: 0 0 5px 3px #eee;
            background-color: #eee;
            color: #999;
            text-indent: 1.5em;
        }
        nav {
            background-color: rgb(19,20,21);
            color: #999;
            position: fixed;
            top: 60px;
            left: 0;
            bottom: 0;
            padding-top: 25px;
        }
        nav li {
            list-style: none;
            padding: 15px 50px 15px 25px;
        }
        nav li:hover {
            cursor: pointer;
            background-color: rgba(50,51,52,0.5);
        }
        nav .active {
            background-color: rgba(90,91,92,0.5) !important;
        }
        .content {
            margin-left: 180px;
            padding-left: 2em;
        }
        .content h2 {
            padding: 20px 0;
        }
        input, select {
            display: block;
            border-radius: 5px;
            background-color: white;
            border: 1px solid #ccc;
            margin-bottom: 30px;
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
        }
        .content section {
            display: none;
        }
        .content .active {
            display: block;
        }
        table {
            background-color: #eee;
            margin: 50px;
            border: 1px solid #999;
            border-collapse: collapse;
        }
        th,td {
            border: 1px solid #ccc;
            padding: 10px 25px;
            min-width: 100px;
            text-align: center;
        }
    </style>
  </head>
  
  <body>
    <!-- 顶部导航栏，无需改动 -->
    <h1>学生信息管理</h1>

    <!-- 左侧导航栏，需要在合适的li标签上添加上 class="active" 字段，四个中只能选一个添加 -->
    <nav>
        <ul>
            <li class="active" data-id="add">添加学生</li>
            <li data-id="delete">删除学生</li>
            <li data-id="modify">修改学生信息</li>
            <li data-id="search">查找学生信息</li>
            <li data-id="anlay">统计学生成绩信息</li>
        </ul>
    </nav>

    <div class="content">

        <!-- 右侧显示的内容，需要在核实的section标签上添加上 class="active" 字段，需要和左侧导航栏的内容相吻合；此外只有查询信息部分需要额外的修改 -->

        <!-- 添加学生 -->
        <section id="add" class="active">
            <h2>添加学生</h2>
            <form action="<c:url value='/Operation'/>" method="post">
            	<input type="hidden" name="method" value="add"/>
            	<span>${errors.nmsg }</span><span id="anerror"></span>
                <input id="anid" type="text" placeholder="姓名" name="name" />
                <span>${errors.gmsg }</span><span id="agerror"></span>
				<select id="agid" name="gender">
                    <option value="" disabled="disabled" selected="selected">请选择性别</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
                <span>${errors.amsg }</span><span id="aaerror"></span>
                <input id="aaid" type="text" placeholder="年龄" name="age" />
                <span>${errors.bmsg }</span><span id="aserror"></span>
                <input id="asid" type="text" placeholder="学号" name="stdNumber" />
                <span>${errors.jmsg }</span><span id="amerror"></span>
                <input type="text" placeholder="计算机网络成绩" name="computerScore"/>
                <input type="text" placeholder="数学成绩" name="mathScore"/>
                <input type="text" placeholder="英语成绩" name="englishScore"/>
                <select id="amid" name="major">
                    <option value="" disabled="disabled" selected="selected">请选择所在专业</option>
                    <option value="通信工程">通信工程</option>
                    <option value="电子信息工程">电子信息工程</option>
                    <option value="信息工程">信息工程</option>
                    <option value="广播电视工程">广播电视工程</option>
                </select>
                <button type="submit">确认添加</button><br/>
                <span>${smsg }</span>
            </form>
        </section>


        <!-- 删除学生 -->
        <section id="delete">
            <h2>删除学生</h2>
            <form action="<c:url value='/Operation'/>" method="post">
            	<input type="hidden" name="method" value="delete"/>
            	<span>${nmsg }</span><span id="dserror"></span>
                <input type="text" placeholder="学号" name="stdNumber" id="dsid"/>
                <button type="submit">确认删除</button><span>${suss }</span>
            </form>
        </section>


        <!-- 修改学生信息 -->
        <section id="modify">
            <h2>修改学生信息（依学号修改）</h2>
            <form action="<c:url value='/Operation'/>" method="post">
            	<input type="hidden" name="method" value="update"/>
            	<span id="unerror"></span>
                <input type="text" placeholder="姓名" name="name" value="" id="unid"/>
                <span id="ugerror"></span>
				<select name="gender" id="ugid">
                    <option value="" disabled="disabled" selected="selected">请选择性别</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
                <span id="uaerror"></span>
                <input type="text" placeholder="年龄" name="age" value="" id="uaid"/>
                <span>${unmsg }</span><span id="userror"></span>
                <input type="text" placeholder="学号" name="stdNumber" value="" id="usid"/>
                <span id="umerror"></span>
                <select name="major" id="umid">
                    <option value="" disabled="disabled" selected="selected">请选择所在专业</option>
                    <option value="通信工程">通信工程</option>
                    <option value="电子信息工程">电子信息工程</option>
                    <option value="信息工程">信息工程</option>
                    <option value="广播电视工程">广播电视工程</option>
                </select>
                <span>${succ }</span>
                <button type="submit">确认修改</button>
            </form>
        </section>
        

        <!-- 查询学生信息 -->
        <section id="search">
            <h2>查找学生信息(姓名/学号可二选一)</h2>
            <form action="<c:url value='/Operation'/>" method="post">
            	<input type="hidden" name="method" value="query"/>
                <input type="text" placeholder="姓名" name="name" value="" id="qnid"/>
                <input type="text" placeholder="学号" name="stdNumber" value="" id="qsid"/>
                <button type="submit">确认查找</button><span>${fmsg }</span><span id="qerror"></span>
            </form>
                <table>
                    <thead>
                        <caption>查询结果</caption>
                        <tr>
                            <th>姓名</th>
                            <th>年龄</th>
							<th>性别</th>
                            <th>学号</th>
                            <th>专业</th>
                            <th>计算机网络</th>
                            <th>数学</th>
                            <th>英语</th>
							<th>操作</th>
                        </tr>
                    </tbody><%--
                    	第${pb.currentpage}页/共${pb.totalpage}页
   						<a href="<c:url value='/manager?method=query&page=1'/>">首页</a>
   						<c:if test="${pb.currentpage > 1}">
   						<a href="<c:url value='/manager?method=query&page=${pb.currentpage-1}'/>">上一页</a>
   						</c:if>
  			 			<c:choose>
   						<c:when test="${pb.totalpage < 10 }">
   						<c:set var="begin" value="1"></c:set>
   						<c:set var="end" value="${pb.toatlpage }"></c:set>
   						</c:when>
   						<c:otherwise>
   						<c:set var="begin" value="${pb.currentpage-5}"></c:set>
   						<c:set var="end" value="${pb.currentpage+4}"></c:set>
   						<c:if test="${begin<1}">
   						<c:set var="begin" value="1"></c:set>
   						<c:set var="end" value="10"></c:set>
   						</c:if>
   						<c:if test="${end>pb.totalpage}">
   						<c:set var="begin" value="${pb.totalpage-9 }"></c:set>
   						<c:set var="end" value="${pb.totalpage}"/>
   						</c:if>
   						</c:otherwise>
   						</c:choose>
   						<c:forEach var="i" begin="${begin}" end="${end}">
   						<c:choose>
   						<c:when test="${i eq pb.currentpage }">
   						[${i }]
  						 </c:when>
   						<c:otherwise>
   						<a href="<c:url value='/manager?method=query&page=${i}'/>">[${i}]</a>
   						</c:otherwise>
   						</c:choose>
   						</c:forEach>
   						<c:if test="${pb.currentpage < totalpage}">
   							<a href="<c:url value='/manager?method=query&page=${pb.currentpage+1 }'/>">下一页</a>
   						</c:if>
   							<a href="<c:url value='/manager?method=query&page=${pb.totalpage }'/>">尾页</a>	
                    --%></thead>
                    <tbody>
                    <c:forEach items="${pb }" var="bean">
                        <tr>
                            <td>${bean.name }</td>
                            <td>${bean.age }</td>
							<td>${bean.gender }</td>
                            <td>${bean.stdNumber }</td>
                            <td>${bean.major }</td>
                            <td>${bean.computerScore}</td>
                            <td>${bean.mathScore}</td>
                            <td>${bean.englishScore}</td>
							<td>
								<a style="background:red" href="<c:url value='/Operation?method=delete&stdNumber=${bean.stdNumber }'/>">删除</a>
							</td>
                        </tr>
                     </c:forEach>
                </table>
          
        </section>
        <section id="anlay">
            <h2>查询科目最高分</h2>
            <form action="<c:url value='/Operation'/>" method="post">
                <input type="hidden" name="method" value="queryScore"/>
                <select name="scoreName">
                    <option value="computerScore">计算机网络</option>
                    <option value="mathScore">高数</option>
                    <option value="englishScore">英语</option>
                </select>
                <button type="submit">确认查询</button>
            </form>
            <table>

                <caption>查询结果</caption>
                <tr>
                    <th>课程最高分</th>
                    <th>课程最低分</th>
                    <th>课程平均分</th>
                </tr>
                <tr>
                    <td>${scorebean.maxScore}</td>
                    <td>${scorebean.minScore}</td>
                    <td>${scorebean.avgScore}</td>
                </tr>
            </table>
        </section>
    </div>


    <!-- 以下为浏览器js脚本，无需改动 -->
  	<script>
  	</script>
    <script type="text/javascript">
    <%--创建XMLHTTPRequest对象--%>
    function createXMLHttpRequest(){
  		try{
  			return new XMLHttpRequest();
  		}catch(e){
  			try{
  			return new ActiveXObject("Msxml2.XMLHTTP");
  			}catch(e){
  				try{
  					return new ActiveXObject("Microsoft.XMLHTTP");
  				}catch(e){
  					throw e;
  				}
  			}
  		}
  	}
    <%--对表单数据判断--%>
  	window.onload = function(){
  		function nameCheck(tag,span){
  			tag.onblur = function(){
  				var text = tag.value;
  				if(text == ""){
  					span.innerHTML = "学生姓名不能为空";
  				}else{
  					span.innerHTML = "";
  				}
  			};
  		}
  		function genderCheck(tag,span){
  			tag.onblur = function(){
  				var text = tag.value;
  				if(text == ""){
  					span.innerHTML = "性别未选择";
  				}else{
  					span.innerHTML = "";
  				}
  			};
  		}
  		function ageCheck(tag,span){
  			tag.onblur = function(){
  				var text = tag.value;
  				if(text == ""){
  					span.innerHTML = "年龄未填写";
  				}else{
  					span.innerHTML = "";
  				}
  			};
  		}
  		function stdNumberCheck(tag,span){
  			tag.onblur = function(){
  				var text = tag.value;
  				if(text == ""){
  					span.innerHTML = "学号未填写";
  				}
  				else{
  					var xmlhttp = createXMLHTTPRequest();
  					xmlhttp.open("POST","<c:url value='/ajaxManager'/>",true);
  					xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
  					xmlhttp.send("stdNumber="+text);
  					xmlhttp.onreadystatechange = function(){
  						if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
  							var text = xmlhttp.responseText;
  							if(text == 0){
  								span.innerHTML = "该学号已被注册过";
  							}else{
  								span.innerHTML = "";
  							}
  						}
  					};
  				}
  			}
  		}
  		function majorCheck(tag,span){
  			tag.onblur = function(){
  				var text = tag.value;
  				if(text == ""){
  					span.innerHTML = "没有填写专业";
  				}else{
  					span.innerHTML = "";
  				}
  				};
  			}
  		var antag = document.getElementById("anid");
  		var anspan = document.getElementById("anerror");
  		nameCheck(antag,anspan);
  		
  		var agtag = document.getElementById("agid");
  		var agspan = document.getElementById("agerror");
  		genderCheck(agtag,agspan);
  		
  		var aatag = document.getElementById("aaid");
  		var aaspan = document.getElementById("aaerror");
  		ageCheck(aatag,aaspan);
  		
  		var astag = document.getElementById("asid");
  		var asspan = document.getElementById("aserror");
  		stdNumberCheck(astag,asspan);
  		
  		var amtag = document.getElementById("amid");
  		var amspan = document.getElementById("amerror");
  		majorCheck(amtag,amspan);
  		
  		var dstag = document.getElementById("dsid");
  		var dsspan = document.getElementById("dserror");
  		stdNumberCheck(dstag,dsspan);
  		
  		var untag = document.getElementById("unid");
  		var unspan = document.getElementById("unerror");
  		nameCheck(untag,unspan);
  		
  		var ugtag = document.getElementById("ugid");
  		var ugspan = document.getElementById("ugerror");
  		genderCheck(ugtag,ugspan);
  		
  		var uatag = document.getElementById("uaid");
  		var uaspan = document.getElementById("uaerror");
  		ageCheck(uatag,uaspan);
  		
  		var ustag = document.getElementById("usid");
  		var usspan = document.getElementById("userror");
  		stdNumberCheck(ustag,usspan);
  		
  		var umtag = document.getElementById("umid");
  		var umspan = document.getElementById("umerror");
  		majorCheck(umtag,umspan);
  		
  		var qntag = document.getElementById("qnid");
  		var qstag = document.getElementById("qsid");
  		var qspan = document.getElementById("qerror");
  		qntag.onblur = function(){
  			var ntext = qntag.value;
  			var stext = qstag.value;
  			if(ntext == "" && stext == ""){
  				qspan.innerHTML = "姓名与学号至少填写一个";
  			}else{
  				qspan.innerHTML = "";
  			}
  		};
  		
  		qstag.onblur = function(){
  			var ntext = qntag.value;
  			var stext = qstag.value;
  			if(ntext == "" && stext == ""){
  				qspan.innerHTML == "姓名与学号至少填写一个";
  			}else{
  				qspan.innerHTML = "";
  			}
  		};
  	}
    	var lis = document.getElementsByTagName("li");
   	    var content =  document.getElementsByClassName("content")[0];
        for(var i=lis.length; i>0; i--){
        lis[i-1].onclick = function(){
            document.getElementsByTagName("nav")[0].getElementsByClassName("active")[0].className = "";
            this.className = "active";
            content.getElementsByClassName("active")[0].className = "";
            document.getElementById(this.getAttribute("data-id")).className = "active";
        }
    }
    </script>
  </body>
</html>
