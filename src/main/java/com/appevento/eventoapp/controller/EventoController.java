package com.appevento.eventoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventoController {

	@RequestMapping("cadastroevento")
	public String form() {
		return "evento/form-evento";
	}

}
