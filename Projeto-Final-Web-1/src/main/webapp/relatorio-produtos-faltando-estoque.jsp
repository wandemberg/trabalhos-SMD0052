<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="ecommerce.modelo.Produto"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
				  <li class="active">Produtos</li>
				</ol>
			</div>
<h2>Produtos - Loja Virtual</h2>

  
	<% if (request.getAttribute("mensagem") != null) { %>
    <hr/>
        <div class="nav navbar-nav"><%= request.getAttribute("mensagem") %></div>
    <hr/>
        
     <% } %>
     	
  
<section id="cart_items">
		<div class="container">

			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">C??digo</td>
							<td class="description">Nome</td>
							<td class="price">Descri????o</td>
							<td class="total">Custo unit??rio</td>						
						</tr>
					</thead>
					<tbody>
					
						<%
						String format = "0.00";
						NumberFormat formatter = new DecimalFormat(format);
				            List<Produto> produtosEncontados = (List<Produto>) request.getAttribute("produtosEncontrados");
				            if (produtosEncontados == null || produtosEncontados.size() == 0) {
				        %>
				        <div>N??o existem produtos faltando no estoque!</div>
				        <br>
				        <%
					        } else {
					            double total = 0;
					            for (int i = 0; i < produtosEncontados.size(); i++) {
					            	Produto produtoEncontrado = produtosEncontados.get(i);
				        %>
				        
				        <tr>
							<td class="cart_price">
								<p><%= produtoEncontrado.getId()%></p>
							</td>							
							<td class="cart_description">
								<h4><a href=""><%= produtoEncontrado.getNome()%></a></h4>
								<p>ID: <%= produtoEncontrado.getId()%></p>
							</td>
							<td class="cart_description">
								<h4><a href=""><%= produtoEncontrado.getDescricao()%></a></h4>
							</td>						
							<td class="cart_price">
								<p>R$ <%= formatter.format(produtoEncontrado.getPreco())%>
								</p>								
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