package br.com.bank.service;


import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenStub extends Bacen {
	
	public long cadastrarBanco(Banco banco) {
		return 1;
	}

}
