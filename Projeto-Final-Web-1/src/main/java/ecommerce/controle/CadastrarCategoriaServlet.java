package ecommerce.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.CategoriaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de categorias
 * 
 */
public class CadastrarCategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	  //String nome = request.getParameter("descricao");
          int codigo = Integer.parseInt(request.getParameter("codigo"));
          String descricao = request.getParameter("descricao");
         
          CategoriaDAO categoriaDAO = new CategoriaDAO();
          String mensagem = null;
          boolean inseriu = true;
          try {
          	 categoriaDAO.inserir(codigo, descricao);
               mensagem = "Cadastro realizado com sucesso!";
  		} catch (Exception e) {
              mensagem = e.getMessage();
              e.printStackTrace();
              inseriu = false;
  		}

          request.setAttribute("mensagem", mensagem);

          if (inseriu) {
  	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("categorias.jsp");
  	        requestDispatcher.forward(request, response);
          } else {
          	RequestDispatcher requestDispatcher = request.getRequestDispatcher("categorias-cadastrar.jsp");
  	        requestDispatcher.forward(request, response);
          }
      
    }

}
