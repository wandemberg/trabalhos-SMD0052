package ecommerce.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.UsuarioDAO;

/**
 * Servlet implementation class NovoClienteServlet
 */
public class NovoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        boolean administrador = false;
        /* processamento */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean inseriu = false;
        String mensagem = null;
        try {
            usuarioDAO.inserir(nome, endereco, email, login, senha, administrador);
            inseriu = true;
            mensagem = "Cliente inserido com sucesso";
        } catch (Exception ex) {
            inseriu = false;
            mensagem = ex.getMessage();
        }
        /* saída */
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("compras.jsp");
        requestDispatcher.forward(request, response);
    }

}
