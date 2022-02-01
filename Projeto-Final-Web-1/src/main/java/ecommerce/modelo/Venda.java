package ecommerce.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Venda {

	private int id;
	private Date data;
	private int idUsuario;
	private String nomeUsuario;
	private List<VendaProduto> produtosVendidos;
	private Double totalVenda;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public List<VendaProduto> getProdutosVendidos() {
		return produtosVendidos;
	}
	public void setProdutosVendidos(List<VendaProduto> produtosVendidos) {
		this.produtosVendidos = produtosVendidos;
	}
	public Double getTotalVenda() {
		return totalVenda;
	}
	public void setTotalVenda(Double totalVenda) {
		this.totalVenda = totalVenda;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
}
