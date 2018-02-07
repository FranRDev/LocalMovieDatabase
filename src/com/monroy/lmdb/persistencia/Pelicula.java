package com.monroy.lmdb.persistencia;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="PELICULA")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	@Id
	@Column(name="ID_PELICULA")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="TITULO_ESPANHA")
	private String tituloEspanha;
	
	@Column(name="TITULO_ORIGINAL")
	private String tituloOriginal;
	
	@Column(name="ANHO")
	private Date anho;
	
	@Column(name="DURACION")
	private int duracion;
	
	@Enumerated(EnumType.ORDINAL)
	private Pais pais;
	
	@ManyToOne
	@JoinColumn(name="ID_DIRECTOR")
	private Director director;
	
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
}