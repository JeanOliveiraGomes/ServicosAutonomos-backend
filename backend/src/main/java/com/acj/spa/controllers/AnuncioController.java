package com.acj.spa.controllers;

import com.acj.spa.dto.AnuncioDTO;
import com.acj.spa.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody AnuncioDTO anuncioDTO) {
        AnuncioDTO novoAnuncio = anuncioService.cadastrar(anuncioDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoAnuncio.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<AnuncioDTO>> buscarTodos() {
        List<AnuncioDTO> anuncioDTOList = anuncioService.buscarTodos();

        return ResponseEntity.ok(anuncioDTOList);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AnuncioDTO> buscarPorId(@PathVariable String id) {
        AnuncioDTO anuncioDTO = anuncioService.buscarPorId(id);

        return ResponseEntity.ok(anuncioDTO);
    }
}
