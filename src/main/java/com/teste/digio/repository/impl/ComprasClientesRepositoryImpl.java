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

import com.teste.digio.model.Cliente;
import com.teste.digio.repository.ComprasClienteRepository;

@Repository
public class ComprasClientesRepositoryImpl implements ComprasClienteRepository {

	private final RestTemplate restTemplate;

	private static final String URL = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json";

	private static final Logger logger = LogManager.getLogger(ComprasClientesRepositoryImpl.class);

	@Autowired
	public ComprasClientesRepositoryImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Cliente> findAll() {
		List<Cliente> clientes = Collections.emptyList();
		try {
			Cliente[] comprasClienteArray = restTemplate.getForObject(URL, Cliente[].class);
			if (comprasClienteArray != null) {
				clientes = Arrays.asList(comprasClienteArray);
			}
		} catch (RestClientException e) {
			System.err.println("Erro ao chamar o serviço: " + e.getMessage());
			logger.error("RestClientException", e);
		} catch (Exception e) {
			logger.error("Exception", e);
			System.err.println("Erro: " + e.getMessage());
		}
		return clientes;
	}

}
