package com.servlets;

import java.io.IOException;

import com.JDBC.BlogDB;
import com.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("blogId"));
        int aid = Integer.parseInt(req.getParameter("aid"));
        HttpSession ses = req.getSession();
        User user = (User) ses.getAttribute("user");
        BlogDB db = new BlogDB();
        if (user != null && user.getId() == aid) {
            db.deleteBlog(id);
            resp.sendRedirect("./displayDetails");
        } else {
            resp.sendRedirect("./displayDetails");
        }
    }
}
