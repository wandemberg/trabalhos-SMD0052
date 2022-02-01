package ecommerce.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author wandemberg
 *
 *Classe que representa os itens do relatório de valor recebido por dia
 */

@SuppressWarnings("serial")
public class ItemRelatorioValorPorDia implements Serializable{

	private Date data;
	private double valor;
		
	public String getDataString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		return formato.format(this.data);
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
}
