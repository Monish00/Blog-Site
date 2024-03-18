package com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TagManager {
	public void createTag(int id, String tag) {
	    String[] tagArr = tag.trim().split("#");
	    DBConnection db = new DBConnection();
	    try {
	        java.sql.PreparedStatement ps = db.getCon().prepareStatement("INSERT INTO tag (tags) VALUES (?) ON DUPLICATE KEY UPDATE tags = tags;",Statement.RETURN_GENERATED_KEYS);
	        
	        for (String t : tagArr) {
	            if (!t.isEmpty() && !t.equals("")) {
	                t = t.trim().toLowerCase();
	                try {
	                    ps.setString(1, t);
	                    ps.executeUpdate();
	                    System.out.println("Tag added: " + t);
	                    blogTag(t,id);
	                    ResultSet rs = ps.getGeneratedKeys();
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {	
	    }
	}


	
	public void blogTag(String tag, int bid) {
		DBConnection db = new DBConnection();
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps1 = null;	
		ResultSet rs = null;

		try {
			connection = db.getCon();
			ps1 = connection.prepareStatement("INSERT INTO blogtagmatcher (tid, bid) VALUES ((select id from tag where tags = ?), ?);");
            ps1.setString(1, tag);
            ps1.setInt(2, bid);
            ps1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			try {
				if(ps1!=null) ps1.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}		
	
	public void deleteBlogTag(int bid) {
		DBConnection db = new DBConnection();
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = db.getCon();
			pstmt = connection.prepareStatement("delete from blogtagmatcher where bid = ?");
			pstmt.setInt(1, bid);
			pstmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
				try {
					if(pstmt != null) pstmt.close();
					if(connection != null) connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	
	public void userTag(int uid, int bid) {
	    DBConnection db = new DBConnection();
	    Connection connection = null;
	    PreparedStatement pstmt1 = null;
	    ResultSet rs = null;

	    try {
	        connection = db.getCon();
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

	        pstmt1 = connection.prepareStatement("SELECT tid FROM blogtagmatcher WHERE bid = ?");
	        pstmt1.setInt(1, bid);
	        rs = pstmt1.executeQuery();

	        while (rs.next()) {
	            int tid = rs.getInt("tid");
	            try (PreparedStatement pstmt = connection.prepareStatement(
	                    "INSERT INTO tagmatcher (uid, tig, time) VALUES (?, ?, ?) " +
	                            "ON DUPLICATE KEY UPDATE time = VALUES(time)")) {

	                pstmt.setInt(1, uid);
	                pstmt.setInt(2, tid);
	                pstmt.setTimestamp(3, timestamp);
	                pstmt.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt1 != null) pstmt1.close();
	            if (connection != null) connection.close(); // Close connection
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	public void deleteOldTag() {
		DBConnection db = new DBConnection();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = db.getCon();
			stmt = connection.createStatement();
			String query = "DELETE FROM tagmatcher  WHERE DATEDIFF(NOW(), time) > 7;";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}	


}
