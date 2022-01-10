package ecommerce.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.UsuarioDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de usuarios
 * 
 */
@SuppressWarnings("serial")
public class AtualizarUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	   int codigo = Integer.parseInt(request.getParameter("codigo"));
    	   Boolean administrador = Boolean.parseBoolean(request.getParameter("administrador"));
           String nome = request.getParameter("nome");
           String endereco = request.getParameter("endereco");
           String email = request.getParameter("email");
           String login = request.getParameter("login");
           String senha = request.getParameter("senha");
           
           UsuarioDAO usuarioDAO = new UsuarioDAO();
           String mensagem = null;
           boolean atualizou = true;
           try {
        	   if (request.getParameter("atualizar") != null){
                 	 usuarioDAO.atualizar(codigo, administrador,endereco,nome,email,login,senha,true);
                     mensagem = "Atualização realizada com sucesso!";
        	 }
        	else if (request.getParameter("apagar") != null){
              	 usuarioDAO.desativar(codigo);
                 mensagem = "Exclusão realizada com sucesso!";
                 RequestDispatcher requestDispatcher = request.getRequestDispatcher("Logout");
        	     requestDispatcher.forward(request, response);
                 return;
                 }


   		} catch (Exception e) {
               mensagem = e.getMessage();
               e.printStackTrace();
               atualizou = false;
   		}

           request.setAttribute("mensagem", mensagem);

           if (atualizou) {
   	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Inicio");
   	        requestDispatcher.forward(request, response);
           } else {
           	RequestDispatcher requestDispatcher = request.getRequestDispatcher("dados-pessoais.jsp");
   	        requestDispatcher.forward(request, response);
           }
    	
    }

}
