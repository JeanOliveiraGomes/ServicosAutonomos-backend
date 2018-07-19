package com.acj.spa.controllers;

import com.acj.spa.dto.UsuarioDTO;
import com.acj.spa.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
    	usuarioDTO.setSenha(usuarioService.encpritografarBcripty(usuarioDTO.getSenha()));
        UsuarioDTO novoUsuario = usuarioService.salvar(usuarioDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        List<UsuarioDTO> usuarios = usuarioService.buscarTodos();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable String id) {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(usuarioDTO);
    }
}
