<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Blog | Login</title>
<link rel="shortcut icon" href="../assets/images/logo.jpg"
	type="image/x-icon">
<link rel="stylesheet" href="../css/Login_Page.css">
</head>
<body>
	<div id="supermain">


		<div id="container" class="same">
			<div id="log">
				<img src="../assets/images/logo.jpg" alt="LOGO">
				<h2>Blog</h2>
			</div>
		</div>

		<form method="post" action="../login">

			<div id="main" class="same">
				<div id="inputs">
					<input type="email" name="email" placeholder="Email"> <input
						type="password" name="password" placeholder="Password"> <input
						type="hidden" name="action" value="login" placeholder="Password">

				</div>
				<div id="btn">
					<button type="submit">Login</button>
				</div>
				<div id="indigation">
					<p>
						I Don't Have Any Account <a href="SignUp_Page.html">SignUp</a>
					</p>
				</div>
			</div>
		</form>


	</div>

	<script>
	<%if( request.getAttribute("message") != null ) {%>
	 var alertMessage = "<%= request.getAttribute("message") %>";
        if (alert !== "null" && alertMessage && alertMessage.trim()) {
            alert(alertMessage);
        }
    <%}%>
</script>
</body>
</html>