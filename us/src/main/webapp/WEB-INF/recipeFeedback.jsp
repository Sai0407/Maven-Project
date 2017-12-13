<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="recipe_feedback">
		<h3>Recipe Feedback</h3>

		<form name="recipe_feedback_form" method="post" action="new_recipe_feedback">
			<table>
				<tr>
					<td>Restaurant</td>
					<td>:</td>
					<td><h4>${restaurant.name}</h4></td>
				</tr>
				<tr><td><input type="hidden" name="branch_id" value="${branch.id}"></td></tr>
				<tr><td><input type="hidden" name="recipe_id" value="${recipe.id}"></td></tr>
				<tr>
					<td>Branch</td>
					<td>:</td>
					<td><h4>${branch.location}</h4></td>
				</tr>
				<tr>
					<td>Recipe</td>
					<td>:</td>
					<td><h4>${recipe.name}</h4></td>
				</tr>
				<tr>
					<td><input type="hidden" name="branch_id" value="${branch.id}"></td>
				</tr>
				<tr>
					<td>Comments</td>
					<td>:</td>
					<td><textarea rows="3" cols="45" name="comments"></textarea></td>
				</tr>
				<tr>
					<td>Rating</td>
					<td>:</td>
					<td><select name="rating">
							<option value=1>1</option>
							<option value=2>2</option>
							<option value=3>3</option>
							<option value=4>4</option>
							<option value=5>5</option>
					</select></td>
				</tr>
				<tr>
					<td>Visited Date</td>
					<td>:</td>
					<td><input type="date" name="visited_Date"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><input type="submit" value="submit"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>