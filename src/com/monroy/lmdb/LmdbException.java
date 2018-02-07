package com.monroy.lmdb;

public class LmdbException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public LmdbException(String mensaje){
		super(mensaje);
	}
}