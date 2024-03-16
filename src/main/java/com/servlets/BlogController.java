//package com.servlets;
//import java.io.IOException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.*;
//
//
//import com.JDBC.BlogDB;
//import com.JDBC.DBConnection;
//import com.models.Blog;
//import com.models.User;
//
//public class BlogController extends HttpServlet{
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BlogDB db = new BlogDB();
//		User user = null;
//		boolean myBlog = false;
//		try {
//			HttpSession session = request.getSession();
//			try {
//				user = (User) session.getAttribute("user");
//				
//			}
//			catch(Exception e) {}
//
//			myBlog = request.getParameter("tab").equals("myblog");
//			System.out.println(myBlog);
//		}
//		catch(Exception e) {
//		}
//		List<Blog> rs = null;
//		if(myBlog && user != null) {
//			rs = db.myBlog(user.getId());
//			request.setAttribute("data", rs);
//		}
//		else {
//			rs= db.getAllValues();
//			request.setAttribute("data", rs);
//		}
//		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//		dispatcher.forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		String action = req.getParameter("action");
//		BlogDB db = new BlogDB();
//		User user = null;
//		HttpSession session = req.getSession();
//		try {
//			user = (User) session.getAttribute("user");
//		}
//		catch(Exception e) {}
//
//		System.out.println(user!=null ? user.getId() : "User not found");
//		if(action != null && user !=null) {
//			if(action.equals("add")) {
//				String title = req.getParameter("title");
//				String desc = req.getParameter("desc");
//				String content = req.getParameter("content");
//				int aid = user.getId();
//				Blog blog = new Blog(0,title,desc,content,aid);
//
//				int a = db.addblog(blog);
//				System.out.println(a);
//			}
//
//			if(action.equals("update")) {
//				String title = req.getParameter("title");
//				String desc = req.getParameter("desc");
//				String content = req.getParameter("content");
//				int bid = Integer.parseInt(req.getParameter("id"));
//
//				int aid = user.getId();
//				Blog blog = new Blog(bid,title,desc,content,aid);
//				int a = db.updateBlog(blog);
//				System.out.println(a);   
//			}
//			System.out.println(action);
//		}
////		List<Blog> rs = db.getAllValues();
////		req.setAttribute("data", rs);
////		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
////		dispatcher.forward(req, res);
//	}
//}