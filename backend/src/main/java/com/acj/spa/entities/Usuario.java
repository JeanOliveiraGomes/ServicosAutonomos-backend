package com.acj.spa.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Usuario implements Serializable {

    @Id
    private String id;

    private String nome;

    @Indexed(unique = true)
    private String email;

    private String senha;
    

    @DBRef
    private DadosProfissionais dadosProfissionais;
    
    private boolean isAdmin;


	public Usuario() {
    }

    public Usuario(String id, String nome, String email, String senha, DadosProfissionais dadosProfissionais, boolean isAdmin) {
       
    	this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dadosProfissionais = dadosProfissionais;
        this.isAdmin = isAdmin;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

    public DadosProfissionais getDadosProfissionais() {
        return dadosProfissionais;
    }

    public void setDadosProfissionais(DadosProfissionais dadosProfissionais) {
        this.dadosProfissionais = dadosProfissionais;
    }

    public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
        	Usuario qualquer = (Usuario) obj;
          return this.id.equals(qualquer.id);
        }else {
          return false;
        }
      }
}
