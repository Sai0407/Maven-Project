<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container">
		<div class="header"></div>
		<div>


			<div id="restaurants">
				<table border="1" class="table table-light">
					<c:forEach items="${restaurantsList}" var="restaurant">
						<tr>
							<td>${restaurant.id}</td>
							<td>${restaurant.name}</td>
							<td>${restaurant.logo}</td>
							<td>
								<table border="1">
									<c:forEach items="${restaurant.branchesList}" var="branch">
										<tr>
											<td>${branch.id}</td>
											<td>${branch.location}</td>
											<td>${branch.city}</td>
											<td>${branch.state}</td>
											<td>${branch.country}</td>
											<td>${branch.postalCode}</td>
											<td>
												<table border="1">
													<c:forEach items="${branch.imagesList}" var="image">
														<tr>
															<td>${image}</td>
														</tr>
													</c:forEach>
												</table>
											</td>
											<td>
												<table border="1">
													<c:forEach items="${branch.imagesList}" var="image">
														<tr>
															<td>${image}</td>
														</tr>
													</c:forEach>
												</table>
											</td>
											<td>
												<table border="1">
													<c:forEach items="${branch.feedbackList}" var="feedback">
														<tr>
															<td>${feedback.id}</td>
															<td>${feedback.user.name}</td>
															<td>${feedback.feedbackType.description}</td>
															<td>${feedback.feedbackDate}</td>
															<td>${feedback.visitedDate}</td>
															<td>${feedback.comments}</td>
															<td>${feedback.ratings}</td>
															<td></td>
														</tr>
													</c:forEach>
												</table>

											</td>
											<td>
												<table border="1">
													<c:forEach items="${branch.cuisinesList}" var="cuisine">
														<tr>
															<td>${cuisine.id}</td>
															<td>${cuisine.name}</td>
															<td>${cuisine.country}</td>
															<td>
																<table border="1">
																	<c:forEach items="${cuisine.recipesList}" var="recipe">
																		<tr>
																			<td>${recipe.id}</td>
																			<td>${recipe.name}</td>
																			<td>${recipe.description}</td>
																			<td>${recipe.price}</td>


																			<td><table border="1">
																					<c:forEach items="${recipe.feedbackList}"
																						var="feedback">
																						<tr>
																							<td>${feedback.id}</td>
																							<td>${feedback.user.name}</td>
																							<td>${feedback.branch.location}</td>
																							<td>${feedback.feedbackDate}</td>
																							<td>${feedback.visitedDate}</td>
																							<td>${feedback.comments}</td>
																							<td>${feedback.ratings}</td>
																							<td></td>
																						</tr>
																					</c:forEach>
																				</table></td>
																		</tr>
																	</c:forEach>
																</table>
															</td>

														</tr>



													</c:forEach>
												</table>

											</td>
										</tr>
									</c:forEach>
								</table>

							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div id="login" align="center">
			<h3>Login</h3>
			<form name="login_form" action="login" method="post">
				<table>

					<tr>
						<td>User ID</td>
						<td>:</td>
						<td><input type="text" name="user_id"></td>
					</tr>
					<tr>
						<td>Password</td>
						<td>:</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td>LoginAs</td>
						<td>:</td>
						<td>
							User<input type="radio" name="loginAs" value="user" style="margin: 5px;"> 
							Restaurant<input type="radio" name="loginAs" value="restaurant" style="margin: 5px;">
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td>
							<input type="submit" value="login" style="margin-top: 15px">
						</td>
					</tr>
				</table>
				<!-- Id</label> <input type="text" name="user_id"><br> <label>Password
				</label> <input type="password" name="password"><br> <label>Login
					As</label> <input type="radio" name="loginAs" value="user"> User <input
					type="radio" name="loginAs" value="restaurant"> Restaurant<br>

				<input type="submit" value="login"> -->
			</form>
		</div>
		<div id="user_registration_spring_form">
			<h3>User Registration </h3>
			<form:form action="user_registration_spring" modelAttribute="user"
				method="POST">
				<table>

					<tr>
						<td>Name</td>
						<td>:</td>
						<td><form:input path="name" /></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td>:</td>
						<td>
							Male<form:radiobutton path="gender" value="Male" /> 
							female<form:radiobutton path="gender" value="Female" />
						</td>
					</tr>
					<tr>
						<td>Email/Username</td>
						<td>:</td>
						<td><form:input path="email" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td>:</td>
						<td><form:password path="password" /></td>
					</tr>
					<tr>
						<td>Mobile Number</td>
						<td>:</td>
						<td><form:input path="mobileNumber" /></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td><input type="submit" style="margin-top: 20px;"/></td>
					</tr>
				</table>
			</form:form>
		</div>

		<%-- <div id="user_registration">
			<h3>User Register</h3>
			<form name="user_registration_form" action="user_registration"
				method="post">
				<input type="hidden" name="action" value="user_registration">

				<label>First Name</label> <input type="text" name="first_name"><br>

				<label>Last Name</label> <input type="text" name="last_name"><br>

				<label>Gender</label> <input type="radio" name="gender" value="Male">
				MALE <input type="radio" name="gender" value="Female">
				FEMALE<br> <label>Email</label> <input type="email"
					name="email"><br> <label>Password</label> <input
					type="password" name="password"><br> <label>Confirm
					Password</label> <input type="password" name="confirm_password"><br>

				<label>Mobile Number</label> <input type="text" name="mobile_number"><br>

				<input type="submit" value="register">
			</form>
		</div> --%>

		<div id="restaurant_registration" style="margin-top: 20px;">
			<h3>Restaurant Register</h3>
			 <%-- <form name="restaurant_registration_form" method="post"
				action="restaurant_registrations" enctype="multipart/form-data">
				<input type="hidden" name="action" value="restaurant_registration">
				<label>Name</label><input type="text" name="name"> <label>Government
					Registration ID</label><input type="text" name="govt_registration_id">
				<label>Restaurant Logo</label><input type="file"
					name="registration_logo"> <label>Password</label><input
					type="password" name="password"> <label>Confirm
					Password</label><input type="password" name="confirm_password"> <input
					type="submit" value="register">
			</form> --%> 
			
			 <form:form action="restaurant_registration_form" modelAttribute="restaurant"
				method="POST" enctype="multipart/form-data">
				<table>
					<tr>
						<td>Name</td>
						<td>:</td>
						<td><form:input path="name"/></td>	
					</tr>
					<tr>
						<td>Registration ID</td>
						<td>:</td>
						<td><form:input path="govtRegistrationtId"/></td>	
					</tr>
					<tr>
						<td>Password</td>
						<td>:</td>
						<td><form:input path="password"/></td>	
					</tr>
					<%-- <tr>
						<td>Confirm Password</td>
						<td>:</td>
						<td><form:input path="conformPassword"/></td>	
					</tr> --%>
					<tr>
						<td>Restaurant Logo</td>
						<td>:</td>
						<td><input type="file" name="registration_logo"></td>	
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td><input type="submit" value="submit" style="margin-top: 20px;"/></td>
					</tr>
				</table>
			</form:form> 
		</div>



		<div class="footer"></div>

	</div>


</body>
</html>