<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<td>Référence</td>
		<td>Libellé</td>
		<td>Catégorie</td>
		<td>Prix</td>
		<td>Visuel</td>
		<td></td>
		<td></td>
	</thead>
<%  Collection<Pizza> pizzas = (Collection<Pizza>) request.getAttribute("listePizzas");
	for(Pizza pizza : pizzas) {
%>
	<tr>
		<td><%= pizza.getCode() %></td>
		<td><%= pizza.getNom() %></td>
		<td><%= pizza.getCategorie().toString() %></td>
		<td><%= pizza.getPrix() %></td>
		<td><%= pizza.getUrlImage() %></td>
		<td><form action="<%=request.getContextPath()%>/pizzas/edite">
                        <input type="hidden" name="code" value="<%=pizza.getCode()%>"></input>
                        <input type="submit" value="Editer" class="btn btn-default"></input>
                    </form>
                </td>
                <td>
                    <form action="<%=request.getContextPath()%>/pizzas/supprime"
                        method="post">
                        <input type="hidden" name="code" value="<%=pizza.getCode()%>"></input>
                        <input type="submit" value="Supprimer" class="btn btn-default"></input>
                    </form></td>
		<!-- <td><a href="<%= request.getContextPath() %>/pizzas/edite?code=<%= pizza.getCode() %>">Editer</a></td> -->
		<!-- <td><a href="<%= request.getContextPath() %>/pizzas/supprime?code=<%= pizza.getCode() %>">Supprimer</a></td> -->
	</tr>
<%	} %>
</table>

</body>
</html>