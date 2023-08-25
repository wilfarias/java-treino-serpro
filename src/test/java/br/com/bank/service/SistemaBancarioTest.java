package br.com.bank.service;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import br.com.bank.service.exceptions.BancoNaoCadastradoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

    @InjectMocks
    private SistemaBancario sistemaBancario;
    @Mock
    private Bacen bacen;
	
	//private SistemaBancario sistemaBancario;
	//private BacenStub stub;
    //private BacenFake fake;
	
    private Banco bancoValido;
	private Banco bancoInvalido;
	
	@BeforeEach
	void setUp() throws Exception{
		bancoValido = new Banco("Itaú");
		bancoInvalido = new Banco("Nubank");
		sistemaBancario = new SistemaBancario(bacen);
		
		Mockito.when(bacen.cadastrarBanco(bancoValido)).thenReturn(1L);
		
		Mockito.when(bacen.cadastrarBanco(bancoInvalido)).thenThrow(BancoNaoCadastradoException.class);
		
	}
	
	/*@Test
	public void deve_retornar_o_numero_de_registro_do_bancoFake() {
		fake =  new BacenFake();
		SistemaBancario sistemaBancario = new SistemaBancario(fake);
		Long itau = sistemaBancario.registrarBanco(new Banco("Itaú"));
		Long nubank = sistemaBancario.registrarBanco(new Banco("Nubank"));
		
		assertEquals(1, itau);
		assertEquals(2, nubank);
	}*/
	
	/*@Test
	public void deve_retornar_o_numero_de_registro_do_bancoStub() {
		stub =  new BacenStub();
		SistemaBancario sistemaBancario = new SistemaBancario(stub);
		Long result = sistemaBancario.registrarBanco(new Banco("Itaú"));
		
		assertEquals(1, result);
	}*/
	
	@Test
	public void deve_retornar_o_numero_de_registro_do_bancoMock() {
		
		Long result = sistemaBancario.registrarBanco(bancoValido);
		
		Mockito.verify(bacen, times(1)).cadastrarBanco(bancoValido);
		Assertions.assertEquals(1, result);
	}
	
	@Test
	public void deve_retornar_BancoNaoCadastradoException_quando_bancoMock_invalido() {
		
		Assertions.assertThrows(BancoNaoCadastradoException.class,() -> {
			sistemaBancario.registrarBanco(bancoInvalido);
		});
		
		Mockito.verify(bacen, times(1)).cadastrarBanco(bancoInvalido);
		
	}

}