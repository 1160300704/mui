package com.zhouhao.service.impl;

import com.zhouhao.dao.ClazzDao;
import com.zhouhao.dao.impl.ClazzDaoImpl;
import com.zhouhao.pojo.Clazz;
import com.zhouhao.pojo.PageBean;
import com.zhouhao.service.ClazzService;
import java.util.List;

public class ClazzServiceImpl implements ClazzService {
    private ClazzDao clazzDao = new ClazzDaoImpl();

    @Override
    public Integer add(Clazz clazz) {
        return clazzDao.add(clazz);
    }

    @Override
    public List<Clazz> getAll() {
        return clazzDao.getAll();
    }

    @Override
    public PageBean<Clazz> getClazzByPage(int index, int size) {
        PageBean<Clazz> pageBean = new PageBean<>();
        int start = size * (index - 1);
        int total = clazzDao.getTotalCount();
        pageBean.setList(clazzDao.getClazzByPage(start, size));
        pageBean.setIndex(index);
        pageBean.setSize(size);
        pageBean.setTotalCount(total);
        return pageBean;
    }

    @Override
    public Integer update(Clazz clazz) {
        return clazzDao.update(clazz);
    }

    @Override
    public List<Clazz> clazzStudentCount() {
        return clazzDao.clazzStudentCount();
    }
}