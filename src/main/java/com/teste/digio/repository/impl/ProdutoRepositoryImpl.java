package com.teste.digio.repository.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.teste.digio.model.Produto;
import com.teste.digio.repository.ProdutoRepository;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

	private final RestTemplate restTemplate;

	private static final String URL = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json";
	
	private static final Logger logger = LogManager.getLogger(ProdutoRepositoryImpl.class);

	@Autowired
	public ProdutoRepositoryImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	@Override
	public List<Produto> findAll() {
		List<Produto> produtos = Collections.emptyList();
		try {
			Produto[] produtosArray = restTemplate.getForObject(URL, Produto[].class);
			if (produtosArray != null) {
				produtos = Arrays.asList(produtosArray);
			}
		} catch (RestClientException e) {
			System.err.println("Erro ao chamar o serviço: " + e.getMessage());
			logger.error("RestClientException", e);
		} catch (Exception e) {
			logger.error("Exception", e);
			System.err.println("Erro: " + e.getMessage());
		}
		return produtos;
	}

}
