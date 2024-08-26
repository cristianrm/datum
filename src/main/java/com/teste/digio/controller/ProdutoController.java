package com.teste.digio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.digio.dto.ClienteFielDTO;
import com.teste.digio.dto.CompraDetalhesDTO;
import com.teste.digio.model.Cliente;
import com.teste.digio.model.Produto;
import com.teste.digio.service.ComprasService;
import com.teste.digio.service.ProdutoService;

@RestController
@RequestMapping("/")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ComprasService comprasService;

	@GetMapping()
	public List<Produto> getVinhos() {

		return produtoService.getAllProdutos();
	}

	@GetMapping("/clienteCompras")
	public List<Cliente> getClientesCompras() {

		return produtoService.getAllClientesCompras();
	}

	@GetMapping("/compras")
	public ResponseEntity<List<CompraDetalhesDTO>> getComprasOrdenadasPorValor() {
		List<CompraDetalhesDTO> compras = comprasService.getComprasOrdenadasPorValor();
		return new ResponseEntity<>(compras, HttpStatus.OK);
	}

	@GetMapping("/maior-compra/{ano}")
	public ResponseEntity<CompraDetalhesDTO> getMaiorCompraDoAno(@PathVariable int ano) {
		CompraDetalhesDTO maiorCompra = comprasService.getMaiorCompraDoAno(ano);
		if (maiorCompra != null) {
			return new ResponseEntity<>(maiorCompra, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/clientes-fieis")
	public ResponseEntity<List<ClienteFielDTO>> getTop3ClientesMaisFieis() {
		List<ClienteFielDTO> clientesFieis = comprasService.getTop3ClientesMaisFieis();
		if (clientesFieis != null && !clientesFieis.isEmpty()) {
			return new ResponseEntity<>(clientesFieis, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/recomendacao/cliente/tipo/{cpfCliente}")
	public ResponseEntity<Produto> recomendarVinhoPorMaiorQuantidade(@PathVariable String cpfCliente) {
		Produto produto = comprasService.recomendarVinhoPorMaiorQuantidade(cpfCliente);
		if (produto != null) {
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
