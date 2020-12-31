package com.orangetalents.contaBancaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orangetalents.contaBancaria.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	List<Pessoa> findAllByNomeContainingIgnoreCase(String nome);
	
	Pessoa findOneByCpf(String cpf);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByCpf(String cpf);
}