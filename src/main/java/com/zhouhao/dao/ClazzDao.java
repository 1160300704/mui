package com.zhouhao.dao;

import com.zhouhao.pojo.Clazz;
import java.util.List;

public interface ClazzDao {
    List<Clazz> getClazzByPage(int start, int size);
    Integer getTotalCount();
    Integer add(Clazz clazz);
    List<Clazz> getAll();
    Integer update(Clazz clazz);
    List<Clazz> clazzStudentCount();
}
