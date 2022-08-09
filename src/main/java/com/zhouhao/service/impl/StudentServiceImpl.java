package com.zhouhao.service.impl;

import com.zhouhao.dao.StudentDao;
import com.zhouhao.dao.impl.StudentDaoImpl;
import com.zhouhao.pojo.PageBean;
import com.zhouhao.pojo.Student;
import com.zhouhao.service.StudentService;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public void add(Student student) {
        Integer add = studentDao.add(student);
        if (add != 1) {
            throw new RuntimeException("学生信息添加失败");
        }
    }

    @Override
    public PageBean<Student> getStudentByPage(Integer index, Integer size, String sname, String phone) {
        int start = size * ( index - 1 );
        if(sname == null || sname.equalsIgnoreCase("null")){
            sname = "";
        }
        if(phone == null || phone.equalsIgnoreCase("null")){
            phone = "";
        }
        int total = studentDao.getTotalCount(sname, phone);
        List<Student> studentByPage = studentDao.searchStudentByPage(start, size, sname, phone);

        PageBean pageBean = new PageBean();
        pageBean.setSize(size);
        pageBean.setList(studentByPage);
        pageBean.setIndex(index);
        pageBean.setTotalCount(total);

        return pageBean;
    }

    @Override
    public List<Student> getStudent(String sname, String phone) {
        if(sname == null || sname.equalsIgnoreCase("null")){
            sname = "";
        }
        if(phone == null || phone.equalsIgnoreCase("null")){
            phone = "";
        }
        return studentDao.getStudent(sname, phone);
    }
}
