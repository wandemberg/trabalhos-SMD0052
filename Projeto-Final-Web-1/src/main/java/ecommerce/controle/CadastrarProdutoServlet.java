package ecommerce.controle;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ecommerce.modelo.CarrinhoCompra;
import ecommerce.modelo.CarrinhoCompraItem;
import ecommerce.modelo.Produto;
import ecommerce.modelo.ProdutoDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de produtos
 * 
 */
public class CadastrarProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
/*
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
                    
                    if (item.isFormField() && item.getFieldName().equals("codigo")) {
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
        */
        String nome = request.getParameter("descricao");
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String descricao = request.getParameter("descricao");
        String foto = request.getParameter("foto");
        double preco = Double.parseDouble(request.getParameter("preco").replaceAll(",", "."));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        String mensagem = null;
        boolean inseriu = true;
        try {
        	 produtoDAO.inserir(nome,codigo, foto, preco, descricao, quantidade);
             mensagem = "Cadastro realizado com sucesso!";
		} catch (Exception e) {
            mensagem = e.getMessage();
            e.printStackTrace();
            inseriu = false;
		}
        
        request.setAttribute("mensagem", mensagem);

        if (inseriu) {
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtos.jsp");
	        requestDispatcher.forward(request, response);
        } else {
        	RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtos-cadastrar.jsp");
	        requestDispatcher.forward(request, response);
        }
    }

}
