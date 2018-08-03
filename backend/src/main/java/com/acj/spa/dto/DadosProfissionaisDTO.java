package com.acj.spa.dto;

import java.io.Serializable;

public class DadosProfissionaisDTO  implements Serializable{
	
	 
	    private String id;
	    private String resumo;
	    private CategoriaDTO categoria;
	    private EnderecoDTO endereco;
		
	    
	    
	    public DadosProfissionaisDTO(String id, String resumo, CategoriaDTO categoriaDTO, EnderecoDTO enderecoDTO) {
			this.id = id;
			this.resumo = resumo;
			this.categoria = categoriaDTO;
			this.endereco = enderecoDTO;
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
		public CategoriaDTO getCategoria() {
			return categoria;
		}
		public void setCategoria(CategoriaDTO categoria) {
			this.categoria = categoria;
		}
		public EnderecoDTO getEndereco() {
			return endereco;
		}
		public void setEndereco(EnderecoDTO endereco) {
			this.endereco = endereco;
		}
		
		
	    
	    

}
