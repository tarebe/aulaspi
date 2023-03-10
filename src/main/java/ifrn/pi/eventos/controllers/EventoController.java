package ifrn.pi.eventos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositories.EventoRepository;

@Controller
@RequestMapping("/eventos") // todas os metodos vão usar a mesma URL agora.
public class EventoController {

	@Autowired
	private EventoRepository er;

	@GetMapping("/form")
	public String form() {
		return "eventos/formEvento";
	}

	@PostMapping
	public String adicionar(Evento evento) {

		System.out.println(evento);
		er.save(evento);

		return "eventos/evento-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView md = new ModelAndView("/eventos/lista");
		List<Evento> eventos = er.findAll();
		md.addObject("eventos", eventos);
		return md;
	}

	@GetMapping("/{id}") // entratra um parametro em /eventos
	public ModelAndView detalhar(@PathVariable Long id) { // esse parametro sera capturado pelo metodo por terem o mesmo nome
		
		ModelAndView md = new ModelAndView();
		Optional<Evento> opt = er.findById(id);
		
		if(opt.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}
		
		Evento evento = opt.get();
		md.setViewName("eventos/detalhes");
		md.addObject("evento", evento);
		return md;
		
	
	}

}
