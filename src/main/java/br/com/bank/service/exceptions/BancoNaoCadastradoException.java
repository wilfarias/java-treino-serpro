package br.com.bank.service.exceptions;

public class BancoNaoCadastradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BancoNaoCadastradoException(String msg) {
		super(msg);
	}

}
