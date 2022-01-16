package ecommerce.controle;

import java.io.IOException;
import java.util.ArrayList;
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
import ecommerce.modelo.Produto;
import ecommerce.modelo.ProdutoDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de produtos
 * 
 */
public class BuscarProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produtoAtualizar = null;
        Integer idCategoria = null;
        try {
        	produtoAtualizar =produtoDAO.obter(codigo, false);
        	idCategoria = produtoDAO.categoriaProduto(codigo, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        request.setAttribute("produtoAtualizar", produtoAtualizar);
        request.setAttribute("codigo", codigo);
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categoriasDisponiveis = null;
        
        try {
        	//Categoria categoria = categoriaDAO.obter(idCategoria, false);
    		request.setAttribute("selectedCat", idCategoria);

            categoriasDisponiveis = categoriaDAO.obterCategoriasEmEstoque();
        } catch (Exception ex) {
            categoriasDisponiveis = new ArrayList<>();
        }
        request.setAttribute("categoriasDisponiveis", categoriasDisponiveis);
        

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtos-atualizar.jsp");
        requestDispatcher.forward(request, response);
    }

}
