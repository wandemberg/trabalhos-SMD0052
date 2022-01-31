package ecommerce.controle;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecommerce.modelo.CarrinhoCompra;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de adicionar um produto
 * no carrinho de compras
 */
public class AdicionarProdutoCarrinhoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        int produtoId = Integer.parseInt(request.getParameter("produtoId"));
        /* processamento */
        Cookie cookie = CarrinhoCompra.obterCookie(request);
        String novoValor = CarrinhoCompra.adicionarItem(produtoId, 1, cookie.getValue());
        cookie.setValue(novoValor);
        /* saída */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("carrinhoCompra");
        requestDispatcher.forward(request, response);
    }

}
