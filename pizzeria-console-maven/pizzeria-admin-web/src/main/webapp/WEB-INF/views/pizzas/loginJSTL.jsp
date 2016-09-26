<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Login</title>
</head>
<body>

<h2>Identification utilisateur</h2>

<form class="form-horizontal" method="post" action="<c:url value='/login'/>">
<fieldset>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="user">E-mail</label>  
  <div class="col-md-4">
  <input id="user" name="user" type="text" placeholder="e-mail" class="form-control input-md" required="" value="admin@pizzeria.fr">
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password</label>
  <div class="col-md-4">
    <input id="password" name="password" type="password" placeholder="password" class="form-control input-md" values="admin">
    
  </div>
</div>

<input type="hidden" name="url" value="${url}"/>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="btnValid"></label>
  <div class="col-md-4">
    <button id="btnValid" name="btnValid" type="submit" class="btn btn-success">Connexion</button>
  </div>
</div>

</fieldset>
</form>

</body>
</html>