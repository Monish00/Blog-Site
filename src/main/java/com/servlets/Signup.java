package com.servlets;

import java.io.IOException;

import com.JDBC.UserDB;
import com.helper.CurrentUser;
import com.models.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Signup extends HttpServlet{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  try {
		  String name = request.getParameter("name");
          String email = request.getParameter("email");
          String password = request.getParameter("password");
          User user = new UserDB().setUser(new User(name, email, password));
          if (user == null) {
              System.out.println("Invalid data !!");
              RequestDispatcher dispatcher = request.getRequestDispatcher("html/SignUp_Page.html");
              request.setAttribute("message", "Invalid data !!");
              dispatcher.forward(request, response);
          } else {
        	  CurrentUser.setUser(user);
              System.out.println(user.getName());
              RequestDispatcher dispatcher = request.getRequestDispatcher("displayDetails");
              request.setAttribute("message", "logged in success");
              dispatcher.forward(request, response);
          }
      } catch (Exception e) {
          e.printStackTrace();
          request.setAttribute("message", "An error occurred while processing your request.");
          RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
          dispatcher.forward(request, response);
      }
}
  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  resp.sendRedirect("html/SignUp_Page.html");
	  super.doGet(req, resp);
	}
}
