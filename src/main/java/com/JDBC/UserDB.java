package com.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.models.User;
import com.servlets.controller;

public class UserDB {
	public User setUser(User user) {
		ResultSet rs = null;
		DBConnection db = new DBConnection();
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			else {
			String sql = "INSERT INTO USERDETIALS (name, email, password) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = db.getCon().prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			
			int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeResources(null, rs);
		}

		return user;
	}
	public User getUser(String mail, String password) {
		User user = null;
		ResultSet rs = null;
		DBConnection db = new DBConnection();
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			String sql = "SELECT * FROM userdetials WHERE email = ? AND password = ?";
			PreparedStatement preparedStatement = db.getCon().prepareStatement(sql);
			preparedStatement.setString(1, mail);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String psd = rs.getString("name");
				System.out.println(id+" "+name);
				user = new User(name,email,psd);
				user.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeResources(null, rs);
		}
		return user;
	}
}
