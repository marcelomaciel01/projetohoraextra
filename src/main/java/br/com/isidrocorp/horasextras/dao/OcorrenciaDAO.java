package br.com.isidrocorp.horasextras.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.isidrocorp.horasextras.model.Ocorrencia;

public interface OcorrenciaDAO extends CrudRepository<Ocorrencia, Integer> {
	public ArrayList<Ocorrencia> findByStatus(int status);

}
