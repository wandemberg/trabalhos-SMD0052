package ecommerce.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.Produto;
import ecommerce.modelo.ProdutoDAO;

/**
 *
 *
 * Classe que implementa a ação de exibição da foto de um produto
 */
public class ExibirProdutoFotoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = null;
        
        try {
            produto = produtoDAO.obter(id);
        } catch (Exception ex) {
            produto = null;
        }
        
        if (produto != null && produto.getFoto() != null && produto.getFoto().trim().length() > 0) {
            File arquivoFoto = new File(produto.getFoto());
            if (arquivoFoto.exists()) {
                ServletContext context = this.getServletContext();
                String mimeType = context.getMimeType(arquivoFoto.getAbsolutePath());
                if (mimeType == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                response.setContentLengthLong(arquivoFoto.length());
                response.setContentType(mimeType);
                FileInputStream in = new FileInputStream(arquivoFoto);
                OutputStream out = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                in.close();
                out.close();
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
    }

}
