package br.ufes.ceunes.projeta.controllers;




import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.ceunes.projeta.model.Login;
import br.ufes.ceunes.projeta.model.Membro;
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
		ModelAndView mv = new ModelAndView("ponto");
		Membro membroLogin = membros.findOne(login.getMatricula());
		if(membroLogin == null){
			return null;
		}
		if(membroLogin.getSenha() != login.getSenha()){
			return null;
		}
		if(membroLogin.getPonto() == null){
			Ponto membroPonto = new Ponto(login.getMatricula(), LocalDate.now());
			membroLogin.setPonto(membroPonto);
			membros.save(membroLogin);
			return mv;
		} else{
			LocalDate saida = LocalDate.now();
			membroLogin.getPonto().setSaida(saida);
			pontos.save(membroLogin.getPonto());
			membroLogin.setPonto(null);
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
