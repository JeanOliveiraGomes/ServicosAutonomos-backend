package com.acj.spa.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EnderecoDTO {

	private String id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 8, max = 8, message = "O cep deve conter 8 caracteres")
	private String cep;

	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String ibge;

	public EnderecoDTO(String id, String cep, String rua, String bairro, String cidade, String estado, String ibge) {

		this.id = id;
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.ibge = ibge;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

}
