<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index Page/Imports Swagger</title>

<style>
table,th {

	border: 1px solid black;width: 100%; white-space: nowrap; table-layout: fixed;
	
	
}

td {
border: 1px solid black;overflow: hidden; text-overflow: ellipsis;
	
}


</style>

</head>
<body>
	<%-- Swagger Document Create Add/Edit logic --%>
	<c:if test="${requestScope.error ne null}">
		<strong style="color: red;"><c:out
				value="${requestScope.error}"></c:out></strong>
	</c:if>
	<c:if test="${requestScope.success ne null}">
		<strong style="color: green;"><c:out
				value="${requestScope.success}"></c:out></strong>
	</c:if>
	<c:url value="/addswagger" var="addURL"></c:url>
	<c:url value="/editSwqgger" var="editSwagger"></c:url>

	<%-- Edit Swagger --%>
	<c:if test="${requestScope.swagger ne null}">
		<form action='<c:out value="${editSwagger}"></c:out>' method="post">
			ID: <input type="text" value="${requestScope.swagger.id}"
				readonly="readonly" name="id"><br> SwaggerName: <input
				type="text" value="${requestScope.swagger.swaggername}" name="swaggername"><br>
			Swagger: <input type="text" value="${requestScope.swagger.swagger}"
				name="swagger"><br> <input type="submit"
				value="Edit Swagger">
		</form>
	</c:if>
 
	<%-- Add Swagger --%>
	<c:if test="${requestScope.swagger eq null}">
		<form action='<c:out value="${addURL}"></c:out>' method="post">
			SwaggerName: <input type="text" name="swaggername"><br> SwaggerURL: <input
				type="text" name="swaggerurl"><br> <input type="submit"
				value="Create Swagger">
		</form>
	</c:if>
	
	<%-- Swagger List Logic --%>
	<c:if test="${not empty requestScope.swaggers}">
		<table>
			<tbody>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Swagger</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${requestScope.swaggers}" var="swaggers">
					<c:url value="/editSwagger" var="editSwagger">
						<c:param name="id" value="${swaggers.id}"></c:param>
					</c:url>
					<c:url value="/deleteSwagger" var="deleteSwagger">
						<c:param name="id" value="${swaggers.id}"></c:param>
					</c:url>
					<tr>
					
					<td><c:out value="${swaggers.id}"></c:out></td>
					<td><c:out value="test"></c:out></td>
						<td><c:out value="${swaggers.swagger}"></c:out></td>
						
						
						<td><a
							href='<c:out value="${editSwagger}" escapeXml="true"></c:out>'>Edit</a></td>
						<td><a
							href='<c:out value="${deleteSwagger}" escapeXml="true"></c:out>'>Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

</body>
</html>