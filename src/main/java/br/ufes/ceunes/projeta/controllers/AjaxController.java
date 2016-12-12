package br.ufes.ceunes.projeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.ceunes.projeta.model.Login;
import br.ufes.ceunes.projeta.model.Membro;
import br.ufes.ceunes.projeta.model.Ponto;
import br.ufes.ceunes.projeta.model.PontoAberto;
import br.ufes.ceunes.projeta.repository.JPAMembro;
import br.ufes.ceunes.projeta.repository.JPAPontosAbertos;

@RestController
public class AjaxController {
	@Autowired
	private JPAMembro membros;
	@Autowired
	private JPAPontosAbertos pontosAbertos;
	
	@GetMapping("/listar/membros")
	public List<Membro> listarMembros(Membro membro){
		List<Membro> todosMembros = membros.findAll();
		return todosMembros;
	}
	
	@PostMapping("/angular/relatorio")
	public List<Ponto> relatorio(Login login){
		Membro membroLogin = membros.findOne(login.getMatricula().longValue());
		if(!membroLogin.getSenha().equals(login.getSenha())){
			return null;
		}
		List<Ponto> todosPontos = membroLogin.getPontos();
		return todosPontos;
		
	}
	@DeleteMapping("/deletar")
	public void delete(Membro membro){
		membros.delete(membro);
	}
	@PutMapping("/editar")
	public void editar(Membro membro){
		membros.save(membro);
	}
	
	@GetMapping("/listar/membro/{matricula}")
	public Membro listarMembro(@PathVariable("matricula") Membro membro){
		return membro;
	}

}
