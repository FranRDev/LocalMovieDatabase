package com.monroy.lmdb.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="DIRECTOR")
public class Director implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	@Id
	@Column(name="ID_DIRECTOR")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	@NotBlank
	@Size(min=1,max=50)
	@Column(name="NOMBRE")
	private String nombre;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_DIRECTOR")
	private List<Pelicula> peliculas;

	// CONSTRUCTORES
	public Director() {
	}

	public Director(String nombre) {
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

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	// MÉTODOS SOBREESCRITOS
	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + id;
		return resultado;
	}

	@Override
	public boolean equals(Object objeto) {
		if (this == objeto)
			return true;
		if (objeto == null)
			return false;
		if (getClass() != objeto.getClass())
			return false;
		Director otro = (Director) objeto;
		if (id != otro.id)
			return false;
		return true;
	}
}