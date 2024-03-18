<%@page import="com.models.User"%>
<%@page import="com.models.Blog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog | Read More</title>
    <link rel="shortcut icon" href="../assets/images/logo.jpg" type="image/x-icon">
      <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300&display=swap');

        body {
            margin: 0;
            padding: 0;
            font-weight: bolder;
            font-size: 20px;
            font-family: 'Poppins', sans-serif;
        }

        #nav {
            display: flex;
            align-items: center;
            justify-content: space-around;
        }

        #nav>#logo {
            display: flex;
            /* border: 2px solid red; */
        }

        #nav>#logo>span {
            position: relative;
            top: 4.9rem;
            left: 0.2rem;
            color: gray;
            font-size: 29px;
        }

        #nav>#links>a>button {
            width: 10rem;
            height: 3rem;
            background-color: white;
            color: black;
            outline: none;
            border: 1px solid black;
            font-size: 20px;
            border-radius: 10px;
            transition: all 2s;
        }

        #nav>#links>a>button:hover {
            background-color: gray;
            color: white;
            border: none;
            outline: none;
            cursor: pointer;

        }

        #nav>#links>#firstbtn {
            margin-right: 20px;
        }

        #logo>img {
            width: 130px;
            height: 130px;
        }

        #innerform {
            /* border: 2px solid red; */
            width: 80vw;
            position: absolute;
            right: 160px;
            height: auto;
            top: 12rem;
        }

        #innerform>form {
            display: flex;
            flex-direction: column;
        }

        #innerform>form>input {
            height: 40px;
        }

        #innerform>form>.lables {
            margin-top: 20px;
        }

        #innerform>form>#secondinp {
            height: 50px;
        }

        #innerform>form>button {
            height: 50px;
            margin-top: 20px;
            border-radius: 10px;
            background-color: white;
            color: black;
            outline: none;
            border: 1px solid black;
            font-size: 20px;
            border-radius: 10px;
            transition: all 2s;
        }

        #innerform>form>button:hover {
            background-color: gray;
            color: white;
            border: none;
            outline: none;
            cursor: pointer;

        }

        #contant {
            overflow: auto;
            /* border: 1px solid firebrick; */
            max-height: 39.5vh;
        }
    </style>

</head>
<body>
    <%
    Blog blog = (Blog) request.getAttribute("blog");
    User user = (User) session.getAttribute("user");
    %>
    <div id="container">
        <div id="nav">
            <div id="logo">
                <img src="*./assets/images/logo.jpg" alt="Logo">
                <span>Blog</span>
            </div>
            <% if(user != null && user.getId() == blog.getAid()) { 
            	session.setAttribute("currentBlog",blog);
            %>
            
            <div id="links">
                <a href="./html/updateBlog.jsp"><button>Edit</button></a>
                <a href="./deleteBlog?blogId=<%= blog.getId()%>&aid=<%= blog.getAid() %>"><button>Delete</button></a>
            </div>
            <% } %>
        </div>
        <div id="innerform">
            <form action="#">
                <h1><%=blog.getTitle() %></h1>
                <p><%=blog.getDesc() %></p>
                <p><%=blog.getContent() %></p>
            </form>
        </div>
    </div>
</body>
</html>
