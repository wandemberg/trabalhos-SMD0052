package ecommerce.controle;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ecommerce.modelo.Usuario;
import ecommerce.modelo.UsuarioDAO;

/**
 *
 * @author Wandemberg Gomes
 *
 * Classe que implementa a ação de login de um usuário do tipo cliente
 */
public class LoginClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String login = request.getParameter("uname");
        String senha = request.getParameter("psw");
        /* processamento */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = false;
        String mensagem = null;
        try {
            Usuario usuario = usuarioDAO.obter(login);
            if ( usuario.isAtivo() && usuario.getSenha().equals(senha)) {
                sucesso = true;
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
            } else {
                sucesso = false;
                mensagem = "Login ou senha inválida";
            }
        } catch (Exception ex) {
            sucesso = false;
            mensagem = ex.getMessage();
        }
        /* saída */
        if (sucesso) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Inicio");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}
