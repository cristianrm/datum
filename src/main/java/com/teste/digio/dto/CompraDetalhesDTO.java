package com.teste.digio.dto;

import java.io.Serializable;

public class CompraDetalhesDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeCliente;
    private String cpfCliente;
    private Integer codigoProduto;
    private Integer quantidade;
    private Double valorTotal;
    
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public Integer getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
    
    

}
