<%--
  Created by IntelliJ IDEA.
  User: 91192
  Date: 2022/6/9
  Time: 17:27
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
    <title>修改班级</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>修改班级</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="">
            <div class="form-group">
                <div class="label">
                    <label>班级名称：</label>
                </div>
                <div class="field">
                    <%
                        String cid = new String(request.getParameter("cid").getBytes("ISO-8859-1"), "UTF-8");
                        String cname = new String(request.getParameter("cname").getBytes("ISO-8859-1"), "UTF-8");
                        String cteacher = new String(request.getParameter("cteacher").getBytes("ISO-8859-1"), "UTF-8");
                        String remark = new String(request.getParameter("remark").getBytes("ISO-8859-1"), "UTF-8");
                    %>
                    <input type="hidden" class="input" id="cid" value="<%=cid%>"  style="width:30%"/>
                    <input type="text" class="input" id="cname" value="<%=cname%>"  style="width:30%"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>班主任：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" id="cteacher" value="<%=cteacher%>" style="width:30%"/>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>备注：</label>
                </div>
                <div class="field">
                    <textarea type="text" class="input" id="remark" style="height:80px;"><%=remark%></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" id="btn" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#btn").click(function () {
            $.post("/mui/clazz?methodName=update", {
                cid: $("#cid").val(),
                cname: $("#cname").val(),
                cteacher: $("#cteacher").val(),
                remark: $("#remark").val()
            }, function (data) {
                data = JSON.parse(data)
                if(data == "1"){
                    alert('修改班级成功')
                    location.href="/mui/clazz?methodName=getClazzByPage&index=1&size=5"
                }
                else
                    alert('修改班级失败')
            })
            return false;
        })
    })
</script>
</html>