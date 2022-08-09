package com.zhouhao.service.impl;

import com.zhouhao.dao.ClazzDao;
import com.zhouhao.dao.UserDao;
import com.zhouhao.dao.impl.ClazzDaoImpl;
import com.zhouhao.dao.impl.UserDaoImpl;
import com.zhouhao.dto.Userdto;
import com.zhouhao.pojo.User;
import com.zhouhao.service.UserService;
import javax.servlet.http.HttpServletRequest;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public Userdto login(String ukey, String pwd, String code, HttpServletRequest req) {
        Userdto userdto = new Userdto();

        String  randStr = (String) req.getSession().getAttribute("randStr");
        if(!code.equalsIgnoreCase(randStr)){
            userdto.setCode(-2);
            userdto.setMessage("验证码错误");
            return userdto;
        }else{
            User user = userDao.getUserByUkeyAndPwd(ukey);
            if(user == null){
                userdto.setMessage("用户名错误");
                userdto.setCode(-1);
                return userdto;
            }else{
                if(!user.getPwd().equals(pwd)){
                    userdto.setMessage("密码错误");
                    userdto.setCode(0);
                    return userdto;
                }else {
                    userdto.setCode(1);
                    userdto.setMessage("登录成功");
                    userdto.setUser(user);
                    return userdto;
                }
            }
        }
    }
}
