package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoEmprestimoDTO;
import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.dto.EmprestimoResumoDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	public List<Emprestimo> getAllEmprestimos(){
		return emprestimoRepository.findAll();
	}
	
	public List<EmprestimoDTO> getAllEmprestimosDTO(){
		List<Emprestimo> listaEmprestimo = emprestimoRepository.findAll();
		List<EmprestimoDTO> listaEmprestimoDTO = new ArrayList<>();
		
		for(Emprestimo emprestimo: listaEmprestimo) {
			EmprestimoDTO emprestimoDTO = toDTO(emprestimo);
			listaEmprestimoDTO.add(emprestimoDTO);
		}
		
		return listaEmprestimoDTO;
	}
	
	public Emprestimo getEmprestimoById(Integer id) {
		//return emprestimoRepository.findById(id).get();
		return emprestimoRepository.findById(id).orElse(null);
	}
	
	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	public Emprestimo updateEmprestimo(Emprestimo emprestimo, Integer id) {
		//Emprestimo emprestimoExistenteNoBanco = emprestimoRepository.findById(id).get();
		
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);

		//emprestimoExistenteNoBanco.setAluno(Aluno);
		emprestimoExistenteNoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());
		//emprestimoExistenteNoBanco.setLivro(Livro);
		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());
		
		return emprestimoRepository.save(emprestimoExistenteNoBanco);
		
		//return emprestimoRepository.save(emprestimo);
	}

	public Emprestimo deleteEmprestimo(Integer id) {
		emprestimoRepository.deleteById(id);
		return getEmprestimoById(id);
	}
	
	public Emprestimo toEntidade(EmprestimoDTO emprestimoDTO ) {
		Emprestimo emprestimo = new Emprestimo();
		
		emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
		emprestimo.setDataEntrega(emprestimoDTO.getDataEntrega());
		emprestimo.setValorEmprestimo(emprestimoDTO.getValorEmprestimo());
		
		return emprestimo;
	}
	
	public EmprestimoDTO toDTO(Emprestimo emprestimo) {
		EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
		
		emprestimoDTO.setCodigoEmprestimo(emprestimo.getCodigoEmprestimo());
		emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoDTO.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoDTO.setValorEmprestimo(emprestimo.getValorEmprestimo());
		
		//BeanUtils.copyProperties(emprestimo, emprestimoDTO);
		
		return emprestimoDTO;
	}
	
	public EmprestimoResumoDTO toEmprestimoResumoDTO(Emprestimo emprestimo) {
		EmprestimoResumoDTO emprestimoResumoDTO = new EmprestimoResumoDTO();
		
//		emprestimoResumoDTO.setCodigoEmprestimo(emprestimo.getCodigoEmprestimo());
//		emprestimoResumoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
//		emprestimoResumoDTO.setDataEntrega(emprestimo.setDataEntrega());
		
		BeanUtils.copyProperties(emprestimo, emprestimoResumoDTO);
		
		return emprestimoResumoDTO;
	}
}