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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase Película.
 * 
 * @author Francisco Rodríguez García.
 */
@Entity
@Table(name="pelicula")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//========================================================================================//
	// ATRIBUTOS
	//========================================================================================//
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
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_DIRECTOR")
	private Director director;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Genero genero;
	
	@OneToMany(mappedBy="pelicula", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<PeliculaActor> actores;
	
	//========================================================================================//
	// CONSTRUCTORES
	//========================================================================================//
	/**
	 * Cosntructor vacío de Película.
	 */
	public Pelicula() {}

	/**
	 * Constructor de película con el título en España, el original, el año, la duración, el país, el director, el género, y el reparto.
	 * @param tituloEspanha Título en España de la película.
	 * @param tituloOriginal Título original de la película.
	 * @param anho Año de la película.
	 * @param duracion Duración de la película.
	 * @param pais País de la película.
	 * @param director Director de la película.
	 * @param genero Género de la película.
	 * @param actores Reparto de la película.
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

	//========================================================================================//
	// GETTERS Y SETTERS
	//========================================================================================//
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
	
	//========================================================================================//
	// GETTERS PROPERTY --> JAVAFX
	//========================================================================================//
	/**
	 * Get del Property de ID.
	 * @return Devuelve el Property del ID.
	 */
	public StringProperty getIdProperty() {
		return new SimpleStringProperty(String.valueOf(this.id));
	}
	
	/**
	 * Get del Property de Título en España.
	 * @return Devuelve el Property del Título en España.
	 */
	public StringProperty getTituloEspanhaProperty() {
		return new SimpleStringProperty(this.tituloEspanha);
	}
	
	/**
	 * Get del Property de Título original.
	 * @return Devuelve el Property del Título original.
	 */
	public StringProperty getTituloOriginalProperty() {
		return new SimpleStringProperty(this.tituloOriginal);
	}
	
	/**
	 * Get del Property de Año.
	 * @return Devuelve el Property de Año.
	 */
	public StringProperty getAnhoProperty() {
		SimpleDateFormat formatoAnho;
		
		formatoAnho = new SimpleDateFormat("yyyy");
		
		return new SimpleStringProperty(formatoAnho.format(this.anho));
	}
	
	/**
	 * Get del Property de Duración.
	 * @return Devuelve el Property de Duración.
	 */
	public IntegerProperty getDuracionProperty() {
		return new SimpleIntegerProperty(this.duracion);
	}

	/**
	 * Get del Property de País.
	 * @return Devuelve el Property de País.
	 */
	public StringProperty getPaisProperty() {
		return new SimpleStringProperty(this.pais.toString());
	}

	/**
	 * Get del Property de Director.
	 * @return Devuelve el Property de Director.
	 */
	public StringProperty getDirectorProperty() {
		String nombreDirector;
		
		if (this.director.getNombre() == null) {
			nombreDirector = "Sin asignar";
			
		} else {
			nombreDirector = this.director.getNombre();
		}
		
		return new SimpleStringProperty(nombreDirector);
	}
	
	/**
	 * Get del Property de Género.
	 * @return Devuelve el Property de Género.
	 */
	public StringProperty getGeneroProperty() {
		return new SimpleStringProperty(this.genero.toString());
	}
	
	/**
	 * Get del Property de Reparto.
	 * @return Devuelve el Property de Reparto.
	 */
	public StringProperty getRepartoProperty() {
		String cadenaReparto;
		
		if (this.actores == null) {
			cadenaReparto = "Sin asignar";
			
		} else {
			cadenaReparto = this.cadenaActores();
		}
		
		return new SimpleStringProperty(cadenaReparto);
	}

	//========================================================================================//
	// MÉTODOS SOBREESCRITOS
	//========================================================================================//
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
	
	//========================================================================================//
	// MÉTODOS
	//========================================================================================//
	/**
	 * Metodo que devuelve una cadena con el reparto.
	 * @return Devuelve una cadena.
	 */
	public String cadenaActores() {
		StringBuilder cadenaActores = new StringBuilder();
		String cadenaFinal = "";
		
		for (PeliculaActor peliculaActor : actores) {
			cadenaActores.append(peliculaActor.getActor().getNombre() + ", ");
		}
		
		if (cadenaActores.length() > 0) {
			cadenaFinal = cadenaActores.substring(0, (cadenaActores.length() - 2));
		}
		
		return cadenaFinal;
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