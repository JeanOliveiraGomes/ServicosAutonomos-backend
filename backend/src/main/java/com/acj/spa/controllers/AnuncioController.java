package com.acj.spa.controllers;

import com.acj.spa.dto.AnuncioDTO;
import com.acj.spa.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "protected/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody AnuncioDTO anuncioDTO, Authentication authenticatioToken) {
        AnuncioDTO novoAnuncio = anuncioService.cadastrar(anuncioDTO, authenticatioToken.getName());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoAnuncio.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping
    public void candidatar(@RequestBody String anuncioId, Authentication authenticatioToken) {
    	anuncioService.candidatar(anuncioId, authenticatioToken.getName());
    }
    
    @GetMapping(value = "/meus-anuncios")
    public ResponseEntity<List<AnuncioDTO>> listarMeusAnucios(Authentication authenticatioToken) {
        List<AnuncioDTO> anuncioDTOList = anuncioService.listarMeusAnuncios(authenticatioToken.getName());

        return ResponseEntity.ok(anuncioDTOList);
    }
    
    @GetMapping
    public ResponseEntity<List<AnuncioDTO>> buscarTodos() {
        List<AnuncioDTO> anuncioDTOList = anuncioService.buscarTodos();
        
        return ResponseEntity.ok(anuncioDTOList);
    }
    
    @GetMapping(value = "/busca/{titulo}")
    public ResponseEntity<List<AnuncioDTO>> buscarPorTitulo(@PathVariable String titulo) {
        List<AnuncioDTO> anuncioDTOList = anuncioService.buscaPorTitulo(titulo);

        return ResponseEntity.ok(anuncioDTOList);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AnuncioDTO> buscarPorId(@PathVariable String id) {
        AnuncioDTO anuncioDTO = anuncioService.buscarPorId(id);

        return ResponseEntity.ok(anuncioDTO);
    }
}
