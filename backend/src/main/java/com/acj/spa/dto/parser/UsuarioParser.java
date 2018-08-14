package com.acj.spa.dto.parser;

import com.acj.spa.dto.UsuarioDTO;
import com.acj.spa.entities.Usuario;

public class UsuarioParser {

	public static UsuarioDTO toDTO(Usuario usuario) {

		return new UsuarioDTO(usuario.getId(), usuario.getNome(),usuario.getEmail(),usuario.getSenha(), usuario.getDadosProfissionais(), usuario.isAdmin());
	}
	
	public static Usuario toEntity(UsuarioDTO usuarioDTO) {
		return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha(), usuarioDTO.getDadosProfissionais(),
				usuarioDTO.isAdmin());
	}

	
}
