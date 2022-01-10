<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
	<script type="text/javascript" src="resources/js/script.js"></script>
</head>
<body>

<h2>Login - Loja Virtual</h2>

<form action="LoginCliente" method="post">
  <div class="imgcontainer">
    <img src="resources/images/e-commerce-e-loja-virtual.png" alt="Avatar" class="avatar">
  </div>

	<% if (request.getAttribute("mensagem") != null) { %>
        <hr/>
        <div><%= request.getAttribute("mensagem") %></div>
        <% } %>
       <hr/>

  <div class="container">
    <label for="uname"><b>Usuário</b></label>
    <input type="text" placeholder="Digite o usuário" name="uname" required>

    <label for="psw"><b>Senha</b></label>
    <input type="password" placeholder="Digite a senha" onblur="checkPass(); " name="psw" id="psw" required>
    <div id="error-nwl"></div>

	<div class="btncontainer">        
    <button type="submit" >Login</button>
    <button onclick="window.location.replace('Inicio');" type="button" class="cancelbtn">Cancelar</button>
    </div>    
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <span class="psw">Cliente Novo? <a href="cadastrar-usuario.jsp">Cadastrar</a></span>    
  </div>
</form>

</body>
</html>