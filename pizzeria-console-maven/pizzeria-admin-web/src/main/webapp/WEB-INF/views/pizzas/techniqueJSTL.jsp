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
<title>Technique</title>
</head>
<body>

<h2>Pizzas créées</h2>
<p>${pizzaCrees}</p>
<table class="table-hover">
	<thead>
		<td class= "col-md-1">Référence</td>
		<td class= "col-md-1">Libellé</td>
		<td class= "col-md-1">Catégorie</td>
		<td class= "col-md-1">Prix</td>
		<td class= "col-md-1">Visuel</td>
		<td class= "col-md-1">Date</td>
	</thead>
	<c:forEach var="pizzaC" items="${pizzaCrees}">
	<tr>
		<td>${pizzaC.pizza.code}</td>
		<td>${pizzaC.pizza.nom}</td>
		<td>${pizzaC.pizza.categorie.name}</td>
		<td>${pizzaC.pizza.prix}</td>
		<td><img src="../Pizzas/${pizzaC.pizza.urlImage}" alt="${pizzaC.pizza.urlImage}" height="100px"></td>
		<td>${pizzaC.dateH.toString()}</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>