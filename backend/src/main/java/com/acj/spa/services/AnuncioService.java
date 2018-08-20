package com.acj.spa.services;

import com.acj.spa.dto.AnuncioDTO;
import com.acj.spa.dto.parser.AnuncioParser;
import com.acj.spa.dto.parser.UsuarioParser;
import com.acj.spa.entities.Anuncio;
import com.acj.spa.entities.Usuario;
import com.acj.spa.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CategoriaService categoriaService;

    public AnuncioDTO cadastrar(AnuncioDTO anuncioDTO, String email) {
        anuncioDTO.setCategoria(categoriaService.buscarPorId(anuncioDTO.getCategoria().getId()));
        anuncioDTO.setAnunciante(usuarioService.buscarPorEmail(email));
        
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        agora.format(formatter);
        anuncioDTO.setDataHora(agora);
        
        Anuncio anuncio = AnuncioParser.toEntity(anuncioDTO);
        return AnuncioParser.toDTO(anuncioRepository.save(anuncio));
    }

    public List<AnuncioDTO> buscarTodos() {
        List<Anuncio> anuncios = anuncioRepository.findByOrderByDataHoraDesc();
        return anuncios.stream().map(AnuncioParser::toDTO).collect(Collectors.toList());
    }
    
    public List<AnuncioDTO> buscarTodosPaginadoOrdenadoHora(Pageable page) {
    	
        List<Anuncio> anuncios = anuncioRepository.findByOrderByDataHoraDesc(page);
        return anuncios.stream().map(AnuncioParser::toDTO).collect(Collectors.toList());
    }
    
    public List<AnuncioDTO> listarMeusAnuncios(String idUsuario) {
    	Usuario usuario = UsuarioParser.toEntity(usuarioService.buscarPorEmail(idUsuario));
        List<Anuncio> anuncios = anuncioRepository.findByUsuario(usuario);
        return anuncios.stream().map(AnuncioParser::toDTO).collect(Collectors.toList());
    }
    
    public List<AnuncioDTO> buscaPorTitulo(String titulo) {
    	List<Anuncio> anunciosPeloTitulo = anuncioRepository.findByTituloLikeIgnoreCaseOrderByDataHoraDesc(titulo);
        return anunciosPeloTitulo.stream().map(AnuncioParser :: toDTO).collect(Collectors.toList());
    }

    public AnuncioDTO buscarPorId(String id) {
        Anuncio anuncio = anuncioRepository.findById(id).orElse(null);
        return AnuncioParser.toDTO(anuncio);
    }
    
    
    public void candidatar(String anuncioId, String usuarioId) {
       Anuncio anuncio =  anuncioRepository.findById(anuncioId).orElse(null);
       Usuario usuario = UsuarioParser.toEntity(usuarioService.buscarPorEmail(usuarioId));
       		
       if (this.candidaturaValida(usuario, anuncio)) {
    	   List<Usuario> candidatos = new ArrayList<Usuario>();
    	   if (anuncio.getCandidatos() != null) {
    		   candidatos = anuncio.getCandidatos();
    	   }
       	   candidatos.add(usuario);
           anuncio.setCandidatos(candidatos);
           anuncioRepository.save(anuncio);
       }else{
    	   System.out.println("Candidatura Invalida");
    	   throw new DataIntegrityViolationException("Erro usuario ja existe ou é dono do anuncio");
       }	   
    } 
    
    public void deletarMeuAnuncio(String anuncioId, String usuarioId) {
    	Anuncio anuncio =  anuncioRepository.findById(anuncioId).orElse(null);
    	if (anuncio.getUsuario().getEmail().equals(usuarioId)) {
    		anuncioRepository.delete(anuncio);
		}else{
	    	   System.out.println("Usuario Tentando apagar anuncio pertecente a outro usuario");
	    	   throw new DataIntegrityViolationException("Usuario não é dono do anuncio");
	    }	   
    	
    }
    
    
    public boolean candidaturaValida(Usuario usuario, Anuncio anuncio) {
    	boolean candidaturaValida = false;
    	
    	if (usuario.getId().equals(anuncio.getUsuario().getId())) {
    		candidaturaValida = false;
		}else{
			 List<Usuario> candidatos = new ArrayList<Usuario>();
			 if(anuncio.getCandidatos() != null) {
				 candidatos = anuncio.getCandidatos();
				 if(candidatos.contains(usuario)) {
					 candidaturaValida = false;
				 }else {
					 candidaturaValida = true;
				 }
			 }else {
				 candidaturaValida=true;
			 }
     	}
    	return candidaturaValida;
    }
}
