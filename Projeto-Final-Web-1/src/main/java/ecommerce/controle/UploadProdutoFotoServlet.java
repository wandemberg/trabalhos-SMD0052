package ecommerce.controle;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ecommerce.modelo.ProdutoDAO;

/**
 *
 *
 * Classe que implementa a ação de upload de foto para um produto
 */
public class UploadProdutoFotoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = -1;
        FileItem foto = null;

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            boolean sucesso = false;
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(new File("/home/wandemberg/Upload/temp"));

                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> itens = upload.parseRequest(request);
                Iterator<FileItem> iter = itens.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    
                    if (!item.isFormField() && item.getFieldName().equals("foto")) {
                        foto = item;
                    }
                    
                    if (item.isFormField() && item.getFieldName().equals("id")) {
                        id = Integer.parseInt(item.getString());
                    }
                }
                if (id != -1 && foto != null) {
                    foto.write(new File("/home/wandemberg/Upload/" + id + foto.getName().substring(foto.getName().lastIndexOf("."))));
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    produtoDAO.atualizarFoto(id, "/home/wandemberg/Upload/" + id + foto.getName().substring(foto.getName().lastIndexOf(".")));
                    sucesso = true;
                }
                
                if (sucesso) {
                    request.setAttribute("mensagem", "Upload da foto deste produto foi efetuado com sucesso");
                } else {
                    request.setAttribute("mensagem", "Não foi possível processar o upload da foto deste produto");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                request.setAttribute("mensagem", "Não foi possível processar o upload da foto deste produto");
            }
        } else {
            request.setAttribute("mensagem", "Não foi possível processar o upload da foto deste produto");
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MostrarProdutoFoto?id=" + id);
        requestDispatcher.forward(request, response);
    }

}
