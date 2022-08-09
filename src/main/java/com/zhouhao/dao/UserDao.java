package com.zhouhao.dao;

import com.zhouhao.pojo.User;

public interface UserDao {
    User getUserByUkeyAndPwd(String ukey);

}
