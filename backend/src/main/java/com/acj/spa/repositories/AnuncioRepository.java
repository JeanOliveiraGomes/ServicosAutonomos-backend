package com.acj.spa.repositories;

import com.acj.spa.entities.Anuncio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends MongoRepository<Anuncio, String> {

	List<Anuncio> findByOrderByDataHoraDesc();
	
	List<Anuncio> findByTituloLikeIgnoreCaseOrderByDataHoraDesc(String titulo);
}
