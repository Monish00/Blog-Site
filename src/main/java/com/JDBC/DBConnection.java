package com.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.models.Blog;

public class DBConnection {
    private Connection con;
    
    public DBConnection() {
    }
    public Connection getCon() {
    	if(con == null) driveRegistor();
    	return con; 
    }
    public void driveRegistor() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/blog?user=root&password=admin");
            System.out.println("Driver Registered");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void closeResources(Statement stmt, ResultSet rs) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
