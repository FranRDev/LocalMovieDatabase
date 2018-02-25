package com.monroy.lmdb.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Clase PeliculaActor.
 * 
 * @author Francisco Rodríguez García
 */
@Entity
@Table(name="pelicula_actor")
public class PeliculaActor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//========================================================================================//
	// ATRIBUTOS
	//========================================================================================//
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACTOR")
	private Actor actor;
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_PELICULA")
	private Pelicula pelicula;

	//========================================================================================//
	// CONSTRUCTORES
	//========================================================================================//
	/**
	 * Constructor de PeliculaActor vacio.
	 */
	public PeliculaActor() {
	}

	/**
	 * Constructor de PeliculaActor con actor y pelicula.
	 * @param actor Actor.
	 * @param pelicula Pelicula.
	 */
	public PeliculaActor(Actor actor, Pelicula pelicula) {
		this.actor = actor;
		this.pelicula = pelicula;
	}

	//========================================================================================//
	// GETTERS Y SETTERS
	//========================================================================================//
	/**
	 * Get del actor.
	 * @return Devuelve el actor.
	 */
	public Actor getActor() {
		return actor;
	}

	/**
	 * Set del actor.
	 * @param actor Actor a establecer.
	 */
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	/**
	 * Get de la pelicula.
	 * @return Devuelve la pelicula.
	 */
	public Pelicula getPelicula() {
		return pelicula;
	}

	/**
	 * Set de la pelicula.
	 * @param pelicula Pelicula a establecer.
	 */
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	//========================================================================================//
	// MÉTODOS SOBREESCRITOS
	//========================================================================================//
	/**
	 * To String de PeliculaActor.
	 */
	@Override
	public String toString() {
		return actor.toString();
	}
}