package com.zhouhao.dao;

import com.zhouhao.pojo.Student;
import java.util.List;

public interface StudentDao {
    Integer add(Student student);

    List<Student> searchStudentByPage(Integer start, Integer size, String sname, String phone);

    List<Student> getStudent(String sname, String phone);

    Integer getTotalCount(String sname, String phone);
}