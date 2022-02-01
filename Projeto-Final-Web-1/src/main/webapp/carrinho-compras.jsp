<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
	request.setAttribute("usuario", usuario);
	if (usuario != null) {
		request.setAttribute("idUsuario", usuario.getId());
	}
	
%>
<%@page import="ecommerce.modelo.Usuario"%>
<%@page import="ecommerce.modelo.CarrinhoCompraItem"%>
<%@page import="java.util.List"%>
<%@page import="ecommerce.modelo.Produto"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrinho de compras</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/price-range.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
	<link href="resources/css/main.css" rel="stylesheet">
	<link href="resources/css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ico/apple-touch-icon-57-precomposed.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
</head>
<body>
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6 ">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<%if (usuario != null) { %>
									<li><a href="compras.jsp"> Bem-vindo, <%= usuario.getNome()%>!</a></li>
									<li><a href="compras.jsp"> <%= usuario.getEmail()%></a></li>
								<%} %>
							</ul>
						</div>
					</div>
					
				</div>
			</div>
		</div><!--/header_top-->
		
			<% if (request.getAttribute("mensagem") != null) { %>
        <hr/>
        <div><%= request.getAttribute("mensagem") %></div>
        <% } %>
 <hr/>
<form action="FinalizarCompraDoCarrinho" method="post">
<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="Inicio">Home</a></li>
				  <li class="active">Carrinho de compras</li>
				</ol>
			</div>
			<% if (usuario == null) { %> 
					<p class="cart_total_price">É necessário realizar o login para finalizar a compra </p> <br>			
			<%} %>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Preço</td>
							<td class="quantity">Quantidade</td>
							<td class="total">Total</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
					
						<%
						String format = "0.00";
						NumberFormat formatter = new DecimalFormat(format);
						String newDVal;
				            List<CarrinhoCompraItem> carrinhoCompraItens = (List<CarrinhoCompraItem>) request.getAttribute("carrinhoCompraItens");
				            if (carrinhoCompraItens == null || carrinhoCompraItens.size() == 0) {
				        %>
				        <div>Não existem produtos no seu carrinho compras</div>
				        <%
					        } else {
					            double total = 0;
					            for (int i = 0; i < carrinhoCompraItens.size(); i++) {
					                CarrinhoCompraItem carrinhoCompraItem = carrinhoCompraItens.get(i);
				        %>

        						<%
        						 newDVal = formatter.format(carrinhoCompraItem.getProduto().getPreco());        
        						 %>
				        <tr>
							<td class="cart_product">
								<a href=""><img src="images/cart/one.png" alt=""></a>
							</td>
							<td class="cart_description">
								<h4><a href=""><%= carrinhoCompraItem.getProduto().getDescricao()%></a></h4>
								<p>ID: <%= carrinhoCompraItem.getProduto().getId()%></p>
							</td>
							<td class="cart_price">

								<p>R$ <%= newDVal %></p>
							</td>
							<td class="cart_quantity">
								<div class="cart_quantity_button">
									<a class="cart_quantity_up" href="AdicionarProdutoCarrinho?produtoId=<%= carrinhoCompraItem.getProduto().getId()%>" > + </a>
									<input class="cart_quantity_input" type="text" name="quantity" value="<%= carrinhoCompraItem.getQuantidade()%>" autocomplete="off" size="2" disabled="true">
									<a class="cart_quantity_down" href="DecrementarProdutoCarrinho?produtoId=<%= carrinhoCompraItem.getProduto().getId()%>"> - </a>
								</div>
							</td>
							<td class="cart_total">
								<p class="cart_total_price">R$ 
								<%  newDVal = formatter.format(carrinhoCompraItem.getProduto().getPreco() * carrinhoCompraItem.getQuantidade()); %> 
								<%= newDVal %></p>
							</td>
							<td class="cart_delete">
								<a class="cart_quantity_delete" href="RemoverProdutoCarrinhoCompra?produtoId=<%= carrinhoCompraItem.getProduto().getId()%>"><i class="fa fa-times"></i></a>
							</td>
							 <% total += carrinhoCompraItem.getProduto().getPreco() * carrinhoCompraItem.getQuantidade(); %>
						</tr>
				        				    
				        <%

				            }
				        %>
				        <tr>
					        <td></td>
					      	<td></td>
					        <td></td>
				        	<td>
					        	<p class="cart_total_price">Total </p>
					        </td>
					        <td>
					        	<p class="cart_total_price"> R$ 
					        	<%  newDVal = formatter.format(total); %> 
								<%= newDVal %></p>
					        	</p>
					        </td>
				        </tr>
				        <%
				            }
				        %>
 
					</tbody>
				</table>
			</div>
			<div class="btncontainer"> 
				<%if (usuario != null && !carrinhoCompraItens.isEmpty()) { %>			       
					<button type="submit" >Comprar</button>
					<input type="hidden" name="idUsuarioA" value="<%= usuario.getId()%>" />	  
					
				<% } %> 								  									  	
				<button onclick="window.location.replace('Inicio');" type="button" class="cancelbtn">Voltar</button>
			</div>

		</div>
	</section> <!--/#cart_items-->
</form>
</body>
</html>