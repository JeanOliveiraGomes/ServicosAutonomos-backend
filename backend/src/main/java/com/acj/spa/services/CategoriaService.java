package com.acj.spa.services;

import com.acj.spa.dto.CategoriaDTO;
import com.acj.spa.dto.parser.CategoriaParser;
import com.acj.spa.dto.parser.UsuarioParser;
import com.acj.spa.entities.Categoria;
import com.acj.spa.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    public CategoriaDTO findById(String id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        return CategoriaParser.toDTO(categoria);
    }
    
    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }
}
