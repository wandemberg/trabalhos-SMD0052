package ecommerce.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.Categoria;
import ecommerce.modelo.CategoriaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de categorias
 * 
 */
public class CategoriaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String nome = request.getParameter("descricao");
        String codigo = request.getParameter("codigo");
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categoriasEncontradas = null;
        try {
        	categoriasEncontradas =categoriaDAO.obterCategoriasPesquisadas(nome, codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        request.setAttribute("categoriasEncontradas", categoriasEncontradas);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("categorias.jsp");
        requestDispatcher.forward(request, response);
    }

}
