package com.teste.digio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Produto {

	private Integer codigo;
	
	@JsonProperty("tipo_vinho")
	private String tipoVinho;
	
	private Double preco;
	
	private String safra;

	@JsonProperty("ano_compra")
	private Integer anoCompra;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTipoVinho() {
		return tipoVinho;
	}

	public void setTipoVinho(String tipoVinho) {
		this.tipoVinho = tipoVinho;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getSafra() {
		return safra;
	}

	public void setSafra(String safra) {
		this.safra = safra;
	}

	public Integer getAnoCompra() {
		return anoCompra;
	}

	public void setAnoCompra(Integer anoCompra) {
		this.anoCompra = anoCompra;
	}

}
