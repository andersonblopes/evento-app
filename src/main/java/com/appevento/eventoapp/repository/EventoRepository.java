package com.appevento.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.appevento.eventoapp.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, String> {
	Evento findById(Long id);

}
