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

  
	<% if (request.getAttribute("mensagem") != null) { %>
    <hr/>
        <div class="nav navbar-nav"><%= request.getAttribute("mensagem") %></div>
    <hr/>
        
     <% } %>  	
<form action="categoria" method="post">	  
    
  <div class="container">
    <label for="descricao"><b>Nome</b></label>
    <input type="text" placeholder="Digite o nome da categoria" name="descricao" >

    <label for="codigo"><b>Código</b></label>
    <input type="text" pattern="([0-9])+" title="Somente números" placeholder="Digite o código do categoria" name="codigo">
    
	
    <div id="error-nwl"></div>

	<div> 
	    <button type="submit" >Pesquisar</button>
	    <button onclick="window.location.replace('categorias-cadastrar.jsp');" type="button" class="otherbtn">Novo</button>	    
	    <button onclick="window.location.replace('compras.jsp');" type="button" class="cancelbtn">Cancelar</button>	    
	</div>
  </div>
</form>  
<section id="cart_items">
		<div class="container">

			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Código</td>
							<td class="description">Nome</td>
							<td></td>
							<td></td>							
						</tr>
					</thead>
					<tbody>
					
						<%
				            List<Categoria> categoriasEncontadas = (List<Categoria>) request.getAttribute("categoriasEncontradas");
				            if (categoriasEncontadas == null || categoriasEncontadas.size() == 0) {
				        %>
				        <div>Não existem categorias com os dados informados!</div>
				        <br>
				        <%
					        } else {
					            double total = 0;
					            for (int i = 0; i < categoriasEncontadas.size(); i++) {
					            	Categoria categoriaEncontrada = categoriasEncontadas.get(i);
				        %>
				        
				        <tr>
							<td class="cart_price">
								<p><%= categoriaEncontrada.getId()%></p>
							</td>
							<td class="cart_description">
								<h4><a href=""><%= categoriaEncontrada.getDescricao()%></a></h4>
							</td>
							<td>	
								<form action="buscarCategoria" method="post">
								<input type="hidden" name="codigo" value="<%= categoriaEncontrada.getId()%>" />	  
									<button type="submit" class="btn" name="Editar" title="Editar"><i class="fa fa-pencil"></i></button>
								</form>
								</td>
							<td>	
								<form action="removerCategoria" method="post">
									<input type="hidden" name="codigo" value="<%= categoriaEncontrada.getId()%>" />																
									<button class="btn" title="Excluir"><i class="fa fa-trash"></i></button>
								</form>
							</td>
						</tr>
				        				    
				        <%

				            }
				        %>
				       
				        <%
				            }
				        %>
					
						
					</tbody>
				</table>
			</div>
		</div>
	</section>

</body>
</html>