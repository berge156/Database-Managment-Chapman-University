package com.company;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDB {

    //Exception throwing, getting the Columns
    public static void print(ResultSet results) throws SQLException {
        ResultSetMetaData data = results.getMetaData();
        int ColumnNum = data.getColumnCount();

        for (int i = 1; i <= ColumnNum; ++i) {
            if (i > 1)
                System.out.print(" ");
            String columnName = data.getColumnName(i);
            System.out.print(columnName);
        }
        System.out.println();

        while (results.next()) {
            for (int i = 1; i <= ColumnNum; i++) {
                if (i > 1)
                    System.out.print(" ");
                String columnAmount = results.getString(i);
                System.out.print(columnAmount);
            }
            System.out.println();
        }
    }

    //Finding the connection, establishing connection to the server
    public static Connection getMySqlConnection(String IP, String DB, String userName) {
        Connection mysqlConnection = null;
        try {
            String connectionUrl = "jdbc:mysql://35.247.2.240:3306/lukebergerA3"; //my Cloud Account
            mysqlConnection = DriverManager.getConnection(String.format(connectionUrl, IP, DB), userName, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlConnection;
    }
}