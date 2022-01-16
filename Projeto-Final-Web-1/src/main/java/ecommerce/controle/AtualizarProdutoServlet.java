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

import ecommerce.modelo.Produto;
import ecommerce.modelo.ProdutoDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de produtos
 * 
 */
public class AtualizarProdutoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = -1;
		FileItem foto = null;

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);


		String nome = null;// = request.getParameter("descricao");
		int codigo = 0;// = Integer.parseInt(request.getParameter("codigo"));
		String descricao = null;// = request.getParameter("descricao");
		//  String foto = request.getParameter("foto");
		double preco = 0;// = Double.parseDouble(request.getParameter("preco").replaceAll(",", "."));
		int quantidade = 0;// = Integer.parseInt(request.getParameter("quantidade"));

		ProdutoDAO produtoDAO = new ProdutoDAO();
		String mensagem = null;
		boolean inseriu = true;
		Integer idCategoria = null;


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
						codigo = Integer.parseInt(item.getString());

					}

					if (item.isFormField() && item.getFieldName().equals("nome")) {
						nome = item.getString();
					}

					if (item.isFormField() && item.getFieldName().equals("descricao")) {
						descricao = item.getString();
					}

					if (item.isFormField() && item.getFieldName().equals("preco")) {
						preco = Double.parseDouble(item.getString().replaceAll(",", "."));
					}

					if (item.isFormField() && item.getFieldName().equals("quantidade")) {
						quantidade = Integer.parseInt(item.getString());
					}

					if (item.isFormField() && item.getFieldName().equals("category")) {
						idCategoria = Integer.parseInt(item.getString());
					}
				}
				if (id != -1 && foto != null) {
					if (!foto.getName().equals("")) {
						File destFile = new File("/home/wandemberg/Upload/", id + foto.getName().substring(foto.getName().lastIndexOf(".")));
						if(destFile.exists()) {
							destFile.delete();
						}
						foto.write(new File("/home/wandemberg/Upload/" + id + foto.getName().substring(foto.getName().lastIndexOf("."))));
						produtoDAO.atualizar(nome,codigo, "/home/wandemberg/Upload/" + id + foto.getName().substring(foto.getName().lastIndexOf(".")), preco, descricao, quantidade, false);
					} else {
						produtoDAO.atualizar(nome,codigo, null, preco, descricao, quantidade, false);

					}

					Integer categoriaAssociada = produtoDAO.categoriaProduto(codigo, false );

					if (categoriaAssociada!=null) {
						produtoDAO.removerCategoriaProduto(codigo, categoriaAssociada, false);
					}

					if (idCategoria != null) {
						produtoDAO.inserirCategoriaProduto(codigo, idCategoria,true);
					}

					mensagem = "Atualização realizada com sucesso!";
					sucesso = true;
				}

				if (sucesso) {
					mensagem = "Atualização realizada com sucesso!";
				} else {
					inseriu = false;
					mensagem = "Não foi possível processar o upload da foto deste produto";
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				inseriu = false;
				mensagem = "Não foi possível processar o upload da foto deste produto";
			}
		} else {
			inseriu = false;
			mensagem = "Não foi possível processar o upload da foto deste produto";
		}

		/* RequestDispatcher requestDispatcher = request.getRequestDispatcher("MostrarProdutoFoto?id=" + id);
        requestDispatcher.forward(request, response);
		 */

		request.setAttribute("mensagem", mensagem);

		if (inseriu) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtos.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Produto produtoAtualizar = null;
			try {
				produtoAtualizar =produtoDAO.obter(codigo, true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("produtoAtualizar", produtoAtualizar);
			request.setAttribute("produtoAtualizar", produtoAtualizar);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("produtos-atualizar.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
