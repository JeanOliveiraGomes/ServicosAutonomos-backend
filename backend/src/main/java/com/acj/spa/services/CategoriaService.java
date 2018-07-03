package com.acj.spa.services;

import com.acj.spa.entities.Categoria;
import com.acj.spa.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }
}
