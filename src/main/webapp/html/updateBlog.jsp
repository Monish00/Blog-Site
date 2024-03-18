<%@page import="com.models.Blog"%>
<%@page import="com.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Blog</title>
    <link rel="shortcut icon" href="../assets/images/logo.jpg" type="image/x-icon">
      <style>
		*{
    font-family: 'Poppins', sans-serif;
    padding: 0;
    margin: 0;
    color: black;
}
/* BlogInsert CSS */
.maincontainer{
    width: 100vw;
    height: 100vh;
    /* border: 2px solid red; */
}

.container1{
    align-items: center;
    display: flex;
    flex-direction: column;
    width: 50vw;
    /* border: 2px solid green; */
    
}


.container1 > a >img{
    margin-top: 20px;
    width: 150px;
    height: 150px;
    /* border: 2px solid firebrick; */
}
.container2{
    width: 50vw;
    /* border: 2px solid mediumaquamarine; */
    display: flex;
    flex-direction: column;
    border-radius: 5px;
    
}
.container2 > #second{
    height: 54px;
}
.container2 > label{
    font-size: 20px;
    font-weight: bolder;
}
.container2 > input{
    font-size: 20px;
    height: 30px;
}
.container2 > textarea{
    font-size: 20px;
    /* height: 30px; */
}
.container2 > button{
    margin-top: 20px;
    height: 40px;
    background-color: white;
    border: none;
    outline: none;
    color: black;
    font-size: 20px;
    border-radius: 10px;
    box-shadow: 0px 3px 22px 2px;
    margin-bottom: 20px;
    transition: all 2s;
}
.container2 > button:hover{
    cursor: pointer;
    background-color: grey;
}

.submain{
    /* border: 2px solid navy; */
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}
.container2 > label{
    margin-top: 30px;
}
	</style>

</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    Blog blog = null;
    if (user != null) {
        blog = (Blog) request.getAttribute("blog");
        if (blog == null) {
            blog = (Blog) session.getAttribute("currentBlog");
        }
        if (blog != null) {
%>
<div class="maincontainer">
    <form method="post" action="../updateBlogServlet"> <!-- Adjust action accordingly -->
        <div class="submain">
            <div class="container1">
                <a href="#">
                    <img src="../assets/images/logo.jpg" alt="Logo">
                </a>
                <h5>Update your blog</h5>
            </div>
            <div class="container2">
                <input type="hidden" name="id" value="<%= blog.getId() %>">
                <label for="title">Title</label>
                <input type="text" name="title" value="<%= blog.getTitle() %>">
                <label for="desc">Description</label>
                <input type="text" name="desc" value="<%= blog.getDesc() %>" id="second">
                <label for="content">Content</label>
                <textarea name="content" id="content" cols="30" rows="10"><%= blog.getContent() %></textarea>
                <input type="hidden" name="action" value="update">
                <button type="submit">Update</button>
            </div>
        </div>
    </form>
</div>
<%
        } else {
        	System.out.println("blog is empty");
            response.sendRedirect("../displayDetails"); 
        }
    } else {
        response.sendRedirect("./Login_Page.jsp"); 
    }
%>
</body>
</html>
