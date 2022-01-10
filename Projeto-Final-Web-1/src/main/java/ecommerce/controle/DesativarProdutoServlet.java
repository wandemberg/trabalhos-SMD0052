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
import ecommerce.modelo.Produto;
import ecommerce.modelo.ProdutoDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de produtos
 * 
 */
public class DesativarProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String mensagem = null;

        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
        	produtoDAO.desativar(codigo);
       	 	mensagem = "Remoção realizada com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
            mensagem = "Não foi possível remover este produto";
		}
        
        request.setAttribute("mensagem", mensagem);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtos.jsp");
        requestDispatcher.forward(request, response);
    }

}
