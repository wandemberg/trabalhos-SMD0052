<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="ecommerce.modelo.Categoria"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script type="text/javascript" src="resources/js/script.js"></script>

    <meta name="description" content="">
    <meta name="author" content="">
    
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/price-range.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
	<link href="resources/css/main.css" rel="stylesheet">
	<link href="resources/css/responsive.css" rel="stylesheet">
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ico/apple-touch-icon-57-precomposed.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="Inicio">Home</a></li>
				  <li class="active">Categorias</li>
				</ol>
			</div>
<h2>Categorias - Loja Virtual</h2>

<form action="atualizarCategoria" method="post" >
      
  <div class="container">
  	<% if (request.getAttribute("mensagem") != null) { %>
    	<hr/>
        <div class="nav navbar-nav"><%= request.getAttribute("mensagem") %></div>
            	<hr/>        
     <% } %>
     
     <%
		    Categoria c = (Categoria) request.getAttribute("categoriaAtualizar");
	%>
   
    <label for="codigo"><b>Código</b></label><br>    
    <input type="text" value="<%= c.getId() %>"pattern="([0-9])+" title="Somente números" placeholder="Digite o código do categoria" name="codigo" required>
    
	<br>
   	<label for="descricao"><b>Descrição</b></label>
    <input type="text" value="<%= c.getDescricao() %>" placeholder="Digite o descrição da categoria" name="descricao" required>

        <br>
        <br>
    
    <div id="error-nwl"></div>



	<div>   
		<% 
			request.setAttribute("codigo", c.getId());
		%>
	    <button type="submit" >Atualizar</button>
	    <button onclick="window.location.replace('Inicio');" type="button" class="cancelbtn">Cancelar</button>
	</div>
  </div>
  

</form>
</body>
</html>