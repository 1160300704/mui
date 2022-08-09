package com.zhouhao.service;

import com.zhouhao.pojo.Clazz;
import com.zhouhao.pojo.PageBean;
import java.util.List;

public interface ClazzService {
    Integer add(Clazz clazz);
    List<Clazz> getAll();
    PageBean<Clazz> getClazzByPage(int index, int size);
    Integer update(Clazz clazz);
    List<Clazz> clazzStudentCount();
}
