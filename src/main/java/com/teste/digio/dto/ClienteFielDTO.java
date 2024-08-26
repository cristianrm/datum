package com.teste.digio.dto;

import java.io.Serializable;

public class ClienteFielDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
    private String cpf;
    private Double totalGasto;
    
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
	public Double getTotalGasto() {
		return totalGasto;
	}
	public void setTotalGasto(Double totalGasto) {
		this.totalGasto = totalGasto;
	}
    
    

}
