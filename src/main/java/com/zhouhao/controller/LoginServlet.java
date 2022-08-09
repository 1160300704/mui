package com.zhouhao.controller;

import com.alibaba.fastjson.JSON;
import com.zhouhao.dto.Userdto;
import com.zhouhao.service.UserService;
import com.zhouhao.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String ukey = req.getParameter("ukey");
        String pwd = req.getParameter("pwd");
        String code = req.getParameter("code");

        Userdto userdto = userService.login(ukey, pwd, code, req);
        resp.getWriter().write(JSON.toJSONString(userdto));

        req.getSession().setAttribute("user", userdto.getUser());
    }
}
