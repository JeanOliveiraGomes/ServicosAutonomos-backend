package com.acj.spa.dto.parser;

import com.acj.spa.dto.CategoriaDTO;
import com.acj.spa.dto.DadosProfissionaisDTO;
import com.acj.spa.dto.EnderecoDTO;
import com.acj.spa.entities.Categoria;
import com.acj.spa.entities.DadosProfissionais;
import com.acj.spa.entities.Endereco;

public class DadosProfissionaisParser {

	public static DadosProfissionaisDTO toDTO(DadosProfissionais dadosProfissionais) {

		EnderecoDTO enderecoDTO = EnderecoParser.toDTO(dadosProfissionais.getEndereco());
		CategoriaDTO categoriaDTO = CategoriaParser.toDTO(dadosProfissionais.getCategoria());

		return new DadosProfissionaisDTO(dadosProfissionais.getId(), dadosProfissionais.getResumo(), categoriaDTO,
				enderecoDTO);
	}

	public static DadosProfissionais toEntity(DadosProfissionaisDTO dadosProfissionaisDTO) {

		Categoria categoria = CategoriaParser.toEntity(dadosProfissionaisDTO.getCategoria());
		Endereco endereco = EnderecoParser.toEntity(dadosProfissionaisDTO.getEndereco());

		return new DadosProfissionais(dadosProfissionaisDTO.getId(), dadosProfissionaisDTO.getResumo(), categoria,
				endereco);
	}

}
