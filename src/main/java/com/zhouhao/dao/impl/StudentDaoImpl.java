package com.zhouhao.dao.impl;

import com.zhouhao.dao.StudentDao;
import com.zhouhao.pojo.Clazz;
import com.zhouhao.pojo.Student;
import com.zhouhao.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Integer add(Student student) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Integer result = null;
        String sql = "INSERT INTO student VALUES (null , ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setObject(1, student.getSname());
            preparedStatement.setObject(2, student.getSex());
            preparedStatement.setObject(3, student.getHobby());
            preparedStatement.setObject(4, student.getBirthdate());
            preparedStatement.setObject(5, student.getPhone());
            preparedStatement.setObject(6, student.getReamrk());
            preparedStatement.setObject(7, student.getCid());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, null);
        }

        return result;
    }

    @Override
    public List<Student> searchStudentByPage(Integer start, Integer size, String sname, String phone) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT sid, sname, sex, hobby, birthdate, phone, reamrk, c.cid, c.cname FROM student s, clazz c WHERE s.cid = c.cid AND IFNULL(sname, '') LIKE ? AND IFNULL(phone, '') LIKE ? LIMIT ?, ?";
        List<Student> list = new ArrayList<>();

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, "%"+sname+"%");
            preparedStatement.setObject(2, "%"+phone+"%");
            preparedStatement.setObject(3, start);
            preparedStatement.setObject(4, size);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setSid(resultSet.getInt(1));
                student.setSname(resultSet.getString(2));
                student.setSex(resultSet.getString(3));
                student.setHobby(resultSet.getString(4));
                student.setBirthdate(resultSet.getDate(5));
                student.setPhone(resultSet.getString(6));
                student.setReamrk(resultSet.getString(7));
                student.setCid(resultSet.getInt(8));
                student.setCname(resultSet.getString(9));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, resultSet);
        }

        return list;
    }

    @Override
    public List<Student> getStudent(String sname, String phone) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT sid, sname, sex, hobby, birthdate, phone, reamrk, c.cid, c.cname FROM student s, clazz c WHERE s.cid = c.cid AND IFNULL(sname, '') LIKE ? AND IFNULL(phone, '') LIKE ?";
        List<Student> list = new ArrayList<>();

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, "%"+sname+"%");
            preparedStatement.setObject(2, "%"+phone+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setSid(resultSet.getInt(1));
                student.setSname(resultSet.getString(2));
                student.setSex(resultSet.getString(3));
                student.setHobby(resultSet.getString(4));
                student.setBirthdate(resultSet.getDate(5));
                student.setPhone(resultSet.getString(6));
                student.setReamrk(resultSet.getString(7));
                student.setCid(resultSet.getInt(8));
                student.setCname(resultSet.getString(9));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn, preparedStatement, resultSet);
        }

        return list;
    }

    @Override
    public Integer getTotalCount(String sname, String phone) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT COUNT(*) FROM student s, clazz c WHERE s.cid = c.cid AND IFNULL(sname, '') LIKE ? AND IFNULL(phone, '') LIKE ?";
        Integer result = null;

        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, "%"+sname+"%");
            preparedStatement.setObject(2, "%"+phone+"%");
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
}
