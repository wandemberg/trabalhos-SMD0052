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
import ecommerce.modelo.Produto;
import ecommerce.modelo.ProdutoDAO;
import ecommerce.modelo.Venda;
import ecommerce.modelo.VendaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de produtos
 * 
 */
@SuppressWarnings("serial")
public class ProdutosFaltandoEstoqueServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtosEncontrados = null;
        try {
        	produtosEncontrados =produtoDAO.obterProdutosFaltandoEmEstoque();
		} catch (Exception e) {
			e.printStackTrace();
		}       
        
        request.setAttribute("produtosEncontrados", produtosEncontrados);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("relatorio-produtos-faltando-estoque.jsp");
        requestDispatcher.forward(request, response);
   	
    }

}
