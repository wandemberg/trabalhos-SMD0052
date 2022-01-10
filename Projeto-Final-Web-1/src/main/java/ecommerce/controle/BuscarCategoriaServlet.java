package ecommerce.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecommerce.modelo.CarrinhoCompra;
import ecommerce.modelo.CarrinhoCompraItem;
import ecommerce.modelo.Categoria;
import ecommerce.modelo.CategoriaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de categorias
 * 
 */
public class BuscarCategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoriaAtualizar = null;
        try {
        	categoriaAtualizar =categoriaDAO.obter(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        request.setAttribute("categoriaAtualizar", categoriaAtualizar);
        request.setAttribute("codigo", codigo);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("categorias-atualizar.jsp");
        requestDispatcher.forward(request, response);
    }

}
