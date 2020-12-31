package com.orangetalents.contaBancaria.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangetalents.contaBancaria.model.Conta;
import com.orangetalents.contaBancaria.model.Pessoa;
import com.orangetalents.contaBancaria.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	ContaService contaService;
	
	public Conta cadastrarPessoa(Pessoa pessoa) {
		repository.save(pessoa);
		return contaService.gerarConta(pessoa);
	}
	
	public boolean maiorIdade(Pessoa pessoa) {	
		LocalDate hoje = LocalDate.now();
		
		LocalDate dataNascimento = pessoa.getDataNascimento();
		
		Period periodo = Period.between(dataNascimento, hoje);
		
		if(periodo.getYears() >= 18) {
			return true;
		}
		return false;
	}
}