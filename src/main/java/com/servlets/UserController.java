//package com.servlets;
//
//import java.io.IOException;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import com.JDBC.UserDB;
//import com.helper.CurrentUser;
//import com.models.User;
//
///**
// * Servlet implementation class UserController
// */
////@WebServlet("/UserController")
//public class UserController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public UserController() {
//		super();
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("displayDetails");
//		dispatcher.forward(request, response);
//
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getParameter("action");
//		System.out.println(action);
//		System.out.println("action not found");
//		if(action.equals("signin")) {
//			String name = request.getParameter("name");
//			String email = request.getParameter("email");
//			String password = request.getParameter("password");
//			User u = new User(name,email,password);
//			User user = new UserDB().setUser(u);
//        	CurrentUser.setUser(user);
//            System.out.println(user.getName());
//			RequestDispatcher dispatcher = request.getRequestDispatcher("displayDetails");
//			dispatcher.forward(request, response);
//		}
//		else if(action.equals("login")){
//			String email = request.getParameter("email");
//			String password = request.getParameter("password");
//			User user = new UserDB().getUser(email, password);
//			if (user == null) {
//				System.out.println("User not found !!");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("Login_Page.html");
//				dispatcher.forward(request, response);
//			} else {
//				HttpSession session = request.getSession();
//				session.setAttribute("user", user);
//				System.out.println(user.getName());
//				RequestDispatcher dispatcher = request.getRequestDispatcher("displayDetails");
//				dispatcher.forward(request, response);
//			}
//		}
//		RequestDispatcher dispatcher = request.getRequestDispatcher("displayDetails");
//		dispatcher.forward(request, response);
//	
//	}
//}
