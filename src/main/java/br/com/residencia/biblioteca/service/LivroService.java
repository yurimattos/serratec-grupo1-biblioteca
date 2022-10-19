package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.LivroRepository;
import br.com.residencia.dto.EditoraDTO;
import br.com.residencia.dto.LivroDTO;

@Service
public class LivroService {
	@Autowired
	LivroRepository livroRepository;
	
	public List<Livro> getAllLivros(){
		return livroRepository.findAll();
	}
	
	public Livro getLivroById(Integer id) {
		return livroRepository.findById(id).orElse(null);
	}
	
	public Livro saveLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public LivroDTO saveLivroDTO(LivroDTO livroDTO) {
		Livro livro = toEntidade(livroDTO);
		Livro novoLivro = livroRepository.save(livro);
		
		LivroDTO livroAtualizadoDTO = toDTO(novoLivro);
		
		return livroAtualizadoDTO;
	}
	
	public Livro updateLivro(Livro livro, Integer id) {
		Livro livroExistenteNoBanco = getLivroById(id);

		livroExistenteNoBanco.setCodigoIsbn(livro.getCodigoIsbn());
		livroExistenteNoBanco.setDataLancamento(livro.getDataLancamento());
		livroExistenteNoBanco.setNomeAutor(livro.getNomeAutor());
		livroExistenteNoBanco.setNomeLivro(livro.getNomeLivro());
		
		return livroRepository.save(livroExistenteNoBanco);
	}
	
	public LivroDTO updateLivroDTO(LivroDTO livroDTO, Integer id) {
		Livro livroExistenteNoBanco = getLivroById(id);
		LivroDTO livroAtualizadoDTO = new LivroDTO();
		
		if(livroExistenteNoBanco != null) {
			livroExistenteNoBanco = toEntidade(livroDTO);
			
			Livro livroAtualizado = livroRepository.save(livroExistenteNoBanco);
			
			livroAtualizadoDTO = toDTO(livroAtualizado);
		}
		
		return livroAtualizadoDTO;
	}
	
	public LivroDTO toDTO(Livro livro) {
		LivroDTO livroDTO = new LivroDTO();
		BeanUtils.copyProperties(livro, livroDTO);
		return livroDTO;
	}
	
	public Livro toEntidade(LivroDTO livroDTO) {
		Livro livro = new Livro();
		BeanUtils.copyProperties(livroDTO, livro);
		return livro;
	}

	public Livro deleteLivro(Integer id) {
		livroRepository.deleteById(id);
		return getLivroById(id);
	}
	
}