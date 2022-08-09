<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: 91192
Date: 2022/6/9
Time: 21:49
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
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
<%
  String sname = request.getParameter("sname");
  if(sname != null){
    sname = new String(sname.getBytes("iso-8859-1"), "utf-8");
  }
  String phone = request.getParameter("phone");
  if(phone != null){
    phone = new String(phone.getBytes("iso-8859-1"), "utf-8");
  }
%>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 学生列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li>
		  姓名:
          <input id="sname" type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
		  手机号:
          <input id="phone" type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a>
		  <a href="/mui/student?methodName=exportStudent&sname=<%=sname%>&phone=<%=phone%>" class="button border-main icon-signal"> 导出</a>
		 </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="100" style="text-align:left; padding-left:20px;">ID</th>
        <th width="10%">姓名</th>
        <th>手机号</th>
        <th>性别</th>
		<th>所在班级</th>
        <th>出生年月</th>
        <th width="310">操作</th>
      </tr>
      <c:forEach items="${list}" var="i">
        <tr>
          <td style="text-align:left; padding-left:20px;">${i.getSid()}</td>
          <td>${i.getSname()}</td>
          <td>${i.getPhone()}</td>
          <c:if test="${i.getSex() == 1}">
            <td>男</td>
          </c:if>
          <c:if test="${i.getSex() == 0}">
            <td>女</td>
          </c:if>
          <c:if test="${i .getSex() == null }">
            <td></td>
          </c:if>
          <td>${i.getCname()}</td>
          <td>${i.getBirthdate()}</td>
          <td><div class="button-group"> <a class="button border-main" href="add.html"><span class="icon-edit"></span> 修改</a> <a class="button border-red" href="javascript:void(0)" onclick="return del(1,1,1)"><span class="icon-trash-o"></span> 删除</a> </div></td>
        </tr>
      </c:forEach>
      <tr>
        <td colspan="8">
          <div class="pagelist">
            <c:if test="${index != 1}">
              <a href="/mui/student?methodName=getStudentByPage&index=${index-1}&size=5&sname=<%=sname%>&phone=<%=phone%>">上一页</a>
            </c:if>
            <c:forEach items="${numbers}" var="i">
              <c:if test="${i == index}">
                <span class="current">${i}</span>
              </c:if>
              <c:if test="${i != index}">
                <a href="/mui/student?methodName=getStudentByPage&index=${i}&size=5&sname=<%=sname%>&phone=<%=phone%>">${i}</a>
              </c:if>
            </c:forEach>
            <c:if test="${index != totalPage}">
              <a href="/mui/student?methodName=getStudentByPage&index=${index+1}&size=5&sname=<%=sname%>&phone=<%=phone%>">下一页</a>
            </c:if>
            <a href="/mui/student?methodName=getStudentByPage&index=${totalPage}&size=5&sname=<%=sname%>&phone=<%=phone%>">尾页</a>
          </div>
        </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

//搜索
function changesearch(){
  var sname = $('#sname').val()
  var phone = $('#phone').val()
  location.href = "/mui/student?methodName=getStudentByPage&index=1&size=5&sname="+ sname +"&phone=" +phone;
}

//单个删除
function del(id,mid,iscid){
	if(confirm("您确定要删除吗?")){
		
	}
}

//全选
$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

//批量删除
function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false;		
		$("#listform").submit();		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}

//批量排序
function sorts(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){	
		
		$("#listform").submit();		
	}
	else{
		alert("请选择要操作的内容!");
		return false;
	}
}


//批量首页显示
function changeishome(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");		
	
		return false;
	}
}

//批量推荐
function changeisvouch(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");	
		
		return false;
	}
}

//批量置顶
function changeistop(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){		
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");		
	
		return false;
	}
}


//批量移动
function changecate(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){		
		
		$("#listform").submit();		
	}
	else{
		alert("请选择要操作的内容!");
		
		return false;
	}
}

//批量复制
function changecopy(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){	
		var i = 0;
	    $("input[name='id[]']").each(function(){
	  		if (this.checked==true) {
				i++;
			}		
	    });
		if(i>1){ 
	    	alert("只能选择一条信息!");
			$(o).find("option:first").prop("selected","selected");
		}else{
		
			$("#listform").submit();		
		}	
	}
	else{
		alert("请选择要复制的内容!");
		$(o).find("option:first").prop("selected","selected");
		return false;
	}
}

</script>
</body>
</html>