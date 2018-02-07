package com.monroy.lmdb.persistencia;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="PELICULA")
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
	public Pelicula() {
	}

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
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getTituloEspanha() {
		return tituloEspanha;
	}

	public void setTituloEspanha(String tituloEspanha) {
		this.tituloEspanha = tituloEspanha;
	}

	public String getTituloOriginal() {
		return tituloOriginal;
	}

	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}

	public Date getAnho() {
		return anho;
	}

	public void setAnho(Date anho) {
		this.anho = anho;
	}

	public Set<PeliculaActor> getActores() {
		return actores;
	}

	public void setActores(Set<PeliculaActor> actores) {
		this.actores = actores;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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
		Pelicula otro = (Pelicula) objeto;
		if (id != otro.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatoAnho;
		
		formatoAnho = new SimpleDateFormat("yyyy");
		
		return tituloEspanha + " (" + tituloOriginal + ") (" + Integer.parseInt(formatoAnho.format(anho)) + ") (" + pais + ")";
	}
	
	// MÉTODOS
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
	
	@AssertTrue(message="El año no es válido")
	private boolean isValidoAnho() {
		boolean esValido;
		String expresionRegular = "\\s\\d{4}";
		
		if (Pattern.matches(expresionRegular, this.anho.toString())) {
			esValido = true;
			
		} else {
			esValido = false;
		}
		
		return esValido;
	}
}