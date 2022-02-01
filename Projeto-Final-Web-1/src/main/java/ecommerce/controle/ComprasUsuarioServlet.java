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
import ecommerce.modelo.Usuario;
import ecommerce.modelo.UsuarioDAO;
import ecommerce.modelo.Venda;
import ecommerce.modelo.VendaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de produtos
 * 
 */
public class ComprasUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       /*
   	  	String login = request.getParameter("login");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
    	try {
			 usuario = usuarioDAO.obter(login);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/
        
        String idUsuario = request.getParameter("idUsuario");
        request.getParameter("idUsuarioAtr");
        request.getAttribute("idUsuario");
        request.getAttribute("idUsuarioAtr");

        int idUsuarioInt = Integer.parseInt(idUsuario);
        
        //idUsuario = (String) request.getAttribute("idUsuario");
        
        /*  String codigo = request.getParameter("codigo");
        
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
*/        
        VendaDAO vendaDAO = new VendaDAO();
        List<Venda> vendasEncontradas = null;
        
        try {
			vendasEncontradas = vendaDAO.obterTodasVendasUsuario(idUsuarioInt, false);
		} catch (Exception e) {
			vendasEncontradas = null;			
			e.printStackTrace();
		}
        
        request.setAttribute("vendasEncontradas", vendasEncontradas);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("compras-usuario.jsp");
        requestDispatcher.forward(request, response);
    }

}
