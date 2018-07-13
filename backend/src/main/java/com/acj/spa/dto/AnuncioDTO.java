package com.acj.spa.dto;

import java.io.Serializable;

public class AnuncioDTO implements Serializable {

    private String id;
    private String titulo;
    private String descricao;
    private UsuarioDTO anunciante;
    private CategoriaDTO categoria;

    public AnuncioDTO() {
    }

    public AnuncioDTO(String id, String titulo, String descricao, UsuarioDTO anunciante, CategoriaDTO categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.anunciante = anunciante;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioDTO getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(UsuarioDTO anunciante) {
        this.anunciante = anunciante;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
}
