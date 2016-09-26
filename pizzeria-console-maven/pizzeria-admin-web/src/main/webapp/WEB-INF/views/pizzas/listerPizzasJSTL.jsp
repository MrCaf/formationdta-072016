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
<title>Liste pizzas</title>
</head>
<body>

<h2>Carte des pizzas</h2>

<table class="table-hover">
	<thead>
		<td class= "col-md-1">Référence</td>
		<td class= "col-md-1">Libellé</td>
		<td class= "col-md-1">Catégorie</td>
		<td class= "col-md-1">Prix</td>
		<td class= "col-md-1">Visuel</td>
		<td class= "col-md-1"></td>
		<td class= "col-md-1"></td>
	</thead>
	<c:forEach var="pizza" items="${listePizzas}">
	<tr>
		<td>${pizza.code}</td>
		<td>${pizza.nom}</td>
		<td>${pizza.categorie.name}</td>
		<td>${pizza.prix}</td>
		<td><img src="../Pizzas/${pizza.urlImage}" alt="${pizza.urlImage}" height="100px"></td>
		<td><form action="<c:url value='/pizzas/edite?code=${pizza.code}' />">
        		<input type="hidden" name="code" value="${pizza.code}"></input>
            	<input type="submit" value="Editer" class="btn btn-default"></input>
            </form>
        </td>
        <td><form action="<c:url value='/pizzas/supprime?code=${pizza.code}' />" method="post">
        		<input type="hidden" name="code" value="${pizza.code}>"></input>
            	<input type="submit" value="Supprimer" class="btn btn-default"></input>
            </form>
        </td>
	</tr>
	</c:forEach>
</table>

</body>
</html>