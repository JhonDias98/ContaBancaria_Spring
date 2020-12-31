package com.orangetalents.contaBancaria.errors;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class ApiErrors {
	
	@Getter
	private List<String> erros;
	
	public ApiErrors(List<String> errors) {
		this.erros = errors;
	}
	
	public ApiErrors(String mensagemErro) {
		this.erros = Arrays.asList(mensagemErro);
	}
}
