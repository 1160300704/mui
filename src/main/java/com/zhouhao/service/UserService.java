package com.zhouhao.service;

import com.zhouhao.dto.Userdto;
import com.zhouhao.pojo.Clazz;
import javax.servlet.http.HttpServletRequest;

public interface UserService {
    Userdto login(String ukey, String pwd, String code, HttpServletRequest req);
}
