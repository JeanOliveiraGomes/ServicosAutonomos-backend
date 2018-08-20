package com.acj.spa.controllers;

import java.net.URI;
import java.util.List;

import com.acj.spa.dto.AnuncioDTO;
import com.acj.spa.entities.Anuncio;
import com.acj.spa.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
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

import com.acj.spa.dto.AnuncioDTO;
import com.acj.spa.repositories.AnuncioRepository;
import com.acj.spa.services.AnuncioService;

@RestController
@RequestMapping(value = "protected/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;
    
   
    
    
    @PostMapping (value = "/deletar") 
	public void deletarMeuAnuncio(@RequestBody String anuncioId, Authentication authenticatioToken){
		 anuncioService.deletarMeuAnuncio(anuncioId, authenticatioToken.getName());
	}
	
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
    
    @GetMapping(value = "/servico-paginado")
    public ResponseEntity<List<AnuncioDTO>> buscarTodosPaginado(Pageable page) {
        List<AnuncioDTO> anuncioDTOList = anuncioService.buscarTodosPaginadoOrdenadoHora(page);
     
        
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
