package com.orangetalents.contaBancaria.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.orangetalents.contaBancaria.model.Conta;
import com.orangetalents.contaBancaria.model.Pessoa;
import com.orangetalents.contaBancaria.repository.PessoaRepository;
import com.orangetalents.contaBancaria.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@PostMapping("/cadastrar")
	public ResponseEntity<Conta> Post(@RequestBody @Valid Pessoa pessoa) {
		
		if(pessoaRepository.existsByEmail(pessoa.getEmail())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já existente");
	    }
		
		if(pessoaRepository.existsByCpf(pessoa.getCpf())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já existente");
	    }
		
		if(!pessoaService.maiorIdade(pessoa)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Menor de idade");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.cadastrarPessoa(pessoa));
	}
	
	@GetMapping("/buscar/{cpf}")
	public ResponseEntity<Pessoa> GetByCpf(@PathVariable String cpf) {
		if(!pessoaRepository.existsByCpf(cpf)) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF não encontrado");
	    }
		return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.findOneByCpf(cpf));
	}
	
	@GetMapping("/buscar/{nome}")
	public ResponseEntity<List<Pessoa>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(pessoaRepository.findAllByNomeContainingIgnoreCase(nome));
	}
}
