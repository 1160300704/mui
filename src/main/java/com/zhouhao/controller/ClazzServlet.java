package com.zhouhao.controller;

import com.alibaba.fastjson.JSON;
import com.zhouhao.pojo.Clazz;
import com.zhouhao.pojo.PageBean;
import com.zhouhao.service.ClazzService;
import com.zhouhao.service.impl.ClazzServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clazz")
public class ClazzServlet extends BaseServlet {
    private ClazzService clazzService = new ClazzServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cname = req.getParameter("cname");
        String cteacher = req.getParameter("cteacher");
        String remark = req.getParameter("remark");

        Clazz clazz = new Clazz();
        clazz.setCname(cname);
        clazz.setCteacher(cteacher);
        clazz.setRemark(remark);

        Integer add = clazzService.add(clazz);
        resp.getWriter().write(add.toString());
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Clazz> list = clazzService.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("saveStudent.jsp").forward(req, resp);
    }

    protected void getClazzByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = Integer.parseInt(req.getParameter("index"));
        int size = Integer.parseInt(req.getParameter("size"));

        PageBean<Clazz> clazzByPage = clazzService.getClazzByPage(index, size);
        req.setAttribute("list", clazzByPage.getList());
        req.setAttribute("index", clazzByPage.getIndex());
        req.setAttribute("totalPageCount", clazzByPage.getTotalPageCount());
        req.setAttribute("numbers", clazzByPage.getNumbers());
        req.getRequestDispatcher("listClazz.jsp").forward(req, resp);
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer cid = Integer.valueOf(req.getParameter("cid"));
        String cname = req.getParameter("cname");
        String cteacher = req.getParameter("cteacher");
        String remark = req.getParameter("remark");

        Clazz clazz = new Clazz();
        clazz.setCid(cid);
        clazz.setCname(cname);
        clazz.setCteacher(cteacher);
        clazz.setRemark(remark);

        Integer update = clazzService.update(clazz);
        resp.getWriter().write(update.toString());
    }
    protected void clazzStudentCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(JSON.toJSONString(clazzService.clazzStudentCount()));
    }
}
