package com.acj.spa.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DadosProfissionais implements Serializable {

    @Id
    private String id;
    private String resumo;
   
    @DBRef
    private Categoria categoria;
    @DBRef
    private Endereco endereco;
    
    

    public DadosProfissionais(String id, String resumo, Categoria categoria, Endereco endereco) {
		
		this.id = id;
		this.resumo = resumo;
		this.categoria = categoria;
		this.endereco = endereco;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }


	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	
    
}
