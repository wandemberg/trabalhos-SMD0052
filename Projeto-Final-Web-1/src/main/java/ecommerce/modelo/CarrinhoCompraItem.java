package ecommerce.modelo;

import java.io.Serializable;

/**
 *
 * 
 * Classe que representa um item do carrinho de compras
 */
public class CarrinhoCompraItem implements Serializable {
    
    private Produto produto;
    private int quantidade;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
