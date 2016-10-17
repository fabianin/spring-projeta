package br.ufes.ceunes.projeta.controllers;




import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.ceunes.projeta.model.Login;
import br.ufes.ceunes.projeta.model.Membro;
import br.ufes.ceunes.projeta.model.Ponto;
import br.ufes.ceunes.projeta.model.TipoCargo;
import br.ufes.ceunes.projeta.repository.JPAPonto;
import br.ufes.ceunes.projeta.repository.JPAmembro;





@Controller
@RequestMapping("/")
public class Home {
	
	@Autowired
	private JPAmembro membros;
	
	@Autowired
	private JPAPonto pontos;
	
	@RequestMapping("/")
	@ResponseBody
	public ModelAndView home(){
		return new ModelAndView("home");
		
	}
	@RequestMapping("/cadastro")
	public ModelAndView cadastro(Membro membro){
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("cargo",TipoCargo.values());

		return mv;
	}
	@RequestMapping("/login")
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value ="/cadastro", method=RequestMethod.POST)
	public ModelAndView cadastrar(Membro membro){
		membros.save(membro);
		return new ModelAndView ("redirect:/");
	}
	
	@RequestMapping("/listar")
	public ModelAndView listar(){
		ModelAndView mv = new ModelAndView("listar");
		List<Membro> todosMembros = membros.findAll();
		mv.addObject("membros", todosMembros);
		return mv;
	}
	@RequestMapping("/ponto")
	public ModelAndView ponto(Login login){
		ModelAndView mv = new ModelAndView("ponto");
		return mv;
		
	}
	@RequestMapping(value = "/ponto", method=RequestMethod.POST)
	public ModelAndView salvarPonto(Login login){
		Membro membroLogin = membros.findOne(login.getMatricula().intValue());
		if(membroLogin == null){
			return null;
			
		}
		if(membroLogin.getSenha() != login.getSenha()){
			return null;
		}
		Ponto ponto = pontos.findOne(login.getMatricula());
		if(ponto == null){
			Instant agora = Instant.now();
			ponto = new Ponto(membroLogin,agora);
			pontos.save(ponto);
			ModelAndView mv = new ModelAndView("redirect:/home");
			return mv;
		} else{
			Instant fechaPonto = Instant.now();
			ponto.setSaida(fechaPonto);
			membroLogin.getPontos().add(ponto);
			pontos.delete(ponto);
			membros.save(membroLogin);
			ModelAndView mv = new ModelAndView("redirect:/home");
			return mv;
		}
		
	}
//	@RequestMapping("/listar/pontos")
//	public ModelAndView listarPontos(){
//		ModelAndView mv = new ModelAndView("listarPontos");
//		List<Ponto> todosPontos = pontos.findAll();
//		if(todosPontos == null)
//			return new ModelAndView("home");
//		mv.addObject("pontos", todosPontos);
//		return mv;
//	}
	
	
	
	
}
