package com.servlets;

import java.io.IOException;

import com.JDBC.UserDB;
import com.models.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 881325591566256178L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = new UserDB().getUser(email, password);
            if (user == null) {
                System.out.println("User not found !!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("html/Login_Page.jsp");
                request.setAttribute("message", "User not found !!");
                dispatcher.forward(request, response);
            } else {
//            	CurrentUser.setUser(user);
                
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                System.out.println(user.getName());
                request.setAttribute("message", "logged in success");
                response.sendRedirect("displayDetails");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while processing your request.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("html/Login_Page.jsp");
    }
}
