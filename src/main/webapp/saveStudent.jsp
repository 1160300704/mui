<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: 91192
Date: 2022/6/9
Time: 14:23
To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>添加学生</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>添加学生</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="/mui/student?methodName=add">
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="sname" value=""  style="width:30%"/>
          <div class="tips"></div>
        </div>
      </div>d
	   
       <div class="form-group">
        <div class="label">
          <label>性别：</label>
        </div>
        <div class="field" style="padding-top:8px;">
          男:<input type="radio"  name="sex" value="1" />
		  女: <input type="radio"  name="sex" value="0" />
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>爱好：</label>
        </div>
        <div class="field" style="padding-top:8px;">
            抽烟 <input   type="checkbox" name="hobby" value="抽烟"/>
            喝酒 <input   type="checkbox" name="hobby" value="喝酒"/>
            烫头 <input   type="checkbox" name="hobby" value="烫头"/>
        </div>
      </div>
	   
	  
	   <div class="form-group">
        <div class="label">
          <label>出生年月：</label>
        </div>
        <div class="field">
          <input type="date" class="input" name="birthdate" value=""  style="width:30%"/>
            <div class="tips"></div>
        </div>
      </div>
	  
	   <div class="form-group">
        <div class="label">
          <label>手机号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="phone" value="" style="width:30%"/>
        </div>
      </div>
	  
	   <div class="form-group">
        <div class="label">
          <label>所在班级：</label>
        </div>
        <div class="field">
        
		  <select class="input" style="width:30%">
          <option>--请选择--</option>
          <c:forEach items="${list}" var="i">
              <option name="cid" value="${i.getCid()}">${i.getCname()}</option>
          </c:forEach>
		  </select>
        </div>
      </div>
	  
	 
	  <div class="form-group">
        <div class="label">
          <label>描述：</label>
        </div>
        <div class="field">
          <textarea type="text" class="input" name="remark" style="height:80px;"></textarea>
        </div>
      </div>
    
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
    <script>
        $(function () {
            $("button").click(function () {
                alert("学生信息添加成功")
            })
        })
    </script>
</body>
</html>