package com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TagManager {
	public void createTag(int id, String tag) {
		String[] tagArr = tag.trim().split("#");
		DBConnection db = new DBConnection();
		try {
			java.sql.PreparedStatement ps = db.getCon().prepareStatement("Insert into tag (tags, bid) values (?,?);");
			for(String t : tagArr) {
				ps.setString(1, t.trim().toLowerCase());
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void userTag(int uid, int bid) {
		DBConnection db = new DBConnection();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = db.getCon();
			LocalDateTime currentDateTime = LocalDateTime.now();
			Timestamp timestamp = Timestamp.valueOf(currentDateTime);

			pstmt = connection.prepareStatement("INSERT INTO tagmatcher (uid, tig, time) VALUES (?, ?, ?) " +
					"ON DUPLICATE KEY UPDATE time = VALUES(time);");

			pstmt.setInt(1, uid);
			pstmt.setInt(2, bid);
			pstmt.setTimestamp(3, timestamp);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}	

}
