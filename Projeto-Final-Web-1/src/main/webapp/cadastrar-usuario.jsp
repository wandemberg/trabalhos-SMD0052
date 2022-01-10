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

<h2>Cadastrar Usuário - Loja Virtual</h2>

<form action="NovoCliente" method="post">
  <div class="container">
    <label for="uname"><b>Nome</b></label>
    <input type="text" placeholder="Digite o nome completo do usuário" name="nome" required>

    <label for="uname"><b>Endereço</b></label>
    <input type="text" placeholder="Digite o endereço do usuário" name="endereco" required>

	<label for="uname"><b>Email</b></label>
    <input type="text" placeholder="Digite o email do usuário" name="email" required>

    <label for="uname"><b>Usuário</b></label>
    <input type="text" placeholder="Digite o usuário" name="login" required>

    <label for="psw"><b>Senha</b></label>
    <input type="password" placeholder="Digite a senha" onblur="checkPass(); " name="senha" id="psw" required>
    <div id="error-nwl"></div>
     
    <div>   
	    <button type="submit" >Cadastrar</button>
	    <button onclick="window.location.replace('Inicio');" type="button" class="cancelbtn">Cancelar</button>
    </div>
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <span class="psw">Cliente já cadastrado? <a href="login.jsp">Login</a></span>    
  </div>
</form>
</body>
</html>