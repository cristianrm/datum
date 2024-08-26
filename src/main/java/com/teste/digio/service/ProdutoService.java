package com.teste.digio.service;

import java.util.List;

import com.teste.digio.model.Cliente;
import com.teste.digio.model.Produto;

public interface ProdutoService {
	
	 List<Produto> getAllProdutos();
	 
	 List<Cliente> getAllClientesCompras();

}
