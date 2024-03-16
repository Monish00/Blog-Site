package com.servlets;
import java.io.IOException;
import java.util.List;

import com.JDBC.BlogDB;
import com.helper.CurrentUser;
import com.helper.currentView;
import com.models.Blog;
import com.models.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class controller extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BlogDB db = new BlogDB();
		User user = null;
		boolean myBlog = false;
		try {
			user = CurrentUser.getUser();
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("user");
			System.out.println(u);
			System.out.println(user);
			if (user == null) {
				response.sendRedirect("html/Login_Page.jsp");
				return;
			}
			if(request.getParameter("tab") != null) {
			myBlog = request.getParameter("tab").equals("myblog");
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		List<Blog> rs = null;
		if(myBlog && user != null) {
			rs = db.myBlog(user.getId());
			currentView.setBlog(rs);
			request.setAttribute("data", rs);
		}
		else {
			System.out.println("nr");
			rs= db.getAllValues();
			currentView.setBlog(rs);
			request.setAttribute("data", rs);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		BlogDB db = new BlogDB();
		User user = null;
		try {
			user = CurrentUser.getUser();
			System.out.println(user.getName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		System.out.println(user!=null ? "USer :"+user.getId() : "User not found");
		if(action != null && user !=null) {
			if(action.equals("add")) {
				String title = req.getParameter("title");
				String desc = req.getParameter("desc");
				String content = req.getParameter("bcontent");
				System.out.println( content	);
				String hashTag = req.getParameter("tag");
				int aid = user.getId();
				System.out.println("post"+aid);
				try {
				Blog blog = new Blog(0,title,desc,content,aid);
				int a = db.addblog(blog,hashTag);
				System.out.println(a);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}

			if(action.equals("update")) {
				String title = req.getParameter("title");
				String desc = req.getParameter("desc");
				String content = req.getParameter("content");
				int bid = Integer.parseInt(req.getParameter("id"));

				int aid = user.getId();
				Blog blog = new Blog(bid,title,desc,content,aid);
				int a = db.updateBlog(blog);
				System.out.println(a);   
			}
			System.out.println(action);
		}
		List<Blog> rs = db.getAllValues();
		req.setAttribute("data", rs);
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, res);
	}
}