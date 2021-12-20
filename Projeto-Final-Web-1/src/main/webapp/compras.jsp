<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
   /* if (usuario == null) {
        request.setAttribute("mensagem", "Você não tem uma sessão válida de usuário do tipo cliente");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    } else {*/
%>
<%@page import="ecommerce.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Lista de produtos comprar</title>
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
    
</head><!--/head-->

<body>
	<header id="header"><!--header-->
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
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-md-4 clearfix">
						<div class="logo pull-left">
							<a href="index.html"><img src="images/home/logo.png" alt="" /></a>
						</div>

					</div>
					<div class="col-md-8 clearfix">
						<div class="shop-menu clearfix pull-right">
							<ul class="nav navbar-nav">
								<%if (usuario == null) { %>
									<li><a href="cadastrar-usuario.jsp"> Crie a sua conta</a></li>																
									<li><a href="login.jsp"> Entre</a></li>
								<%} %>
								<li><a href="carinho-compras.jsp"> Carrinho</a></li>
								<%if (usuario != null) { %>							
									<li><a href="Logout"> Sair</a></li>
								<%} %>
							</ul>
						</div>
						<% if (request.getAttribute("mensagem") != null) { %>
        					<hr/>
        					<div class="nav navbar-nav"><%= request.getAttribute("mensagem") %></div>
        				<% } %>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	

       	<hr/>
	</header>
	
	<section id="advertisement">
		<div class="container">
			<img src="images/shop/advertisement.jpg" alt="" />
		</div>
	</section>
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Categoria</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Esporte
										</a>
									</h4>
								</div>
								<div id="sportswear" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="">Nike </a></li>
											<li><a href="">Adidas </a></li>
											<li><a href="">Puma</a></li>
											<li><a href="">ASICS </a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#mens">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Brinquedo
										</a>
									</h4>
								</div>
								<div id="mens" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="">Barbie</a></li>
											<li><a href="">Carros</a></li>
											<li><a href="">Bolas</a></li>									
										</ul>
									</div>
								</div>
							</div>
							
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#womens">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											Casa
										</a>
									</h4>
								</div>
								<div id="womens" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="">Ventilador</a></li>
											<li><a href="">Forno</a></li>
											<li><a href="">Fogão</a></li>
											<li><a href="">Mesa</a></li>
											<li><a href="">Geladeira</a></li>
										</ul>
									</div>
								</div>
							</div>
						
						</div><!--/category-productsr-->						
						
					</div>
				</div>
				
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Produtos</h2>
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/shop/product12.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/shop/product11.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/shop/product10.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/shop/product9.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
									<img src="images/home/new.png" class="new" alt="" />
								</div>
								
							</div>
						</div>
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/shop/product8.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
									<img src="images/home/sale.png" class="new" alt="" />
								</div>
								
							</div>
						</div>
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/shop/product7.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/home/product6.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/home/product5.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/home/product4.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/home/product3.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						
						
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/home/product2.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="images/home/product1.jpg" alt="" />
										<h2>R$ 25,00</h2>
										<p>Produto</p>
										<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
									</div>
									<div class="product-overlay">
										<div class="overlay-content">
											<h2>R$ 25,00</h2>
											<p>Produto</p>
											<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Adicionar ao carrinho</a>
										</div>
									</div>
								</div>

							</div>
						</div>
						
						<ul class="pagination">
							<li class="active"><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">&raquo;</a></li>
						</ul>
					</div><!--features_items-->
				</div>
			</div>
		</div>
	</section>
	

	

  
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/price-range.js"></script>
    <script src="resources/js/jquery.scrollUp.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/main.js"></script>
</body>
</html>
<%
  //  }
%>