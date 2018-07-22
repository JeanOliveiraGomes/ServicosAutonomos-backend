package com.acj.spa.services;

import com.acj.spa.dto.UsuarioDTO;
import com.acj.spa.dto.parser.UsuarioParser;
import com.acj.spa.entities.Usuario;
import com.acj.spa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioParser.toEntity(usuarioDTO);
        return UsuarioParser.toDTO(usuarioRepository.save(usuario));
    }

    public List<UsuarioDTO> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioParser::toDTO).collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(String id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return UsuarioParser.toDTO(usuario);
    }
    
    public UsuarioDTO buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return UsuarioParser.toDTO(usuario);
    }
    
	//METODO DE ENCRIPTO PARA SENHAS
	public String encpritografarBcripty(String senha) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(senha);	
	}
}
