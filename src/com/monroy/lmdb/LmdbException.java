package com.monroy.lmdb;

/**
 * Exepción de LocalMovieDatabase.
 * 
 * @author Francisco Rodríguez García
 */
public class LmdbException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * @param mensaje Mensaje que recibe.
	 */
	public LmdbException(String mensaje){
		super(mensaje);
	}
}