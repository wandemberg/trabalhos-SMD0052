package ecommerce.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.VendaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de remover a compra do usuário
 * 
 */
@SuppressWarnings("serial")
public class RemoverCompraServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        int codigo = Integer.parseInt(request.getParameter("codigoCompra"));
        String mensagem = null;

        VendaDAO vendaDAO = new VendaDAO();
        
        try {
        	vendaDAO.desativar(codigo);
        	
       	 	mensagem = "Remoção realizada com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
            mensagem = "Não foi possível remover esta venda";
		}
        
        request.setAttribute("mensagem", mensagem);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("comprasUsuarioAdmin");
        requestDispatcher.forward(request, response);
    }

}
