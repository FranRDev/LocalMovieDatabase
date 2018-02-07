package com.monroy.lmdb.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Clase Director.
 * @author Francisco Rodríguez García
 */
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
	/**
	 * Constructor vacío de Director.
	 */
	public Director() {
	}

	/**
	 * Constructor de Director con nombre.
	 * @param nombre Nombre del director.
	 */
	public Director(String nombre) {
		this.nombre = nombre;
	}

	// GETTERS Y SETTERS
	/**
	 * Get del ID.
	 * @return Devuelve el ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set del ID.
	 * @param id ID que establece.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get del nombre.
	 * @return Devuelve el nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Set del nombre.
	 * @param nombre Nombre que establece.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Get de la lista de peliculas.
	 * @return Devuelve la lista de peliculas.
	 */
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	/**
	 * Set de la lista de peliculas.
	 * @param peliculas Lista de peliculas que establece.
	 */
	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	// MÉTODOS SOBREESCRITOS
	/**
	 * Metodo hashCode sobreescrito.
	 */
	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + id;
		return resultado;
	}

	/**
	 * Metodo equals sobreescrito.
	 */
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