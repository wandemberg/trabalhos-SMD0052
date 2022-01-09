package smdecommerce.produto.modelo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * Classe que implementa a ação de mostrar um produto
 */
public class MostrarProdutoFotoServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        int id = Integer.parseInt(request.getParameter("id"));
        /* processamento */
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = null;
        try {
            produto = produtoDAO.obter(id);
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }
        request.setAttribute("produto", produto);
        /* saída */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("uploadProduto.jsp");
        requestDispatcher.forward(request, response);
    }

}
