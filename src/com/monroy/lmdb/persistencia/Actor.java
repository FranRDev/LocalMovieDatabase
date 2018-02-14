package com.monroy.lmdb.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Clase Actor.
 * @author Francisco Rodríguez García
 */
@Entity
@Table(name="actor")
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	// ATRIBUTOS
	@Id
	@Column(name="ID_ACTOR")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	@NotBlank
	@Size(min=1,max=50)
	@Column(name="NOMBRE")
	private String nombre;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ACTOR")
	private List<PeliculaActor> peliculas;
	
	// CONSTRUCTORES
	/**
	 * Constructor de Actor sin parametros.
	 */
	public Actor() {
	}
	
	/**
	 * Constructor de Actor con nombre.
	 * @param nombre Nombre del actor.
	 */
	public Actor(String nombre) {
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
	 * Get de la lista PeliculaActor.
	 * @return Devuelve la lista PeliculaActor.
	 */
	public List<PeliculaActor> getPeliculas() {
		return peliculas;
	}

	/**
	 * Set de la lista PeliculaActor.
	 * @param peliculas Lista PeliculaActor que establece.
	 */
	public void setPeliculas(List<PeliculaActor> peliculas) {
		this.peliculas = peliculas;
	}
}