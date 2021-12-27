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

/**
 *
 *
 * Servlet que implementa a camada de controle da ação do carrinho de compras
 * 
 */
public class CarrinhoCompraServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(CarrinhoCompra.CHAVE_COOKIE_CARRINHO_COMPRA)) {
                cookie = cookies[i];
                break;
            }
        }
        if (cookie == null) {
            cookie = new Cookie(CarrinhoCompra.CHAVE_COOKIE_CARRINHO_COMPRA, "");
        }
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        
        List<CarrinhoCompraItem> carrinhoCompraItens = CarrinhoCompra.obterCarrinhoCompra(cookie.getValue());
        request.setAttribute("carrinhoCompraItens", carrinhoCompraItens);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("carrinho-compras.jsp");
        requestDispatcher.forward(request, response);
    }

}
