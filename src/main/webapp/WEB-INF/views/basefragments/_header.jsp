<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Estudar este cara -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<html>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
  <div style="float: left">
     <h1>My Site</h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
 
  	<div class="five columns right">
			
			<sec:authorize access="isAnonymous()">
			
			<form style="margin-top: 1.0em;" action="<c:url value="/j_spring_security_check"/>" method="post">
				<div class="row">
					<input type="text" name="j_username" placeholder="Usu&aacute;rio" class="three columns right"/>
					<input type="password" name="j_password" placeholder="Senha" class="three columns right"/>
					<input type="submit" value="Entrar" class="tiny button success" class="three columns right"/>&nbsp;
				</div>
			</form>
			
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				Bem vindo(a) <sec:authentication property="principal"/> - <a href="<c:url value="/j_spring_security_logout"/>">Sair</a>
			</sec:authorize>
			
		</div>
  </div>
 
</div>