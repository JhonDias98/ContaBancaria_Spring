package com.orangetalents.contaBancaria.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	@NotEmpty(message = "Campo nome é obrigatório")
	private String nome;
	
	@Column(nullable = false, unique = true)
	@Length(min = 15, max = 100)
	@Email(message = "Email inválido")
	@NotEmpty(message = "Campo email é obrigatório")
	private String email;
	
	@Column( nullable = false, unique = true, length = 11)
	@NotEmpty(message = "campo cpf é obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "conta_fk"))
	@JsonIgnoreProperties("pessoa")
	private Conta conta;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
}