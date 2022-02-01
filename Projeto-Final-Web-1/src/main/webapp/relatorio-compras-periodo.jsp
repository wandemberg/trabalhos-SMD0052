<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="ecommerce.modelo.ItemRelatorioClientePeriodo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="resources/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="resources/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="resources/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="resources/images/ico/apple-touch-icon-57-precomposed.png">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li><a href="Inicio">Home</a></li>
			<li>Relatórios gerenciais</li>
			<li class="active">Total de compras por cliente por período</li>
		</ol>
	</div>
	<h2>Total de compras por cliente por período - Relatórios
		gerenciais - Loja Virtual</h2>


	<% if (request.getAttribute("mensagem") != null) { %>
	<hr />
	<div class="nav navbar-nav"><%= request.getAttribute("mensagem") %></div>
	<hr />

	<% } %>

	<form action="totalComprasClientePeriodo" method="post">
		<div class="container">
			<label for="periodoInicio"><b>Data Início do período</b></label> <input
				type="date" placeholder="Digite o início do perído"
				name="periodoInicio"> <br> <label for="periodoFim"><b>Data
					Final do período</b></label> <input type="date"
				placeholder="Digite o fim do perído" name="periodoFim">

			<div id="error-nwl"></div>
			<br>

			<div>
				<button type="submit">Pesquisar</button>
				<button onclick="window.location.replace('Inicio');" type="button"
					class="cancelbtn">Cancelar</button>
			</div>
		</div>
	</form>

	<section id="cart_items">
		<div class="container">

			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Id Cliente</td>
							<td class="image">Nome Cliente</td>
							<td class="description">Quantidade Compras</td>

						</tr>
					</thead>
					<tbody>

						<%
				            List<ItemRelatorioClientePeriodo> produtosEncontados = (List<ItemRelatorioClientePeriodo>) request.getAttribute("vendasEncontradas");
				            if (produtosEncontados == null || produtosEncontados.size() == 0) {
				        %>
						<div>Não existem vendas com os dados informados!</div>
						<br>
						<%
					        } else {
					            double total = 0;
					            for (int i = 0; i < produtosEncontados.size(); i++) {
					            	ItemRelatorioClientePeriodo produtoEncontrado = produtosEncontados.get(i);
				        %>

						<tr>
							<td class="cart_price">
								<p><%= produtoEncontrado.getId()%></p>
							</td>
							<td class="cart_description">
								<p><%= produtoEncontrado.getNome()%></p>
							</td>
							<td class="cart_description">
								<p>	<%= produtoEncontrado.getQuantidadeCompras()%></p>
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