package ecommerce.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.Usuario;
import ecommerce.modelo.UsuarioDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de usuarios
 * 
 */
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String nome = request.getParameter("usuario");
       // String codigo = request.getParameter("codigo");
        String id = request.getParameter("codigo");
        Usuario usuario = (Usuario) request.getAttribute("usuario");
        String login = request.getParameter("login");


        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioEncontrado = null;
        try {
        	usuarioEncontrado =usuarioDAO.obter(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        request.setAttribute("usuario", usuarioEncontrado);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("dados-pessoais.jsp");
        requestDispatcher.forward(request, response);
    }

}
