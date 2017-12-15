package com.appevento.eventoapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes atributes) {
		if (result.hasErrors()) {
			atributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastroevento";
		}
		eventoRepository.save(evento);
		atributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
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

		Iterable<Convidado> convidados = convidadoRepository.findByEvento(eventoEspecifico);

		modelAndView.addObject("convidados", convidados);

		return modelAndView;
	}

	@RequestMapping("deletarEvento")
	public String deletarEvento(Long id) {
		Evento eventoToRemove = eventoRepository.findById(id);
		eventoRepository.delete(eventoToRemove);

		return "redirect:/eventos";
	}

	@RequestMapping("deletarConvidado")
	public String deletarConvidado(String rg) {
		Convidado convidadoToRemove = convidadoRepository.findByRg(rg);
		convidadoRepository.delete(convidadoToRemove);

		Evento evento = convidadoToRemove.getEvento();

		return "redirect:/" + evento.getId();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("id") long id, @Valid Convidado convidado, BindingResult result,
			RedirectAttributes atributes) {
		if (result.hasErrors()) {
			atributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{id}";
		}
		Evento evento = eventoRepository.findById(id);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		atributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{id}";

	}

}
