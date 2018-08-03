package com.acj.spa.dto.parser;

import com.acj.spa.dto.EnderecoDTO;
import com.acj.spa.entities.Endereco;

public class EnderecoParser {

	public static EnderecoDTO toDTO(Endereco endereco) {
		return new EnderecoDTO(endereco.getId(), endereco.getCep(), endereco.getBairro(), endereco.getCidade(),
				endereco.getEstado(), endereco.getRua(), endereco.getIbge());
	}

	public static Endereco toEntity(EnderecoDTO enderecoDTO) {
		return new Endereco(enderecoDTO.getId(), enderecoDTO.getCep(), enderecoDTO.getBairro(), enderecoDTO.getCidade(),
				enderecoDTO.getEstado(), enderecoDTO.getRua(), enderecoDTO.getIbge());
	}

}
