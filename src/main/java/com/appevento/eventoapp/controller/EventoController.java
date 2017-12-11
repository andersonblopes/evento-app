package com.appevento.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appevento.eventoapp.model.Evento;
import com.appevento.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@RequestMapping(value = "/cadastroevento", method = RequestMethod.GET)
	public String form() {
		return "evento/form-evento";
	}

	@RequestMapping(value="/cadastroevento", method= RequestMethod.POST)
	public String form(Evento evento) {
		
		eventoRepository.save(evento);
		return "redirect:/cadastroevento";
	}

}
