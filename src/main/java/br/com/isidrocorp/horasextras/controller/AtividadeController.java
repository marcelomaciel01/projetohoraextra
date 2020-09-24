package br.com.isidrocorp.horasextras.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.horasextras.dao.AtividadeDAO;
import br.com.isidrocorp.horasextras.model.Atividade;

@RestController
public class AtividadeController {
	
	@Autowired
	AtividadeDAO dao;

	@GetMapping("/atividades")
	public ArrayList<Atividade> buscarTodas(){
		ArrayList<Atividade> lista;
		lista = (ArrayList<Atividade>)dao.findAll();
		return lista;
	}
}
