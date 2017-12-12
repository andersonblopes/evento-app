package com.appevento.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.appevento.eventoapp.model.Convidado;
import com.appevento.eventoapp.model.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
	Iterable<Convidado> findByEvento(Evento evento);

}
