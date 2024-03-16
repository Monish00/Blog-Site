package com.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.helper.CurrentUser;
import com.models.Blog;
import com.models.User;

public class BlogDB {
	private int count = 0;
	public int addblog(Blog blog,String tag) {
		int a = 0;
		DBConnection db = new DBConnection();
		try {
		    if (db.getCon() == null) {
		        db.driveRegistor();
		    }
		    System.out.println("Entered");
		    String sql = "INSERT INTO blog_data (btitle, bdesc, bcontent, aid) VALUES (?, ?, ?, ?)";
		    try (PreparedStatement pstmt = db.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		        pstmt.setString(1, blog.title);
		        pstmt.setString(2, blog.desc);
		        pstmt.setString(3, blog.content);
		        pstmt.setInt(4, blog.aid);
		        a = pstmt.executeUpdate();
		        
		        if (a > 0) {
		            ResultSet generatedKeys = pstmt.getGeneratedKeys();
		            if (generatedKeys.next()) {
		                int id = generatedKeys.getInt(1);
		                TagManager tm = new TagManager();
		                tm.createTag(id, tag);
		                System.out.println("Blog inserted successfully.");
		            } else {
		                System.out.println("Failed to retrieve generated keys.");
		            }
		        } else {
		            System.out.println("Failed to insert the blog.");
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    return a;
	}

	
	public int updateBlog(Blog blog) {
		int a=0;
		DBConnection db = new DBConnection();
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			String sql = "UPDATE blog_data SET  BTITLE= ? , BDESC = ?, BCONTENT = ? WHERE ID = ?";
			try (PreparedStatement pstmt = db.getCon().prepareStatement(sql)) {
				
				pstmt.setString(1, blog.title);
				pstmt.setString(2, blog.desc);
				pstmt.setString(3, blog.content);
				pstmt.setInt(4,blog.id);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Blog updated successfully.");
				} else {
					System.out.println("Failed to update the blog.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	public int deleteBlog(Blog blog,int aid) {
		int a=0;
		DBConnection db = new DBConnection();
		if(aid == blog.aid)
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			Statement stmt = db.getCon().createStatement();
			a=stmt.executeUpdate("delete from blog_data where id = "+blog.id+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}
	
	
	public List<Blog> getAllValues() {
		List<Blog> result = new ArrayList<>();
		ResultSet rs = null;
		DBConnection db = new DBConnection();
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			count=0;
			Statement stmt = db.getCon().createStatement();
			User user = CurrentUser.getUser();
//			if(user != null) {
//				rs = stmt.executeQuery("SELECT * FROM blog_data where id in (select t.bid from tag t inner join tagMatcher tm on t.id = tm.tig and tm.uid = "+user.getId()+")");
//				if(!rs.next()) rs = stmt.executeQuery("SELECT * FROM blog_data");
//			}
//			else {
			rs = stmt.executeQuery("SELECT * FROM blog_data");
//			}
			
			while (rs.next()) {
				int bid = rs.getInt("id");
				String btitle = rs.getString("btitle");
				String bdesc = rs.getString("bdesc");
				String bcontent = rs.getString("bcontent");
				int aid = rs.getInt("aid");
				Blog blog = new Blog(bid,btitle,bdesc,bcontent,aid);
				result.add(blog);
			}
			count = result.size();
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeResources(null, rs);
		}

		return result;
	}
	
	public List<Blog> search(String title) {
	    List<Blog> result = new ArrayList<>();
	    ResultSet rs = null;
	    DBConnection db = new DBConnection();
	    PreparedStatement pstmt = null;
	    try {
	        if (db.getCon() == null) {
	            db.driveRegistor();
	        }
	        count = 0;
	        String query = "SELECT * FROM blog_data WHERE INSTR(btitle, ?) > 0";
	        pstmt = db.getCon().prepareStatement(query);
	        pstmt.setString(1, title);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            int bid = rs.getInt("id");
	            String btitle = rs.getString("btitle");
	            String bdesc = rs.getString("bdesc");
	            String bcontent = rs.getString("bcontent");
	            int aid = rs.getInt("aid");
	            Blog blog = new Blog(bid, btitle, bdesc, bcontent, aid);
	            result.add(blog);
	        }
	        count = result.size();
	        System.out.println(count);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        db.closeResources(pstmt, rs);
	    }
	    return result;
	}

	public List<Blog> myBlog(int uid) {
		List<Blog> result = new ArrayList<>();
		ResultSet rs = null;
		DBConnection db = new DBConnection();
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			count=0;
			Statement stmt = db.getCon().createStatement();
			rs = stmt.executeQuery("SELECT * FROM blog_data where aid = "+uid);

			while (rs.next()) {
				int bid = rs.getInt("id");
				String btitle = rs.getString("btitle");
				String bdesc = rs.getString("bdesc");
				String bcontent = rs.getString("bcontent");
				int aid = rs.getInt("aid");
				Blog blog = new Blog(bid,btitle,bdesc,bcontent,aid);
				result.add(blog);
			}
			count = result.size();
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeResources(null, rs);
		}

		return result;
	}
}
