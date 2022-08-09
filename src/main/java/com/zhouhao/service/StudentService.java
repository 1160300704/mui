package com.zhouhao.service;

import com.zhouhao.pojo.PageBean;
import com.zhouhao.pojo.Student;
import java.util.List;

public interface StudentService {
    void add(Student student);
    PageBean<Student> getStudentByPage(Integer index, Integer size, String sname, String phone);
    List<Student> getStudent(String sname, String phone);
}
