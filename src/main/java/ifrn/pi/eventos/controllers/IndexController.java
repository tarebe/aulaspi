package ifrn.pi.eventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		System.out.println("Voce chamou o método index");
		return "redirect:/eventos"; //redireciona pra pagina de eventos
	}
}
