package ecommerce.modelo;

import java.io.Serializable;

/**
 * * 
 * Classe que representa a entidade categoria
 */
public class Categoria implements Serializable {
    
    private Integer id;
    private String descricao;
    private Boolean ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
 
    
}
