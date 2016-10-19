<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Estudar este cara -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>Person Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>

<h1>
	Add a Person
</h1>

<c:url var="addAction" value="/membro/person/add" ></c:url>

<form:form action="${addAction}" commandName="person">
<table>
	<c:if test="${!empty person.name}">
	<tr>
		<td>
			<form:label path="id">  <!--  path é o nome do atributo que será enviado. Poderia também ser path="person.id"-->
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
			<form:errors path="name" cssStyle="color:red" cssClass=""/>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="country">
				<spring:message text="Country"/>
			</form:label>
		</td>
		<td>
			<form:input path="country" />
		</td>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td> -->
<%-- 			<form:label path="password"> --%>
<%-- 				<spring:message text="Password"/> --%>
<%-- 			</form:label> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<form:input path="password" /> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td> -->
<%-- 			<form:label path="authority"> --%>
<%-- 				<spring:message text="authority"/> --%>
<%-- 			</form:label> --%>
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 			<form:input path="authority" /> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td colspan="2">
			<c:if test="${!empty person.name}">
				<input type="submit"
					value="<spring:message text="Edit Person"/>" />
			</c:if>
			<c:if test="${empty person.name}">
				<input type="submit"
					value="<spring:message text="Add Person"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Persons List</h3>
<c:if test="${!empty listPersons}">
	<table class="tg">
	<tr>
		<th width="80">Person ID</th>
		<th width="120">Person Name</th>
		<th width="120">Person Country</th>
<!-- 		<th width="120">Password</th> -->
<!-- 		<th width="120">Authority</th> -->
		<th width="60">Edit</th>
		<sec:authorize access="hasRole('ROLE_MEMBRO')"> <th width="60">Delete</th></sec:authorize>
	</tr>
	<c:forEach items="${listPersons}" var="person">
		<tr>
			<td>${person.id}</td>
			<td>${person.name}</td>
			<td>${person.country}</td>
<%-- 			<td>${person.password}</td> --%>
<%-- 			<td>${person.authority.name}</td> --%>
			<td><a href="<c:url value='/membro/edit/${person.id}' />" >Edit</a></td>
			<sec:authorize access="hasRole('ROLE_ADMIN')"> <td><a href="<c:url value='/membro/remove/${person.id}' />" >Delete</a></td></sec:authorize>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>