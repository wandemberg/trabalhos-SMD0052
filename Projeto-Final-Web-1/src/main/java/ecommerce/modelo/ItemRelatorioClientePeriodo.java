package ecommerce.modelo;

import java.io.Serializable;

/**
 * 
 * @author wandemberg
 *
 *Classe que representa os itens do formulário de compras de clientes por período
 */

@SuppressWarnings("serial")
public class ItemRelatorioClientePeriodo implements Serializable{

	private int id;
	private String nome;
	private int quantidadeCompras;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidadeCompras() {
		return quantidadeCompras;
	}
	public void setQuantidadeCompras(int quantidadeCompras) {
		this.quantidadeCompras = quantidadeCompras;
	}
	
}
