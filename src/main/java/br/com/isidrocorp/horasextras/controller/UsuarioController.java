package br.com.isidrocorp.horasextras.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.horasextras.dao.UsuarioDAO;
import br.com.isidrocorp.horasextras.model.Usuario;

// vamos definir que a classe é um RestController para EXPOR URLs (nossa API)
@RestController
public class UsuarioController {
	
	// esse cara precisa acessar o banco? SIM
	/*  Aqui vamos entender um conceito chamado "Injeção de Dependência"
	 *  A Anotação Autowired faz essa injeção. Como é feita essa injeção?
	 *  
	 *  
	 */
	@Autowired
	private UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ArrayList<Usuario> buscarTodos(){
		ArrayList<Usuario> lista;
		lista = (ArrayList<Usuario>)dao.findAll(); // aqui eu faço um "Select * from itmn232_usuario"
		return lista;
	}
	
	
	/* ao retornar um objeto do tipo ResponseEntity, eu não quero que retorne apenas o conteúdo da mensagem,
	 * mas também quero poder manipular seu cabeçalho!!
	 */
	@PostMapping("/usuarios/login")
	public ResponseEntity<Usuario> logarUsuario(@RequestBody Usuario dadosLogin) {
		Usuario res = dao.findByRacf(dadosLogin.getRacf());
		if (res != null) {  // ele existe na base com o RACF informado?
			if (res.getSenha().equals(dadosLogin.getSenha())) {
				return ResponseEntity.ok(res); // retorno 200, com o objeto no corpo da resposta
			}
			else {
				return ResponseEntity.status(401).build(); // retorno código 401 com corpo da mensagem vazio
			}
		}
		else {
			return ResponseEntity.notFound().build();  // se não existir, retorno um código 404
		}
	}
	

}
