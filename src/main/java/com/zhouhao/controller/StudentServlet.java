package com.zhouhao.controller;

import com.zhouhao.pojo.PageBean;
import com.zhouhao.pojo.Student;
import com.zhouhao.service.StudentService;
import com.zhouhao.service.impl.StudentServiceImpl;
import com.zhouhao.utils.ExcelOperate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends BaseServlet {
    StudentService studentService = new StudentServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        String sname = new String(req.getParameter("sname").getBytes("iso-8859-1"), "utf-8");
        String sex = req.getParameter("sex");
        String[] hobbies = req.getParameterValues("hobby");
        String hobby = "";
        for (int i = 0; i < hobbies.length; i++) {
            hobbies[i] = hobbies[i] == null ? null : new String(hobbies[i].getBytes("iso-8859-1"), "utf-8");
            if (i != hobbies.length - 1) {
                hobby = hobby + hobbies[i] + ",";
            } else {
                hobby = hobby + hobbies[i];
            }
        }
        String birthdate = req.getParameter("birthdate");
        String phone = req.getParameter("phone");
        String remark = new String(req.getParameter("remark").getBytes("iso-8859-1"), "utf-8");

        Student student = new Student();
        student.setSname(sname);
        student.setSex(sex);
        student.setHobby(hobby);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        student.setBirthdate(simpleDateFormat.parse(birthdate));
        student.setPhone(phone);
        student.setReamrk(remark);

        studentService.add(student);
        resp.sendRedirect("/mui/saveStudent.jsp");
    }

    protected void getStudentByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        Integer index = Integer.valueOf(req.getParameter("index"));
        Integer size = Integer.valueOf(req.getParameter("size"));
        String sname = req.getParameter("sname");
        if(sname != null){
            sname = new String(sname.getBytes("iso-8859-1"), "utf-8");
        }
        String phone = req.getParameter("phone");
        if(phone != null){
            phone = new String(phone.getBytes("iso-8859-1"), "utf-8");
        }

        PageBean<Student> studentByPage = studentService.getStudentByPage(index, size, sname, phone);

        req.setAttribute("list", studentByPage.getList());
        req.setAttribute("numbers", studentByPage.getNumbers());
        req.setAttribute("index", studentByPage.getIndex());
        req.setAttribute("totalPage", studentByPage.getTotalPageCount());
        req.getRequestDispatcher("/listStudent.jsp").forward(req, resp);
    }

    protected void exportStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        resp.setContentType("application/vnd.ms-excel;charset=utf-8");
        resp.setHeader("Content-Disposition", "attachment; filename=com.zhouhao.student.xls" );
        String sname = req.getParameter("sname");
        if(sname != null){
            sname = new String(sname.getBytes("iso-8859-1"), "utf-8");
        }
        String phone = req.getParameter("phone");
        if(phone != null){
            phone = new String(phone.getBytes("iso-8859-1"), "utf-8");
        }

        List<Student> list = studentService.getStudent(sname, phone);
        ExcelOperate.createExcel(list, resp.getOutputStream());
    }
}