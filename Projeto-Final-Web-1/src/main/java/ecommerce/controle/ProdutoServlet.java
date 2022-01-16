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
public class ProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String nome = request.getParameter("descricao");
        String codigo = request.getParameter("codigo");
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtosEncontrados = null;
        try {
        	produtosEncontrados =produtoDAO.obterProdutosPesquisados(nome, codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        for (Produto produto : produtosEncontrados) {

            try {
            	Integer idCategoria = produtoDAO.categoriaProduto(produto.getId(), false);
            	Categoria categoriaEncontrada = null;
            	if (idCategoria!=null) {
            		 categoriaEncontrada = categoriaDAO.obter(idCategoria,false);
            	}
            	produto.setCategoria(categoriaEncontrada);
            } catch (Exception ex) {
            	produto.setCategoria(null);
            }
        }
        
        request.setAttribute("produtosEncontrados", produtosEncontrados);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtos.jsp");
        requestDispatcher.forward(request, response);
    }

}
