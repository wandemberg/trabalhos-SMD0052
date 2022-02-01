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
import ecommerce.modelo.VendaDAO;

/**
 *
 *
 * Servlet que implementa a camada de controle da ação de produtos
 * 
 */
@SuppressWarnings("serial")
public class TotalComprasClientePeriodoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
   
		String inicio = request.getParameter("periodoInicio");
		String fim = request.getParameter("periodoFim");

		List<ItemRelatorioClientePeriodo> vendasEncontradas = null;

		if (inicio!=null && fim!=null) {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
			Date dataInicio = null;
			Date dataFim = null;
			
			try {
				dataInicio = formato.parse(inicio);
				dataFim = formato.parse(fim);
				Calendar cal = Calendar.getInstance(); 
				cal.setTime(dataFim); 
				cal.add(Calendar.DATE, 1);
				dataFim = cal.getTime();				

			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			VendaDAO vendaDAO = new VendaDAO();

			try {
				vendasEncontradas = vendaDAO.obterRelatorioComprasPeriodo(dataInicio, dataFim, false);
			} catch (Exception e) {
				vendasEncontradas = null;			
				e.printStackTrace();
			}
		}
		
		request.setAttribute("vendasEncontradas", vendasEncontradas);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("relatorio-compras-periodo.jsp");
		requestDispatcher.forward(request, response);
	}

}
