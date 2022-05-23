package com.justmax.virtualstyler.mysql;

import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private static final String host = "37.140.192.75";
    private static final String database = "u1679042_virtualstyler";
    private static final String url = "jdbc:mysql://" + host +
            "/" + database + "?" +
            "useUnicode=true&" +
            "serverTimezone=Europe/Moscow&" +
            "useSSL=false&" +
            "characterEncoding=UTF-8&" +
            "zeroDateTimeBehavior=convertToNull&" +
            "retainStatementAfterResultSetClose=true";
    private static final String user = "u1679042";
    private static final String password = "virtualstyler_db";
    private static Connection con;

    public static void setDriverJDBC() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean connect() {
        try {
            if (con == null || con.isClosed())
                con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Невозможно подключиться к базе данных, так как сервер был отключён");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean disconnect() {
        try {
            if (!con.isClosed())
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean executeCommand(String $) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute($);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Nullable
    public static ResultSet select(String $) {
        try {
            Statement stmt = con.createStatement();
            return stmt.executeQuery($);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
