package com.monroy.lmdb.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.Principal;
import com.monroy.lmdb.persistencia.HibernateUtil;
import com.monroy.lmdb.persistencia.Pelicula;

/**
 * Clase que gestiona las transacciones de peliculas.
 * @author Francisco Rodríguez García
 */
public class PeliculaDAO extends GenericDAO<Pelicula> {
	
	/**
	 * Metodo que localiza una pelicula por su ID.
	 * @param id ID para localizar la pelicula.
	 * @return Devuelve la pelicula localiza.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	public Pelicula localizar(int id) throws LmdbException {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		sesion.beginTransaction();
		Pelicula pelicula = (Pelicula) sesion.get(Pelicula.class, id);
		
		if (pelicula == null) {
			sesion.getTransaction().rollback();
			throw new LmdbException("No existe la película.");
		}
		
		sesion.getTransaction().commit();

		return pelicula;
	}
	
	/**
	 * Metodo que lista las peliculas.
	 * @return Devuelve una lista de peliculas.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	@SuppressWarnings("unchecked")
	public List<Pelicula> listarPeliculas() throws LmdbException {
		Session sesion;
		Query consulta;
		List<Pelicula> listaPeliculas;
		
		sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		consulta = sesion.createQuery("SELECT p FROM Pelicula p");
		listaPeliculas = consulta.list();
		
		if (listaPeliculas.isEmpty()) {
			throw new LmdbException("No hay películas.");
		}
		
		return listaPeliculas;
	}
	
	/**
	 * Metodo que lista las peliculas por anho.
	 * @param anho Anho por el que lista.
	 * @return Devuelve una lista de peliculas.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	@SuppressWarnings("unchecked")
	public List<Pelicula> listarPeliculasPorAnho(String anho) throws LmdbException {
		Session sesion;
		Query consulta;
		List<Pelicula> listaPeliculas;
		
		sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		consulta = sesion.createQuery("SELECT p FROM Pelicula p WHERE anho LIKE '%" + anho + "%'");
		listaPeliculas = consulta.list();
		
		if (listaPeliculas.isEmpty()) {
			throw new LmdbException("No hay películas de " + anho + ".");
		}
		
		return listaPeliculas;
	}

	/**
	 * Metodo que lista las peliculas por pais.
	 * @param indicePais Indice de pais por el que lista.
	 * @return Devuelve una lista de peliculas.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	@SuppressWarnings("unchecked")
	public List<Pelicula> listarPeliculasPorPais(int indicePais) throws LmdbException {
		Session sesion;
		Query consulta;
		List<Pelicula> listaPeliculas;
		
		sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		consulta = sesion.createQuery("SELECT p FROM Pelicula p WHERE pais = " + indicePais + "");
		listaPeliculas = consulta.list();
		
		if (listaPeliculas.isEmpty()) {
			throw new LmdbException("No hay películas de " + Principal.solicitarPais(indicePais) + ".");
		}
		
		return listaPeliculas;
	}

	/**
	 * Metodo que lista las peliculas por genero.
	 * @param indiceGenero Indice de genero por el que lista.
	 * @return Devuelve una lista de peliculas.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	@SuppressWarnings("unchecked")
	public List<Pelicula> listarPeliculasPorGenero(int indiceGenero) throws LmdbException {
		Session sesion;
		Query consulta;
		List<Pelicula> listaPeliculas;
		
		sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		consulta = sesion.createQuery("SELECT p FROM Pelicula p WHERE genero = " + indiceGenero + "");
		listaPeliculas = consulta.list();
		
		if (listaPeliculas.isEmpty()) {
			throw new LmdbException("No hay películas de " + Principal.solicitarGenero(indiceGenero) + ".");
		}
		
		return listaPeliculas;
	}

	/**
	 * Metodo que lista las peliculas por rango de duracion.
	 * @param duracionMinima Duracion minima.
	 * @param duracionMaxima Duracion maxima.
	 * @return Devuelve una lista.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	@SuppressWarnings("unchecked")
	public List<Pelicula> listarPeliculasPorRangoDuracion(int duracionMinima, int duracionMaxima) throws LmdbException {
		Session sesion;
		Query consulta;
		List<Pelicula> listaPeliculas;
		
		sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		consulta = sesion.createQuery("SELECT p FROM Pelicula p WHERE duracion BETWEEN " + duracionMinima + " AND " + duracionMaxima + "");
		listaPeliculas = consulta.list();
		
		if (listaPeliculas.isEmpty()) {
			throw new LmdbException("No hay películas con duración entre " + duracionMinima + " y " + duracionMaxima + " minutos.");
		}
		
		return listaPeliculas;
	}
}