package br.com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BancoTest {
	
	private Banco banco;
	private Conta conta;
	
	@BeforeEach
	void setUp() throws Exception{
		banco = new Banco("Itau");		
	}
	
	@Test
	public void testAdicionarConta() {
		conta = new Conta("12345678923");
		banco.adicionarConta(conta);
		
		assertEquals(1, banco.getTotalContas());
	}
	
	@Test
	public void testPesquisarContaDoClienteDeveRetornarUmaContaQuandoReceberUmCPF() {
		conta = new Conta("12345678923");
		banco.adicionarConta(conta);
		Optional<Conta> result = banco.pesquisarContaDoCliente("12345678923");
		
		assertTrue(result.isPresent());
		assertEquals(result.get(), conta);
	}
	
	@Test
	public void testListarContasAltaRendaDeveListarAsContasComSaldoMaiorQueDezMil() {
		Conta conta1 = new Conta("12345678923");
		Conta conta2 = new Conta("12378901234");
		Conta conta3 = new Conta("09876543212");
		conta1.depositar(500000.0);
		conta2.depositar(100001.0);
		conta3.depositar(1004.0);		
		banco.adicionarConta(conta1);
		banco.adicionarConta(conta2);
		banco.adicionarConta(conta3);
		
		List<Conta> result = banco.listarContasAltaRenda();
		
		assertEquals(2, result.size());
		
	}

}
