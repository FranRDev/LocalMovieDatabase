package com.monroy.lmdb.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="ACTOR")
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	// ATRIBUTOS
	@Id
	@Column(name="ID_ACTOR")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ACTOR")
	private List<PeliculaActor> peliculas;
	
	// CONSTRUCTORES
	public Actor() {
	}
	
	public Actor(String nombre) {
		this.nombre = nombre;
	}

	// GETTERS Y SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PeliculaActor> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<PeliculaActor> peliculas) {
		this.peliculas = peliculas;
	}
}