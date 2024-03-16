package com.servlets;

import java.io.IOException;
import java.util.List;

import com.JDBC.BlogDB;
import com.models.Blog;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = (String) req.getParameter("title");
        if (search != null && !search.isEmpty()) { 
            List<Blog> blogs = new BlogDB().search(search);
            req.setAttribute("data", blogs);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("displayDetials");
            dispatcher.forward(req, resp);
        }
    }
}
