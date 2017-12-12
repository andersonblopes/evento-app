package com.appevento.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "/cadastroevento", method = RequestMethod.POST)
	public String form(Evento evento) {

		eventoRepository.save(evento);
		return "redirect:/cadastroevento";
	}

	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView modelAndView = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepository.findAll();
		modelAndView.addObject("eventos", eventos);
		return modelAndView;
	}

	@RequestMapping("/{id}")
	public ModelAndView detalhesEvento(@PathVariable("id") long id) {
		Evento eventoEspecifico = eventoRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("evento/detalhesevento");
		modelAndView.addObject("evento", eventoEspecifico);
		return modelAndView;

	}

}
