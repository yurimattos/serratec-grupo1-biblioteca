package br.com.residencia.biblioteca.dto;

import java.util.List;

public class AlunoEmprestimoDTO {
	private Integer numeroMatriculaAluno;
	private String nome;
	private String cpf;
	private List<EmprestimoResumoDTO> listaEmprestimosResumoDTO;
	
	public Integer getNumeroMatriculaAluno() {
		return numeroMatriculaAluno;
	}
	
	public void setNumeroMatriculaAluno(Integer numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<EmprestimoResumoDTO> getListaEmprestimosResumoDTO() {
		return listaEmprestimosResumoDTO;
	}

	public void setListaEmprestimosResumoDTO(List<EmprestimoResumoDTO> listaEmprestimosResumoDTO) {
		this.listaEmprestimosResumoDTO = listaEmprestimosResumoDTO;
	}
	
	
}
