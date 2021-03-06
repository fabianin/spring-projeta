package br.ufes.ceunes.projeta.controllers;




import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
//	            dateFormat, false));
//	}
	
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
	public ModelAndView listarMembros(Membro membro){
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
		if(!membroLogin.getSenha().equals(login.getSenha())){
			System.out.println("******" + login.getSenha()+"*******");
			System.out.println("******" + membroLogin.getSenha()+"*******");
			return new ModelAndView("login");
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
			return new ModelAndView("home");
		}
		
	}
	@RequestMapping("/listar/ponto/{matricula}")
	public ModelAndView listarPontos(@PathVariable("matricula") Membro membro){
		ModelAndView mv = new ModelAndView("listarPontos");
		List<Ponto> todosPontos = membro.getPontos();
		PontoAberto pontoAberto = pontosAbertos.findOne(membro.getMatricula());
		mv.addObject("pontoaberto",pontoAberto);
		mv.addObject("pontos", todosPontos);
		return mv;
	}
	
	@RequestMapping(value = "/listar/membros", method=RequestMethod.POST)
	public ModelAndView pesquisa(Membro membro){
		ModelAndView mv = new ModelAndView("listarMembros");
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Membro> example = Example.of(membro, matcher);
		List<Membro> todosMembros = membros.findAll(example);
		mv.addObject("membros",todosMembros);
		mv.addObject("cargos",TipoCargo.values());
		return mv;
	}
	
	@RequestMapping(value ="/relatorio", method=RequestMethod.POST)
	public ModelAndView relatorio(Login login){
		Membro membroLogin = membros.findOne(login.getMatricula().longValue());
		if(!membroLogin.getSenha().equals(login.getSenha())){
			return new ModelAndView("/");
		}
		PontoAberto pontoAberto = pontosAbertos.findOne(membroLogin.getMatricula());
		ModelAndView mv = new ModelAndView("listarPontos");
		mv.addObject("pontoaberto",pontoAberto);
		List<Ponto> todosPontos = membroLogin.getPontos();
		mv.addObject("pontos", todosPontos);
		return mv;
		
	}
	
	
	
}
