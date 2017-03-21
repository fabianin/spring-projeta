package br.ufes.ceunes.projeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufes.ceunes.projeta.model.Membro;
import br.ufes.ceunes.projeta.repository.JPAMembro;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private JPAMembro membro;
	
	@RequestMapping("/m")
	public List<Membro> listar(){
		return membro.findAll();
	}
}
