package com.teste.digio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.digio.model.Cliente;
import com.teste.digio.model.Produto;
import com.teste.digio.repository.ComprasClienteRepository;
import com.teste.digio.repository.ProdutoRepository;
import com.teste.digio.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository vinhoRepository;

	@Autowired
	private ComprasClienteRepository comprasClienteRepository;

	public List<Produto> getAllProdutos() {
		return vinhoRepository.findAll();
	}

	@Override
	public List<Cliente> getAllClientesCompras() {
		return comprasClienteRepository.findAll();
	}

}
