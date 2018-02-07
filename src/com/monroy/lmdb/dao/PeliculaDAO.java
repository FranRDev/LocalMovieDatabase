package com.monroy.lmdb.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.persistencia.HibernateUtil;
import com.monroy.lmdb.persistencia.Pelicula;

public class PeliculaDAO extends GenericDAO<Pelicula> {
	
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
	
	@SuppressWarnings("unchecked")
	public List<Pelicula> listarPeliculas() throws LmdbException {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Pelicula> listaPeliculas;
		Query consulta;
		
		consulta = sesion.createQuery("SELECT p FROM Pelicula p");
		listaPeliculas = consulta.list();
		
		if (listaPeliculas.isEmpty()) {
			throw new LmdbException("No hay películas.");
		}
		
		return listaPeliculas;
	}
}