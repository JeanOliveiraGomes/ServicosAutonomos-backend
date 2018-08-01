package com.acj.spa.services;

import com.acj.spa.dto.AnuncioDTO;
import com.acj.spa.dto.parser.AnuncioParser;
import com.acj.spa.entities.Anuncio;
import com.acj.spa.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
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
        List<Anuncio> anuncios = anuncioRepository.findByOrderByDataHoraAsc();
        return anuncios.stream().map(AnuncioParser::toDTO).collect(Collectors.toList());
    }
    
    public List<AnuncioDTO> buscaPorTitulo(String titulo) {
    	List<Anuncio> anunciosPeloTitulo = anuncioRepository.findByTituloLikeIgnoreCaseOrderByDataHoraAsc(titulo);
        return anunciosPeloTitulo.stream().map(AnuncioParser :: toDTO).collect(Collectors.toList());
    }

    public AnuncioDTO buscarPorId(String id) {
        Anuncio anuncio = anuncioRepository.findById(id).orElse(null);
        return AnuncioParser.toDTO(anuncio);
    }
}
