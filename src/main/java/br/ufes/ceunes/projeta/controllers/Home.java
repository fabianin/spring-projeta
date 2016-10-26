package br.ufes.ceunes.projeta.controllers;




import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.ceunes.projeta.model.Login;
import br.ufes.ceunes.projeta.model.Membro;
import br.ufes.ceunes.projeta.model.Ponto;
import br.ufes.ceunes.projeta.model.PontoAberto;
import br.ufes.ceunes.projeta.model.TipoCargo;
import br.ufes.ceunes.projeta.repository.JPAMembro;
import br.ufes.ceunes.projeta.repository.JPAPontosAbertos;





@Controller

public class Home {
	
	@Autowired
	private JPAMembro membros;
	

	@Autowired
	private JPAPontosAbertos pontosAbertos;
	
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
	
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
		//List<Membro> todosMembros = membros.findAll();
		//mv.addObject("membros", todosMembros);
		return mv;
	}
	
	@RequestMapping("/listar/membros")
	public ModelAndView listarMembros(){
		ModelAndView mv = new ModelAndView("listarMembros");
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
		Membro membroLogin = membros.findOne(login.getMatricula().longValue());
		if(membroLogin == null){
			return new ModelAndView("cadastro");
			
		}
		if(membroLogin.getSenha() != login.getSenha()){
			new ModelAndView("login");
		}
		if(pontosAbertos.findOne(membroLogin.getMatricula())!=null){
			PontoAberto pontoAberto = pontosAbertos.findOne(membroLogin.getMatricula());
			Ponto pontoFechando = new Ponto(pontoAberto.getMatricula(), pontoAberto.getEntrada(), Instant.now());
			membroLogin.getPontos().add(pontoFechando);
			membros.save(membroLogin);
			pontosAbertos.delete(pontoAberto);
			return new ModelAndView("home");
		}else {
			PontoAberto pontoAberto = new PontoAberto(membroLogin.getMatricula(), Instant.now());
			pontosAbertos.save(pontoAberto);
			return new ModelAndView("listarMembros");
		}
		
	}
	@RequestMapping("/listar/pontos")
	public ModelAndView listarPontos(){
		ModelAndView mv = new ModelAndView("listarPontos");
		List<Ponto> todosPontos = membros.getOne(Long.getLong("2014101879")).getPontos();
		if(todosPontos == null)
			return new ModelAndView("home");
		mv.addObject("pontos", todosPontos);
		return mv;
	}
	
	
	
	
}
