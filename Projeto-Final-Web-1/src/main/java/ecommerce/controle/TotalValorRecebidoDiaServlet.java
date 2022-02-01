package ecommerce.controle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecommerce.modelo.ItemRelatorioClientePeriodo;
import ecommerce.modelo.ItemRelatorioValorPorDia;
import ecommerce.modelo.Venda;
import ecommerce.modelo.VendaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação do relatório de valores recebidos por dia
 * 
 */
@SuppressWarnings("serial")
public class TotalValorRecebidoDiaServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ItemRelatorioValorPorDia> vendasEncontradas = null;

		VendaDAO vendaDAO = new VendaDAO();

		try {
			vendasEncontradas = vendaDAO.obterRelatorioComprasPorDia(true);
		} catch (Exception e) {
			vendasEncontradas = null;			
			e.printStackTrace();
		}


		request.setAttribute("vendasEncontradas", vendasEncontradas);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("relatorio-valor-recebido-dia.jsp");
		requestDispatcher.forward(request, response);
	}

}
