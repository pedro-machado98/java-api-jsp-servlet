package com.pedromachado.estoque.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbConfig {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/pedro";
    private static String user = "root";
    private static String pass = "root";


    public static Connection connect() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Data Base Connected!");
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e.getCause());
        }

        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Data Base connection closed.");
            } catch (Exception e) {
                throw new DbException(e.getMessage(),e.getCause());
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
                System.out.println("Prepared Statement closed.");
            } catch (Exception e) {
                throw new DbException(e.getMessage(),e.getCause());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                System.out.println("Result set closed.");
            } catch (Exception e) {
                throw new DbException(e.getMessage(),e.getCause());
            }
        }
    }


}
