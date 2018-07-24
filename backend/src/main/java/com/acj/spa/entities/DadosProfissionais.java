package com.acj.spa.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class DadosProfissionais implements Serializable {

    @Id
    private String id;
    private String resumo;

    @DBRef
    private Categoria profissao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Categoria getProfissao() {
        return profissao;
    }

    public void setProfissao(Categoria profissao) {
        this.profissao = profissao;
    }
}
