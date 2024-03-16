package com.servlets;

import java.io.IOException;

import com.JDBC.TagManager;
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

public class BlogDisptcher extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt( req.getParameter("id"));
		Blog b = currentView.getBlogById(id);
		if(b != null) {
		User user = CurrentUser.getUser();
		if(user != null) {
			TagManager tm = new TagManager();
			tm.userTag(user.getId(), b.id);	
		}
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		System.out.println(u);
		req.setAttribute("blog", b);
		RequestDispatcher dispatcher = req.getRequestDispatcher("html/blogview.jsp");
		dispatcher.forward(req, resp);
		return;
		}
		else {
			resp.sendRedirect("displayDetails");
			return;
		}
	}
}
