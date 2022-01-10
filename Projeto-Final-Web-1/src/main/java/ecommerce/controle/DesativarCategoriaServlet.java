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
public class DesativarCategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String mensagem = null;

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
        	categoriaDAO.desativar(codigo);
       	 	mensagem = "Remoção realizada com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
            mensagem = "Não foi possível remover este categoria";
		}
        
        request.setAttribute("mensagem", mensagem);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("categorias.jsp");
        requestDispatcher.forward(request, response);
    }

}
