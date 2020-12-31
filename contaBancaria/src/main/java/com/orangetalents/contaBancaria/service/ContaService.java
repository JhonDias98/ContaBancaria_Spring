package com.orangetalents.contaBancaria.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangetalents.contaBancaria.model.Conta;
import com.orangetalents.contaBancaria.model.Pessoa;
import com.orangetalents.contaBancaria.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	public Conta gerarConta(Pessoa pessoa) {
		Conta novaConta = new Conta();
		
		String conta = Integer.toString(gerarNumerosConta(1000000, 999999999));
		String agencia = Integer.toString(gerarNumerosConta(10000, 999999));
		String senha = Integer.toString(gerarNumerosConta(100000, 999999));
		
		novaConta.setNumeroAgencia(agencia);
		novaConta.setNumeroConta(conta);
		novaConta.setSenha(senha);
		novaConta.setPessoa(pessoa);
		novaConta.setSaldo(0);
		
		return repository.save(novaConta);
	}
	
	public int gerarNumerosConta(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }
	
	public Conta depositar(Conta conta, double valor) {
		conta.setSaldo(conta.getSaldo() + valor);
		
		return repository.save(conta);
	}
	
	public Conta sacar(Conta conta, double valor) {
		conta.setSaldo(conta.getSaldo() - valor);
		
		return repository.save(conta);
	}
	
	public Conta transferir(Conta conta, double valor, Long idContaDestino) {
		Conta contaAtual = repository.findById(conta.getId()).get();
		Conta contaDestino = repository.findById(idContaDestino).get();
		
		sacar(contaAtual, valor);
		depositar(contaDestino, valor);
		
		repository.save(contaDestino);
		
		return repository.save(conta);
	}
} 