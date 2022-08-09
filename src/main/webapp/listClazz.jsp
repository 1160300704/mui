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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>

</head>
<body>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 班级列表</strong> <a href=""
                                                                               style="float:right; display:none;">添加字段</a>
        </div>
        <table class="table table-hover text-center">
            <tr>
                <th width="100" style="text-align:left; padding-left:20px;">ID</th>
                <th width="10%">班级名称</th>
                <th>班主任</th>
                <th>备注</th>
            </tr>
            <c:forEach items="${list}" var="i">
                <tr>
                    <td style="text-align:left; padding-left:20px;">${i.getCid()}</td>
                    <td>${i.getCname()}</td>
                    <td>${i.getCteacher()}</td>
                    <td>${i.getRemark()}</td>
                    <td>
                        <div class="button-group">
                            <a class="button border-main"
                               href="updateClazz.jsp?cid=${i.getCid()}&cname=${i.getCname()}&cteacher=${i.getCteacher()}&remark=${i.getRemark()}">
                                <span class="icon-edit"></span> 修改</a>
                            <a class="button border-red" href="javascript:void(0)" onclick="return del(1,1,1)">
                                <span class="icon-trash-o"></span> 删除</a></div>
                    </td>
                <tr>
            </c:forEach>
                <td colspan="8">
                    <div class="pagelist">
                        <a href="javascript:;" id="pre">上一页</a>
                        <c:forEach items="${numbers}" var="i" varStatus="status">
                            <c:if test="${status.index+1 != index}">
                                <a href="/mui/clazz?methodName=getClazzByPage&index=${i}&size=5" class="a">${i}</a>
                            </c:if>
                            <c:if test="${status.index+1 == index}">
                                <span class="current">${i}</span>
                            </c:if>
                        </c:forEach>
                        <a href="javascript:;" id="next">下一页</a>
                        <a href="/mui/clazz?methodName=getClazzByPage&index=${totalPageCount}&size=5">尾页</a>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">
    $(function () {
        var index =
        ${index}
        var totalPageCount = ${totalPageCount}
            $("#next").click(function () {
                if (index >= totalPageCount)
                    alert("已经是最后一页了")
                else
                    location.href = '/mui/clazz?methodName=getClazzByPage&index=${index+1}&size=5'
            })
        $("#pre").click(function () {
            if (index <= 1)
                alert("已经是第一页了")
            else
                location.href = '/mui/clazz?methodName=getClazzByPage&index=${index-1}&size=5'
        })
    })

    //搜索
    function changesearch() {

    }

    //单个删除
    function del(id, mid, iscid) {
        if (confirm("您确定要删除吗?")) {

        }
    }

    //全选
    $("#checkall").click(function () {
        $("input[name='id[]']").each(function () {
            if (this.checked) {
                this.checked = false;
            } else {
                this.checked = true;
            }
        });
    })

    //批量删除
    function DelSelect() {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {
            var t = confirm("您确认要删除选中的内容吗？");
            if (t == false) return false;
            $("#listform").submit();
        } else {
            alert("请选择您要删除的内容!");
            return false;
        }
    }

    //批量排序
    function sorts() {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");
            return false;
        }
    }


    //批量首页显示
    function changeishome(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量推荐
    function changeisvouch(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {


            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量置顶
    function changeistop(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");

            return false;
        }
    }


    //批量移动
    function changecate(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量复制
    function changecopy(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {
            var i = 0;
            $("input[name='id[]']").each(function () {
                if (this.checked == true) {
                    i++;
                }
            });
            if (i > 1) {
                alert("只能选择一条信息!");
                $(o).find("option:first").prop("selected", "selected");
            } else {

                $("#listform").submit();
            }
        } else {
            alert("请选择要复制的内容!");
            $(o).find("option:first").prop("selected", "selected");
            return false;
        }
    }
</script>
</body>
</html>