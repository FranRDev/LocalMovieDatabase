package com.monroy.lmdb.persistencia;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="PELICULA_ACTOR")
public class PeliculaActor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	@Id
	@ManyToOne
	@JoinColumn(name="ID_ACTOR")
	private Actor actor;
	
	@Id
	@ManyToOne
	@JoinColumn(name="ID_PELICULA")
	private Pelicula pelicula;

	// CONSTRUCTORES
	public PeliculaActor() {
	}

	public PeliculaActor(Actor actor, Pelicula pelicula) {
		this.actor = actor;
		this.pelicula = pelicula;
	}

	// GETTERS Y SETTERS
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
}