package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.AlunoEmprestimoDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.dto.EmprestimoResumoDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.AlunoRepository;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class AlunoService {
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	EmprestimoService emprestimoService;
	
	public List<Aluno> getAllAlunos(){
		return alunoRepository.findAll();
	}
	
	public List<AlunoEmprestimoDTO> getAllAlunosEmprestimosDTO(){
		List<Aluno> listaAlunos = alunoRepository.findAll();
		List<AlunoEmprestimoDTO> listAlunosEmprestimoDTO = new ArrayList<>();
		
		for(Aluno aluno : listaAlunos) {
			AlunoEmprestimoDTO alunoEmprestimoDTO = toAlunoEmprestimoDTO(aluno);
			List<Emprestimo> listaEmprestimos = new ArrayList<>();
			List<EmprestimoResumoDTO> listaEmprestimosResumoDTO = new ArrayList<>();
			
			listaEmprestimos = emprestimoRepository.findByAluno(aluno);
			
			for(Emprestimo emprestimo: listaEmprestimos) {
				EmprestimoResumoDTO emprestimoResumoDTO = emprestimoService.toEmprestimoResumoDTO(emprestimo);
				listaEmprestimosResumoDTO.add(emprestimoResumoDTO);
			}
			
			alunoEmprestimoDTO.setListaEmprestimosResumoDTO(listaEmprestimosResumoDTO);
			
			listAlunosEmprestimoDTO.add(alunoEmprestimoDTO);
		}
		
		return listAlunosEmprestimoDTO;
	}
	
	public Aluno getAlunoById(Integer id) {
		//return alunoRepository.findById(id).get();
		return alunoRepository.findById(id).orElse(null);
	}
	
	public Aluno saveAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public Aluno updateAluno(Aluno aluno, Integer id) {
		//Aluno alunoExistenteNoBanco = alunoRepository.findById(id).get();
		
		Aluno alunoExistenteNoBanco = getAlunoById(id);

		alunoExistenteNoBanco.setBairro(aluno.getBairro()); //Centro
		alunoExistenteNoBanco.setCidade(aluno.getCidade()); //Petropolis
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento()); //SN
		alunoExistenteNoBanco.setCpf(aluno.getCpf()); //123456789
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento()); //...
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());
		
		return alunoRepository.save(alunoExistenteNoBanco);
		
		//return alunoRepository.save(aluno);
	}
	
	private Aluno toAluno(AlunoDTO alunoDTO ) {
		Aluno aluno = new Aluno();
		
		aluno.setNumeroMatriculaAluno(alunoDTO.getNumeroMatriculaAluno());
		aluno.setNome(alunoDTO.getNome());
		aluno.setCidade(alunoDTO.getCidade());
		aluno.setBairro(alunoDTO.getBairro());
		aluno.setComplemento(alunoDTO.getComplemento());
		aluno.setCpf(alunoDTO.getCpf());
		aluno.setDataNascimento(alunoDTO.getDataNascimento());
		aluno.setLogradouro(alunoDTO.getLogradouro());
		aluno.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());
		
		return aluno;
	}
	
	private AlunoDTO toDTO(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
		
		alunoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		alunoDTO.setNome(aluno.getNome());
		alunoDTO.setCidade(aluno.getCidade());
		alunoDTO.setBairro(aluno.getBairro());
		alunoDTO.setComplemento(aluno.getComplemento());
		alunoDTO.setCpf(aluno.getCpf());
		alunoDTO.setDataNascimento(aluno.getDataNascimento());
		alunoDTO.setLogradouro(aluno.getLogradouro());
		alunoDTO.setNumeroLogradouro(aluno.getNumeroLogradouro());
		
		//BeanUtils.copyProperties(editora, editoraDTO);
		
		return alunoDTO;
	}
	
	private AlunoEmprestimoDTO toAlunoEmprestimoDTO(Aluno aluno) {
		AlunoEmprestimoDTO alunoEmprestimoDTO = new AlunoEmprestimoDTO();
		
//		alunoEmprestimoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
//		alunoEmprestimoDTO.setNome(aluno.getNome());
//		alunoEmprestimoDTO.setCpf(aluno.getCpf());
		
		BeanUtils.copyProperties(aluno, alunoEmprestimoDTO);
		
		return alunoEmprestimoDTO;
	}
	

	public Aluno deleteAluno(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
	}
	
}