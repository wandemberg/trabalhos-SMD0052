package ecommerce.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.Venda;
import ecommerce.modelo.VendaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de buscar as compras de todos os usuários
 * 
 */
@SuppressWarnings("serial")
public class ComprasUsuarioAdminServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            
        VendaDAO vendaDAO = new VendaDAO();
        List<Venda> vendasEncontradas = null;
        
        try {
			vendasEncontradas = vendaDAO.obterTodasVendas(true);
		} catch (Exception e) {
			vendasEncontradas = null;			
			e.printStackTrace();
		}
        
        request.setAttribute("vendasEncontradas", vendasEncontradas);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("compras-usuario-admin.jsp");
        requestDispatcher.forward(request, response);
    }

}
