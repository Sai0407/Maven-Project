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

<title>Urbanspoon</title>
</head>
<body>
<div class="container">
	<dir class="header">
	
	</dir>
	<dir class="body">
		<div>
			<nav class="navbar navbar-expand-sm bg-light">
			  <ul class="navbar-nav">
			    <li class="nav-item">
			      <a class="nav-link" href="loginPage">Sign-up</a>
			    </li>
			   <li class="nav-item dropdown">
			      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
			        Register
			      </a>
			      <div class="dropdown-menu">
			        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#userReg">User</a>
			        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#restaurantReg">Restaurant</a>
			      </div>
			    </li>
			  </ul>
			</nav>
		</div>
		<div>
			<table border="1" class="table table-light">
				<thead>
					<td><dt>S.No</dt></td>
					<td><dt>Restaurant Name</dt></td>
					<td><dt>logo</dt></td>
				</thead>
				<tbody>
					<c:forEach items="${restaurantsList}" var="restaurant">
						<tr>
							<td>${restaurant.id}</td>
							<td><a href="#demo" data-toggle="collapse">${restaurant.name}</a></td>
							<td><img src="/webapp/Images/restaurants/${restaurant.logo}" alt="${restaurant.logo}" width="50px" height="50px"></td>
						</tr>
						<tr>
							<c:forEach items="${restaurant.branchesList}" var="branch">
								<tr class="col-md-12">
									<td><a href="">${branch.location},${branch.city},${branch.state}</a></td>
								</tr>	
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</dir>
</div>


<div class="modal fade" id="signUp">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <div class="modal-header">
          <h4 class="modal-title">Sign-up</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         	<div class="container">
			  <form name="login_form" action="login" method="post">
			    <div class="form-group">
			      <label for="email">User ID:</label>
			      <input type="text" name="user_id" class="form-control" placeholder="Enter User ID">
			    </div>
			    <div class="form-group">
			      <label for="pwd">Password:</label>
			      <input type="password" name="password" class="form-control" placeholder="Enter password">
			    </div>
			    <div class="radio">
			      <label class="radio-inline"><input type="radio" name="loginAs" value="user">User</label>
			      <label class="radio-inline"><input type="radio" name="loginAs" value="restaurant">Restaurant</label>
			    </div>
			    <dir class="modal-footer">
			    	<input type="submit" class="btn btn-primary" data-dismiss="modal" value="login">
			    </dir>
			  </form>
			</div>
        </div>
        
        <!-- Modal footer -->
        <!-- <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div> -->
        
      </div>
    </div>
</div>
</body>
</html>