package com.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.models.Blog;
import com.models.User;

public class BlogDB {
	private int count = 0;
	public int addblog(Blog blog,String tag) {
		int a = 0;
		DBConnection db = new DBConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			System.out.println("Entered");
			String sql = "INSERT INTO blog_data (btitle, bdesc, bcontent, aid) VALUES (?, ?, ?, ?)";
			pstmt = db.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, blog.getTitle());
			pstmt.setString(2, blog.getDesc());
			pstmt.setString(3, blog.getContent());
			pstmt.setInt(4, blog.getAid());
			a = pstmt.executeUpdate();

			if (a > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					TagManager tm = new TagManager();
					tm.createTag(id, tag);
					System.out.println("Blog inserted successfully.");
				} else {
					System.out.println("Failed to retrieve generated keys.");
				}
			} else {
				System.out.println("Failed to insert the blog.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return a;
	}


	public int updateBlog(Blog blog) {
		int a=0;
		DBConnection db = new DBConnection();
		PreparedStatement pstmt = null;
		try  {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			String sql = "UPDATE blog_data SET  BTITLE= ? , BDESC = ?, BCONTENT = ? WHERE ID = ?";

			pstmt = db.getCon().prepareStatement(sql);
			pstmt.setString(1, blog.getTitle());
			pstmt.setString(2, blog.getDesc());
			pstmt.setString(3, blog.getContent());
			pstmt.setInt(4,blog.getId());
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Blog updated successfully.");
			} else {
				System.out.println("Failed to update the blog.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return a;
	}

	public int deleteBlog(int id) {
		int a=0;
		DBConnection db = new DBConnection();
		Statement stmt = null;
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			stmt = db.getCon().createStatement();
			TagManager tm = new TagManager();
			tm.deleteBlogTag(id);
			a=stmt.executeUpdate("delete from blog_data where id = "+id+"");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return a;
	}


	public List<Blog> getAllValues(User user) {
		List<Blog> result = new ArrayList<>();
		ResultSet rs = null;
		DBConnection db = new DBConnection();
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			count=0;
			Statement stmt = db.getCon().createStatement();
			if(user != null) {
				rs = stmt.executeQuery("SELECT * FROM blog_data where id in (select tb.bid from blogtagmatcher tb inner join tagMatcher tm on tb.tid = tm.tig and tm.uid = "+user.getId()+") or aid = "+ user.getId()+";");
			}
			else {
				rs = stmt.executeQuery("SELECT * FROM blog_data");
			}

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

	public List<Blog> search(String title)  {
		List<Blog> result = new ArrayList<>();
		ResultSet rs = null;
		DBConnection db = new DBConnection();
		PreparedStatement pstmt = null;
		try {
			if (db.getCon() == null) {
				db.driveRegistor();
			}
			String query = "SELECT * FROM blog_data WHERE INSTR(btitle, ?) > 0";
			pstmt = db.getCon().prepareStatement(query);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			List<Integer> ids = new ArrayList<Integer>();
			while (rs.next() ) {
				int bid = rs.getInt("id");
				String btitle = rs.getString("btitle");
				String bdesc = rs.getString("bdesc");
				String bcontent = rs.getString("bcontent");
				int aid = rs.getInt("aid");
				Blog blog = new Blog(bid, btitle, bdesc, bcontent, aid);
				ids.add(bid);
				result.add(blog);
			}
			String query1 = "SELECT * FROM blog_data WHERE INSTR(bdesc, ?) > 0 ";
			query1 += (ids.isEmpty())?";":"and id not in "+ids.toString().replace("[", "(").replace("]", ")")+";";
			System.out.println(query1);
			pstmt = db.getCon().prepareStatement(query1);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while (rs.next() ) {
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		}

		return result;
	}
}
