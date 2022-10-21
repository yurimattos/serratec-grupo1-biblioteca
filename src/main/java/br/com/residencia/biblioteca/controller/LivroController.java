package br.com.residencia.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
	@Autowired
	LivroService livroService;
	
	@GetMapping
	public ResponseEntity<List<Livro>> getAllLivros(){
		return new ResponseEntity<>(livroService.getAllLivros(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> getLivroById(@PathVariable Integer id){
		Livro livro = livroService.getLivroById(id);
		if(livro != null) {
			return new ResponseEntity<>(livro, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(livro, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Livro> saveLivro(@RequestBody Livro livro){
		return new ResponseEntity<>(livroService.saveLivro(livro), HttpStatus.CREATED);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<LivroDTO> saveLivroDTO(@RequestBody LivroDTO livroDTO){
		return new ResponseEntity<>(livroService.saveLivroDTO(livroDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> updateLivro(@RequestBody Livro livro, @PathVariable Integer id){
		return new ResponseEntity<>(livroService.updateLivro(livro, id), HttpStatus.OK);
	}
	
	@PutMapping("/dto/{id}")
	public ResponseEntity<LivroDTO> updateLivroDTO(@RequestBody LivroDTO livroDTO, @PathVariable Integer id){
		return new ResponseEntity<>(livroService.updateLivroDTO(livroDTO, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> deleteLivro(@PathVariable Integer id){
		Livro livro = livroService.getLivroById(id);
		if(livro != null) {
			return new ResponseEntity<>(livroService.deleteLivro(id), HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	
}
