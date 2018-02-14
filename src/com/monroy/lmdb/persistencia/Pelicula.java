package com.monroy.lmdb.persistencia;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Clase Pelicula.
 * @author Francisco Rodríguez García.
 */
@Entity
@Table(name="pelicula")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	@Id
	@Column(name="ID_PELICULA")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Size(min=1,max=100)
	@NotBlank
	@Column(name="TITULO_ESPANHA")
	private String tituloEspanha;
	
	@NotNull
	@Size(min=1,max=100)
	@NotBlank
	@Column(name="TITULO_ORIGINAL")
	private String tituloOriginal;
	
	@NotNull
	@Column(name="ANHO")
	private Date anho;
	
	@NotNull
	@Digits(fraction = 0, integer = 3)
	@Min(value = 1)
	@Column(name="DURACION")
	private int duracion;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Pais pais;
	
	@NotNull
	@Valid
	@ManyToOne
	@JoinColumn(name="ID_DIRECTOR")
	private Director director;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Genero genero;
	
	@OneToMany(mappedBy="pelicula", cascade=CascadeType.ALL)
	private Set<PeliculaActor> actores;
	
	// CONSTRUCTORES
	/**
	 * Cosntructor vacio de Pelicula.
	 */
	public Pelicula() {
	}

	/**
	 * Constructor de pelicula con el titulo en Espanha, el original, el anho, la duracion, el pais, el director, el genero, y el reparto.
	 * @param tituloEspanha Titulo en Espanha de la pelicula.
	 * @param tituloOriginal Titulo original de la pelicula.
	 * @param anho Anho de la pelicula.
	 * @param duracion Duracion de la pelicula.
	 * @param pais Pais de la pelicula.
	 * @param director Director de la pelicula.
	 * @param genero Genero de la pelicula.
	 * @param actores Reparto de la pelicula.
	 */
	public Pelicula(String tituloEspanha, String tituloOriginal, Date anho, int duracion, Pais pais, Director director, Genero genero, Set<PeliculaActor> actores) {
		this.tituloEspanha = tituloEspanha;
		this.tituloOriginal = tituloOriginal;
		this.anho = anho;
		this.duracion = duracion;
		this.pais = pais;
		this.director = director;
		this.genero = genero;
		this.actores = actores;
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
	 * Get de la duracion.
	 * @return Devuelve la duracion.
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * Set de la duracion.
	 * @param duracion Duracion que establece.
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * Get del titulo en Espanha.
	 * @return Devuelve el titulo en Espanha.
	 */
	public String getTituloEspanha() {
		return tituloEspanha;
	}

	/**
	 * Set del titulo en Espanha.
	 * @param tituloEspanha Titulo en Espanha que establece.
	 */
	public void setTituloEspanha(String tituloEspanha) {
		this.tituloEspanha = tituloEspanha;
	}

	/**
	 * Get del titulo original.
	 * @return Devuelve el titulo original.
	 */
	public String getTituloOriginal() {
		return tituloOriginal;
	}

	/**
	 * Set del titulo original.
	 * @param tituloOriginal Titulo original que establece.
	 */
	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}

	/**
	 * Get de anho.
	 * @return Devuelve el anho.
	 */
	public Date getAnho() {
		return anho;
	}

	/**
	 * Set del anho.
	 * @param anho Anho que establece.
	 */
	public void setAnho(Date anho) {
		this.anho = anho;
	}

	/**
	 * Get de la lista PeliculaActor.
	 * @return Devuelve la lista.
	 */
	public Set<PeliculaActor> getActores() {
		return actores;
	}

	/**
	 * Set de la lista PeliculaActor.
	 * @param actores Lista que establece.
	 */
	public void setActores(Set<PeliculaActor> actores) {
		this.actores = actores;
	}

	/**
	 * Get del director.
	 * @return Devuelve el director.
	 */
	public Director getDirector() {
		return director;
	}

	/**
	 * Set del director.
	 * @param director Director a establecer.
	 */
	public void setDirector(Director director) {
		this.director = director;
	}

	/**
	 * Get del genero.
	 * @return Devuelve el genero.
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * Set del genero.
	 * @param genero Genero a establecer.
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * Get del pais.
	 * @return Devuelve el pais.
	 */
	public Pais getPais() {
		return pais;
	}

	/**
	 * Set del pais.
	 * @param pais Pais a establecer.
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
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
		Pelicula otro = (Pelicula) objeto;
		if (id != otro.id)
			return false;
		return true;
	}

	/**
	 * Metodo toString sobreescrito.
	 */
	@Override
	public String toString() {
		SimpleDateFormat formatoAnho;
		
		formatoAnho = new SimpleDateFormat("yyyy");
		
		return tituloEspanha + " (" + tituloOriginal + ") (" + Integer.parseInt(formatoAnho.format(anho)) + ") (" + pais + ")";
	}
	
	// MÉTODOS
	/**
	 * Metodo que devuelve una cadena con el reparto.
	 * @return Devuelve una cadena.
	 */
	public String cadenaActores() {
		StringBuilder cadenaActores = new StringBuilder();
		int totalActores, contador;;
		
		totalActores = actores.size();
		contador = 1;
		
		for (PeliculaActor peliculaActor : actores) {
			if (contador != totalActores) {
				cadenaActores.append(peliculaActor.getActor().getNombre() + ", ");
			} else {
				cadenaActores.append(peliculaActor.getActor().getNombre());
			}
		}
		
		return cadenaActores.toString();
	}
	
	/**
	 * Metodo que valida el anho con Hibernate.
	 * @return Devuelve true si es correcto.
	 */
	@AssertFalse(message="El año no es válido")
	private boolean isAnhoPelicula() {
		boolean esValido;
		String expresionRegular = "Sat Jan 01 00:00:00 CET \\d{4}";
		
		if (Pattern.matches(expresionRegular, this.anho.toString())) {
			esValido = true;
			
		} else {
			esValido = false;
		}
		
		return esValido;
	}
}