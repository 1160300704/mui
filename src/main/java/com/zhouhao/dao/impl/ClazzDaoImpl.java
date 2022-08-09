package com.zhouhao.dao.impl;

import com.zhouhao.dao.ClazzDao;
import com.zhouhao.pojo.Clazz;
import com.zhouhao.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClazzDaoImpl implements ClazzDao {
    @Override
    public List<Clazz> getClazzByPage(int start, int size) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM `clazz` LIMIT ?, ?";
        List<Clazz> list = new ArrayList<>();

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, start);
            preparedStatement.setObject(2, size);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Clazz clazz = new Clazz();
                clazz.setCid(resultSet.getInt(1));
                clazz.setCname(resultSet.getString(2));
                clazz.setCteacher(resultSet.getString(3));
                clazz.setRemark(resultSet.getString(4));
                list.add(clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, resultSet);
        }

        return list;
    }

    @Override
    public Integer getTotalCount() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT count(*) FROM `clazz`";
        Integer result = null;

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, resultSet);
        }

        return result;
    }

    @Override
    public Integer add(Clazz clazz) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Integer result = null;
        String sql = "INSERT INTO clazz VALUES (null , ?, ?, ?)";

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setObject(1, clazz.getCname());
            preparedStatement.setObject(2, clazz.getCteacher());
            preparedStatement.setObject(3, clazz.getRemark());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, null);
        }

        return result;
    }

    @Override
    public List<Clazz> getAll() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM `clazz`";
        List<Clazz> list = new ArrayList<>();

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Clazz clazz = new Clazz();
                clazz.setCid(resultSet.getInt(1));
                clazz.setCname(resultSet.getString(2));
                clazz.setCteacher(resultSet.getString(3));
                clazz.setRemark(resultSet.getString(4));
                list.add(clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, resultSet);
        }

        return list;
    }

    @Override
    public Integer update(Clazz clazz) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Integer result = null;
        String sql = "UPDATE clazz SET cname = ?, cteacher = ?, remark = ? WHERE cid = ?";

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, clazz.getCname());
            preparedStatement.setObject(2, clazz.getCteacher());
            preparedStatement.setObject(3, clazz.getRemark());
            preparedStatement.setObject(4, clazz.getCid());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, null);
        }

        return result;
    }

    @Override
    public List<Clazz> clazzStudentCount() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT cname, COUNT(sid) FROM (SELECT c.cid, s.sid, c.cname FROM clazz c LEFT JOIN student s on c.cid = s.cid) as t GROUP BY cid";
        List<Clazz> list = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Clazz clazz = new Clazz();
                clazz.setCname(resultSet.getString(1));
                clazz.setCount(resultSet.getInt(2));
                list.add(clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, resultSet);
        }
        return list;
    }
}