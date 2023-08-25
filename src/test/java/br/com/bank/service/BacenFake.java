package br.com.bank.service;


import java.util.HashMap;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenFake extends Bacen {
	
	private HashMap<Long, Banco> map = new HashMap<>();
	
	@Override
	public long cadastrarBanco(Banco banco) {
		
		long id = map.size() + 1;
		map.put(id, banco);
		return id;
	}

}
