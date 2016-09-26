<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Edition pizza</title>
</head>
<body>

<% Pizza pizza = (Pizza) request.getAttribute("pizza"); %>

<h2>Mise à jour de la pizza : <%= pizza.getNom() %></h2>

<form class="form-horizontal" method="post" action="<%= request.getContextPath() %>/pizzas/edite?ancienCode=<%= pizza.getCode() %>">
<fieldset>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="code">Reference</label>  
  <div class="col-md-4">
  <input id="code" name="code" type="text" placeholder="Code" class="form-control input-md" required="" value="<%= pizza.getCode() %>">
  <span class="help-block">Reference à 3 lettres</span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nom">Libelle</label>  
  <div class="col-md-4">
  <input id="nom" name="nom" type="text" placeholder="Nom" class="form-control input-md" required="" value="<%= pizza.getNom() %>">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="categorie">Categorie</label>  
  <div class="col-md-4">
  <input id="categorie" name="categorie" type="text" placeholder="Categorie" class="form-control input-md" required="" value="<%= pizza.getCategorie().toString() %>">
  <span class="help-block">VIANDE / POISSON / SANS_VIANDE</span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="prix">Prix</label>  
  <div class="col-md-4">
  <input id="prix" name="prix" type="text" placeholder="Prix" class="form-control input-md" required="" value="<%= pizza.getPrix() %>">
    
  </div>
</div>

<!-- File Button --> 
<div class="form-group">
  <label class="col-md-4 control-label" for="url">Image</label>
  <div class="col-md-4">
    <input id="url" name="url" class="input-file" type="file" value="<%= pizza.getUrlImage() %>">
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="valider"></label>
  <div class="col-md-4">
    <button id="valider" name="valider" type="submit" class="btn btn-success">Valider</button>
  </div>
</div>

</fieldset>
</form>

</body>
</html>