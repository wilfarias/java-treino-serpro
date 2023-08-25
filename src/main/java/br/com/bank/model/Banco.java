package br.com.bank.model;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {

	private static final Double saldoMinimoContaAltaRenda = 100000.0;
	
	private String nome;

	public Banco(String nome) {
		this.nome = nome;
	}

	private HashMap<String,Conta> contas = new HashMap<>();

	public void adicionarConta(Conta conta) {
		contas.put(conta.getCpf(), conta);
	}

	public Optional<Conta> pesquisarContaDoCliente(String cpf) {		
		Optional<Conta> conta = Optional.ofNullable(contas.get(cpf));		
		return conta;
	}

	public List<Conta> listarContasAltaRenda() {
		return filtrarContas(conta -> conta.getSaldo() >= saldoMinimoContaAltaRenda);
	}

	private List<Conta> filtrarContas(Predicate<Conta> filtro) {
		return contas.values().stream().filter(filtro).collect(Collectors.toList());
	}
	
	public int getTotalContas() {
		return contas.size();
	}
}
