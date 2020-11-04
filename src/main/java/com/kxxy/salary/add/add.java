package com.kxxy.salary.add;

import org.springframework.data.relational.core.sql.SQL;

import java.sql.*;

public class add {

    public static final String URL = "jdbc:mysql://localhost:3306/forSalary?useSSL=false&serverTimezone=UTC&allowMutiQueries=true&characterEncoding=UTF-8";
    public static final String USER = "root";
    public static final String PASSWORD = "Wxy5211314";

    public Connection DAO()
    {
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public static String addWorker(String name, String sex, String phone)
    {
        add dao = new add();
        String msg = new String("fail");
        Connection conn = dao.DAO();
        String sql0 = "insert into worker (name, sex, phone) values (?, ?, ?)";
        String sql1 = "select * from worker where name = (?) and sex = (?)";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql1);
            ptst.setString(1, name);
            ptst.setString(2, sex);
            ResultSet rs = ptst.executeQuery();
            if(rs.next())
            {
                msg = "已经添加";
                return msg;
            }
            ptst = conn.prepareStatement(sql0);
            ptst.setString(1, name);
            ptst.setString(2, sex);
            ptst.setString(3, phone);
            ptst.execute();
            msg = "success";
            return msg;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return msg;
    }

    public static String addWorkTime(String name, String sex, String month, float time)
    {
        String status = new String("fail");
        float sumTime = 0;
        add dao = new add();
        Connection conn = dao.DAO();
        String sql0 = "select time from workTime where name = (?) and sex = (?) and month = (?)";
        String sql1 = "insert into workTime (name, sex, time, month) values (?, ?, ?, ?)";
        String sql2 = "update workTime set time = ? where name = (?) and sex = (?) and month = (?)";
        try{
            PreparedStatement ptst = conn.prepareStatement(sql0);
            ptst.setString(1, name);
            ptst.setString(2, sex);
            ptst.setString(3, month);
            ResultSet rs = ptst.executeQuery();
            if(!rs.next()) {
                ptst = conn.prepareStatement(sql1);
                ptst.setString(1, name);
                ptst.setString(2, sex);
                ptst.setFloat(3, 0);
                ptst.setString(4, month);
                ptst.execute();
            }
            else
            {
                sumTime = rs.getFloat("time");
            }
            sumTime += time;
            ptst = conn.prepareStatement(sql2);
            ptst.setString(2, name);
            ptst.setString(3, sex);
            ptst.setFloat(1, sumTime);
            ptst.setString(4, month);
            ptst.execute();
            status = "success";
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return status;
    }
}
