package com.teste.digio.service;

import java.util.List;

import com.teste.digio.dto.ClienteFielDTO;
import com.teste.digio.dto.CompraDetalhesDTO;
import com.teste.digio.model.Produto;

public interface ComprasService {

	 List<CompraDetalhesDTO> getComprasOrdenadasPorValor();
	 
	 CompraDetalhesDTO getMaiorCompraDoAno(int ano);
	 
	 List<ClienteFielDTO> getTop3ClientesMaisFieis();
	 
	 Produto recomendarVinhoPorMaiorQuantidade(String cpfCliente);
	 
}
