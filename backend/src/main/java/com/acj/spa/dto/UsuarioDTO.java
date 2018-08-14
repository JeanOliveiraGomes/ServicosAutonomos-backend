package com.acj.spa.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.acj.spa.entities.DadosProfissionais;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

    private String id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Formato inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 8, max = 30, message = "A senha deve conter entre 8 e 30 caracteres")
    private String senha;
    
    private DadosProfissionais dadosProfissionais;
    
    private boolean isAdmin;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String nome, String email, String senha, DadosProfissionais dadosProfissionais, boolean isAdmin) {
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
    
}
