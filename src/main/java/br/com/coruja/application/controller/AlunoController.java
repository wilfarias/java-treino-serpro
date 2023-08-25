package br.com.coruja.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coruja.application.model.Aluno;
import br.com.coruja.application.repositories.AlunoRepository;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Aluno>> list(){
		List<Aluno> listAluno = repository.findAll();
		return ResponseEntity.ok().body(listAluno);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Aluno> find(@PathVariable Long id){
		Optional<Aluno> objAluno = repository.findById(id);
		return ResponseEntity.of(objAluno);
	}
	
	@PostMapping
	public ResponseEntity<Aluno> save(@RequestBody Aluno aluno){
		Aluno objAluno = repository.save(aluno);
		return ResponseEntity.created(null).body(objAluno);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno aluno){
		Aluno objAluno = repository.getById(id);
		objAluno.setNome(aluno.getNome());
		objAluno.setEmail(aluno.getEmail());
		objAluno = repository.save(objAluno);
		return ResponseEntity.ok().body(objAluno);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Aluno> delete(@PathVariable Long id){
		repository.deleteById(id);
		return ResponseEntity.ok(null);
	}

}
