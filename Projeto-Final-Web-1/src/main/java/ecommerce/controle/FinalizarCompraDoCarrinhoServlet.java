package ecommerce.controle;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.CarrinhoCompra;
import ecommerce.modelo.CarrinhoCompraItem;
import ecommerce.modelo.Produto;
import ecommerce.modelo.ProdutoDAO;
import ecommerce.modelo.VendaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de finalizar uma compra 
 * do carrinho de compras
 */
public class FinalizarCompraDoCarrinhoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* entrada */

		/* processamento */
		Cookie cookie = CarrinhoCompra.obterCookie(request);
		List<CarrinhoCompraItem> carrinhoCompraItens = CarrinhoCompra.obterCarrinhoCompra(cookie.getValue());

		ProdutoDAO produtoDAO = new ProdutoDAO();
		VendaDAO vendaDAO = new VendaDAO();
		Produto produtoAtualizar = null;
		boolean produtosDiponiveis = true;
		String produtosIndisponiveis = " Só existem em estoque: ";

        String idUsuario = request.getParameter("idUsuario");
        Integer idUsuarioInt = idUsuario!=null ? Integer.parseInt(idUsuario) : 4;


		for (CarrinhoCompraItem item : carrinhoCompraItens) {        	
			try {
				produtoAtualizar =produtoDAO.obter(item.getProduto().getId(), false);
				if (produtoAtualizar.getQuantidade()<item.getQuantidade()) {
					produtosDiponiveis = false;
					produtosIndisponiveis = produtosIndisponiveis +produtoAtualizar.getQuantidade() + " undidade(s) de " + produtoAtualizar.getNome() + ", ";
				}            		
			} catch (Exception e) {
				e.printStackTrace();
			}        	
		}

		/* saída */
		if (produtosDiponiveis) {
	        Random gerador = new Random();
	        int idVenda = gerador.nextInt(10000000);
	        
			try {
				vendaDAO.inserir(idVenda, new Timestamp(System.currentTimeMillis()), idUsuarioInt, false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				carrinhoCompraItens = null;
				e1.printStackTrace();
			}

			for (CarrinhoCompraItem item : carrinhoCompraItens) {
				try {
					produtoAtualizar =produtoDAO.obter(item.getProduto().getId(), false);
					produtoDAO.atualizar(item.getProduto().getNome(), item.getProduto().getId(), 
							item.getProduto().getFoto(), item.getProduto().getPreco(), item.getProduto().getDescricao(), 
							produtoAtualizar.getQuantidade()-item.getQuantidade(), false);
					
					vendaDAO.inserirVendaProduto(item.getProduto().getId(), idVenda,item.getQuantidade(), false);
					
					String novoValor = CarrinhoCompra.removerItem( item.getProduto().getId(), cookie.getValue());
					cookie.setValue(novoValor);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			request.setAttribute("mensagem", "Compra realizada com sucesso!");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Inicio");
			requestDispatcher.forward(request, response);
		} else {
			request.setAttribute("mensagem", " Não foi possível finalizar a compra. " + produtosIndisponiveis);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("carrinhoCompra");
			requestDispatcher.forward(request, response);
		}


	}

}
