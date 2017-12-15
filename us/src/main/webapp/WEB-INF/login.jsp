<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container" id="login" align="center" style=" position:absolute;
  top:50%;
  left:50%;
  padding:10px;
  -ms-transform: translateX(-50%) translateY(-50%);
  -webkit-transform: translate(-50%,-50%);
  transform: translate(-50%,-50%);">
  <h2>Login</h2>
  <form name="login_form" action="login" method="post">
    <div class="form-group">
      <label for="email">User ID:</label>
      <input type="text" name="user_id" class="form-control" placeholder="Enter user id">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
    </div>
    <div class="form-check">
      <label class="form-check-label">
        User<input type="radio" name="loginAs" value="user" style="margin: 5px;"> 
					Restaurant<input type="radio" name="loginAs" value="restaurant" style="margin: 5px;">
      </label>
    </div>
    <button type="submit" class="btn btn-primary" value="login">Submit</button>
  </form>
</div>
				
</body>
</html>