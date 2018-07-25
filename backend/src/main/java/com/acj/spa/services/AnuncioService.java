package com.acj.spa.services;

import com.acj.spa.dto.AnuncioDTO;
import com.acj.spa.dto.parser.AnuncioParser;
import com.acj.spa.entities.Anuncio;
import com.acj.spa.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Anuncio anuncio = AnuncioParser.toEntity(anuncioDTO);
        return AnuncioParser.toDTO(anuncioRepository.save(anuncio));
    }

    public List<AnuncioDTO> buscarTodos() {
        List<Anuncio> anuncios = anuncioRepository.findAll();
        return anuncios.stream().map(AnuncioParser::toDTO).collect(Collectors.toList());
    }

    public AnuncioDTO buscarPorId(String id) {
        Anuncio anuncio = anuncioRepository.findById(id).orElse(null);
        return AnuncioParser.toDTO(anuncio);
    }
}
