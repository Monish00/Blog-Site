
<%@page import="com.helper.CurrentUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.models.Blog, com.models.User, jakarta.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Blog</title>
<link rel="shortcut icon" href="./assets/images/logo.jpg" type="image/x-icon">
<link rel="stylesheet" href="css/main.css">
 
</head>
<body>
	<%
    List<Blog> data = (List<Blog>)request.getAttribute("data");
    System.out.println(data.size());
    User user = null;
    try{
    user = CurrentUser.getUser();
    }
    catch(Exception e){	
    }
    %>
	<div class="container">
		<div class="off1" id="offid1">
			<img src="./assets/images/logo.jpg" alt="logo" id="logoimg"> <a
				href="#" id="innerid"><b>Blog</b></a> 
				<a href="displayDetails" id="innerid2" class="innerid">Home</a> 
					<% if(user != null){%>
				<a href="displayDetails?tab=myblog" id="innerid3" class="innerid3">MyBlog</a>
				<a href = "./html/BlogInsert.html" id="innerid4" class="innerid4">Add</a>
				
				<%}%>
		</div>
		
		<div class="off2" id="offid2">
			<form method="get" action = "search">
				<input type="text" placeholder="Seach Here" name = "title" id="inputtag"> 
				<button type = "submit">
				<img src="./assets/images/searchicon.png" alt="SearchHear">
                </button>
			</form>
		</div>
		<div class = "off3" id="offid3">
				<% if(user != null){%>
				<p class = "head3" style = "width:auto;"><%=user.getName()%></p>
				<button class="buttonlog2" onclick="logOut()">LogOut</button>
				<%
                }else{
                %>
				<button class="buttonlog"> <a href="./html/Login_Page.jsp">Log In</a></button>
				<%
                }
				%>
		</div>
	</div>

     <% if (data != null && !data.isEmpty()) { %>
	<div class="anothersections">
	     <% for(Blog s : data) { %>
		<div class="section">
			<div class="imgsection">
				<img src="./assets/images/sampleimg.jpeg" alt="">
			</div>
			<div class="textsection">
				<div class="heads"><%= s.title %></div>
				<div class="paras"><%= s.desc %></div>
				<div class="ano">
				<% 
				  if(s.content !=null && s.content.length()>100){
				%>
				<%=s.content.substring(0,100) %>
				<% }else {%>
				<%=s.content %>
				<%}
				%>
				</div>
				<a href="blogDispatcher?id=<%=s.id%>" ><button  class="anotherbutton">Read More</button></a>
			</div>
		</div>
		 <% } %>
	</div>
	<% } %>
    <script>
        function logOut() {
        	<%
         	 // CurrentUser.setUser(null);
        	%>
        	window.location.href = 'displayDetails';
        }
    </script>	
</body>
</html>


