package com.acj.spa.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.acj.spa.dto.UsuarioDTO;
import com.acj.spa.entities.DadosProfissionais;
import com.acj.spa.services.UsuarioService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
    	usuarioDTO.setSenha(usuarioService.encpritografarBcripty(usuarioDTO.getSenha()));
    	usuarioDTO.setAdmin(false);
        UsuarioDTO novoUsuario = usuarioService.salvar(usuarioDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        List<UsuarioDTO> usuarios = usuarioService.buscarTodos();

        return ResponseEntity.ok(usuarios);
    }

   
    @GetMapping(value = "{email}")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@PathVariable String email) {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorEmail(email);

        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping(value = "dados-profissionais")
    public ResponseEntity<UsuarioDTO> inserirDadosProfissionais(@RequestBody DadosProfissionais dadosProfissionais, Authentication authentication) {
        UsuarioDTO usuarioDTO = usuarioService.inserirDadosProfissionais(authentication.getName(), dadosProfissionais);

        return ResponseEntity.ok(usuarioDTO);
    }
    @GetMapping(value = "protected")
    public UsuarioDTO buscarPorId(Authentication authenticatio) {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorEmail(authenticatio.getName());
        return usuarioDTO;
    }
}
