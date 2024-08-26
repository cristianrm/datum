package com.teste.digio.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.digio.dto.ClienteFielDTO;
import com.teste.digio.dto.CompraDetalhesDTO;
import com.teste.digio.model.Cliente;
import com.teste.digio.model.Compra;
import com.teste.digio.model.Produto;
import com.teste.digio.repository.ComprasClienteRepository;
import com.teste.digio.repository.ProdutoRepository;
import com.teste.digio.service.ComprasService;

@Service
public class ComprasServiceImpl implements ComprasService {

	@Autowired
	private ComprasClienteRepository comprasClienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	private static final Logger logger = LogManager.getLogger(ComprasServiceImpl.class);

	@Override
	public List<CompraDetalhesDTO> getComprasOrdenadasPorValor() {
		List<CompraDetalhesDTO> comprasDetalhes = new ArrayList<CompraDetalhesDTO>();
		try {
			List<Cliente> clienteList = comprasClienteRepository.findAll();
			Map<Integer, Produto> produtoMap = getProdutos();
			
			logger.debug("NumberFormatException");

			for (Cliente cliente : clienteList) {
				for (Compra compra : cliente.getCompras()) {
					Produto produto = produtoMap.get(Integer.parseInt(compra.getCodigo()));
					if (produto != null) {
						CompraDetalhesDTO dto = new CompraDetalhesDTO();
						dto.setNomeCliente(cliente.getNome());
						dto.setCpfCliente(cliente.getCpf());
						dto.setCodigoProduto(produto.getCodigo());
						dto.setQuantidade(compra.getQuantidade());
						dto.setValorTotal(compra.getQuantidade() * produto.getPreco());
						comprasDetalhes.add(dto);
					}
				}
			}

			comprasDetalhes.sort(Comparator.comparingDouble(CompraDetalhesDTO::getValorTotal));

		} catch (NumberFormatException e) {
			System.err.println("Erro de formato do código: " + e.getMessage());
			logger.error("NumberFormatException", e);
		} catch (NullPointerException e) {
			System.err.println("Erro null: " + e.getMessage());
			logger.error("NullPointerException", e);
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
			logger.error("Exception", e);
		}
		return comprasDetalhes;
	}

	public CompraDetalhesDTO getMaiorCompraDoAno(int ano) {
		CompraDetalhesDTO maiorCompra = null;
		try {
			Map<Integer, Produto> produtoMap = getProdutos();
			List<CompraDetalhesDTO> comprasDetalhadas = new ArrayList<CompraDetalhesDTO>();

			for (Cliente cliente : comprasClienteRepository.findAll()) {
				for (Compra compra : cliente.getCompras()) {
					Produto produto = produtoMap.get(Integer.parseInt(compra.getCodigo()));
					if (produto != null && produto.getAnoCompra() != null && produto.getAnoCompra().equals(ano)) {
						CompraDetalhesDTO dto = new CompraDetalhesDTO();
						dto.setNomeCliente(cliente.getNome());
						dto.setCpfCliente(cliente.getCpf());
						dto.setCodigoProduto(produto.getCodigo());
						dto.setQuantidade(compra.getQuantidade());
						dto.setValorTotal(compra.getQuantidade() * produto.getPreco());
						comprasDetalhadas.add(dto);
					}
				}
			}

			maiorCompra = comprasDetalhadas.stream().max(Comparator.comparingDouble(CompraDetalhesDTO::getValorTotal))
					.orElse(null);

		} catch (NumberFormatException e) {
			System.err.println("Erro no código: " + e.getMessage());
			logger.error("NumberFormatException", e);
		} catch (NullPointerException e) {
			System.err.println("Erro null: " + e.getMessage());
			logger.error("NullPointerException", e);
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
			logger.error("Exception", e);
		}
		return maiorCompra;
	}

	private Map<Integer, Produto> getProdutos() {
		Map<Integer, Produto> produtoMap = produtoRepository.findAll().stream()
				.collect(Collectors.toMap(Produto::getCodigo, produto -> produto));
		return produtoMap;
	}

	@Override
	public List<ClienteFielDTO> getTop3ClientesMaisFieis() {
		List<ClienteFielDTO> top3Clientes = new ArrayList<ClienteFielDTO>();
		try {
			Map<Integer, Produto> produtoMap = getProdutos();

			Map<Cliente, Double> clienteTotalGasto = comprasClienteRepository.findAll().stream().collect(Collectors
					.toMap(cliente -> cliente, cliente -> cliente.getCompras().stream().mapToDouble(compra -> {
						Produto produto = produtoMap.get(Integer.parseInt(compra.getCodigo()));
						return (produto != null) ? compra.getQuantidade() * produto.getPreco() : 0.0;
					}).sum()));

			top3Clientes = clienteTotalGasto.entrySet().stream()
					.sorted(Map.Entry.<Cliente, Double>comparingByValue().reversed()).limit(3).map(entry -> {
						Cliente cliente = entry.getKey();
						ClienteFielDTO dto = new ClienteFielDTO();
						dto.setNome(cliente.getNome());
						dto.setCpf(cliente.getCpf());
						dto.setTotalGasto(entry.getValue());
						return dto;
					}).collect(Collectors.toList());

		} catch (NumberFormatException e) {
			System.err.println("Erro no código:" + e.getMessage());
			logger.error("NumberFormatException", e);
		} catch (NullPointerException e) {
			System.err.println("Erro null:" + e.getMessage());
			logger.error("NullPointerException", e);
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
			logger.error("Exception", e);
		}
		return top3Clientes;
	}

	public Produto recomendarVinhoPorMaiorQuantidade(String cpfCliente) {
		Produto produtoRecomendado = null;
		try {
			List<Cliente> clientesList = comprasClienteRepository.findAll();
			List<Produto> produtosList = produtoRepository.findAll();

			Cliente cliente = clientesList.stream().filter(c -> c.getCpf().equals(cpfCliente)).findFirst().orElse(null);

			if (cliente == null) {
				return null;
			}

			Compra compraMaiorQuantidade = cliente.getCompras().stream()
					.max((c1, c2) -> Integer.compare(c1.getQuantidade(), c2.getQuantidade())).orElse(null);

			if (compraMaiorQuantidade == null) {
				return null;
			}

			produtoRecomendado = produtosList.stream()
					.filter(p -> p.getCodigo() == Integer.parseInt(compraMaiorQuantidade.getCodigo())).findFirst()
					.orElse(null);

		} catch (NumberFormatException e) {
			System.err.println("Erro no código: " + e.getMessage());
			logger.error("NumberFormatException", e);
		} catch (NullPointerException e) {
			System.err.println("Erro Null: " + e.getMessage());
			logger.error("NullPointerException", e);
		} catch (Exception e) {
			System.err.println("Erro:: " + e.getMessage());
			logger.error("Exception", e);
		}
		return produtoRecomendado;
	}

}
