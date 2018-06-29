package org.gc.manage.dao.util;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/GeneralCourse";
        String userName = "root";
        String userPWD = "admin";

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(url, userName, userPWD);

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean execute(String sql){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ResultSet query(String sql) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            return statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
