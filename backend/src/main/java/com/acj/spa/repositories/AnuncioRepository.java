package com.acj.spa.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.acj.spa.entities.Anuncio;
import com.acj.spa.entities.Usuario;

@Repository
public interface AnuncioRepository extends MongoRepository<Anuncio, String> {
	
	List<Anuncio> findByOrderByDataHoraDesc();
	List <Anuncio> findByOrderByDataHoraDesc(Pageable page);
	List<Anuncio> findByTituloLikeIgnoreCaseOrderByDataHoraDesc(String titulo);
	List<Anuncio> findByUsuario(Usuario usuario);
}
