package com.kxxy.salary.find;

import com.kxxy.salary.model.msgModel;
import com.kxxy.salary.model.workModel;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;

public class find {
    public static final String URL = "jdbc:mysql://localhost:3306/forSalary?useSSL=false&serverTimezone=UTC&allowMutiQueries=true&characterEncoding=UTF-8";
    public static final String USER = "root";
    public static final String PASSWORD = "Wxy5211314";

    public Connection DAO() {
        Connection conn = null;
        try {
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

    public static float getData(String name, String sex, String month)
    {
        find dao = new find();
        Connection conn = dao.DAO();
        String sql = "select time from workTime where name = (?) and sex = (?) and month = (?)";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setString(1, name);
            ptst.setString(2, sex);
            ptst.setString(3, month);
            ResultSet rs = ptst.executeQuery();
            if(!rs.next())
                return -1;
            return rs.getFloat("time");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }
    public static msgModel getWorker(String name, String month)
    {
        msgModel wm = new msgModel();
        find dao = new find();
        Connection conn = dao.DAO();
        String sql = "select * from worker where name = (?)";
        String sql2 = "select time from workTime where name = (?) and month = (?)";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setString(1, name);
            ResultSet rs = ptst.executeQuery();
            if(!rs.next())
                return wm;
            wm.setName(name);
            wm.setPhone(rs.getString("phone"));
            wm.setSex(rs.getString("sex"));
            ptst = conn.prepareStatement(sql2);
            ptst.setString(1, name);
            ptst.setString(2, month);
            rs = ptst.executeQuery();
            if(!rs.next())
                wm.setTime(0);
            else
                wm.setTime(rs.getFloat("time"));
            return wm;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return wm;
    }
}
