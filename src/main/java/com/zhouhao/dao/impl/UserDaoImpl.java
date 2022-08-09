package com.zhouhao.dao.impl;

import com.zhouhao.dao.UserDao;
import com.zhouhao.pojo.User;
import com.zhouhao.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByUkeyAndPwd(String ukey) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM `user` WHERE user.ukey = ?";
        User user = null;

        try{
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, ukey);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User();
                user.setUid(resultSet.getInt(1));
                user.setUkey(resultSet.getString(2));
                user.setPwd(resultSet.getString(3));
                user.setRealname(resultSet.getString(4));
                user.setType(resultSet.getInt(5));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, resultSet);
        }

        return user;
    }
}
