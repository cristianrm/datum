package com.teste.digio.repository;

import java.util.List;

import com.teste.digio.model.Produto;

public interface ProdutoRepository {
	
	 List<Produto> findAll();

}
