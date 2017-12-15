<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="page">
		<div class="header"></div>
		<div>
			<div id="restaurants">
				<table border="1">
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
											<td><a
												href="branch_feedback?restaurant_id=${restaurant.id}&branch_id=${branch.id}">Add
													FeedBack</a></td>
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
													<c:forEach items="${branch.cuisinesList}" var="cuisine">
														<tr>
															<td>${cuisine.id}</td>
															<td>${cuisine.name}</td>
															<td>${cuisine.country}</td>
															<td>
																<table>
																	<c:forEach items="${cuisine.recipesList}" var="recipe">
																		<tr>
																			<td>${recipe.id}</td>
																			<td>${recipe.name}</td>
																			<td>${recipe.description}</td>
																			<td>${recipe.price}</td>
																			<td><a
																				href="recipe_feedback?recipe_id=${recipe.id}&branch_id=${branch.id}&restaurant_id=${restaurant.id}">Add
																					FeedBack</a></td>
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
	</div>
</body>
</html>