package com.jwebscan.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Basil
 * @create 2022/5/27 10:22
 */
public class DataBaseHelper {
    public static final String URL = "jdbc:mysql://localhost:3309/nmap_vuln";
    public static final String NAME = "com.mysql.cj.jdbc.Driver";
    public static final String USER = "root";
    public static final String PASSWORD = "djh2759";
    public Connection conn = null;
    public PreparedStatement pst = null;

    public DataBaseHelper(String sql) {
        try {
            //指定连接类型
            Class.forName(NAME);
            //获取连接pst = conn.prepareStatement(sq1);I/准备执行语句
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            pst = conn.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int dataBaseExecute(String sql) throws SQLException {
        return pst.executeUpdate(sql);
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
