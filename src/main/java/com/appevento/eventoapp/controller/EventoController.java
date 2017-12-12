package com.appevento.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.appevento.eventoapp.model.Convidado;
import com.appevento.eventoapp.model.Evento;
import com.appevento.eventoapp.repository.ConvidadoRepository;
import com.appevento.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private ConvidadoRepository convidadoRepository;

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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("id") long id) {
		Evento eventoEspecifico = eventoRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("evento/detalhesevento");
		modelAndView.addObject("evento", eventoEspecifico);
		return modelAndView;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("id") long id, Convidado convidado) {
		Evento evento = eventoRepository.findById(id);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		return "redirect:/{id}";

	}

}
