package com.monroy.lmdb.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.persistencia.Director;
import com.monroy.lmdb.persistencia.HibernateUtil;

/**
 * Clase que gestiona las transacciones de los directores/as.
 * 
 * @author Francisco Rodríguez García
 */
public class DirectorDAO extends GenericDAO<Director> {
	
	//========================================================================================//
	// MÉTODOS
	//========================================================================================//
	/**
	 * Metodo que localiza un director/a mediante su ID.
	 * @param id ID del director/a a localizar.
	 * @return Devuelve el director/a localizado.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	public Director localizar(int id) throws LmdbException {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		sesion.beginTransaction();
		Director director = (Director) sesion.get(Director.class, id);
		
		if (director == null) {
			sesion.getTransaction().rollback();
			throw new LmdbException("No existe director con ID: " + id + ".");
		}
		
		sesion.getTransaction().commit();
		
		sesion.clear();

		return director;
	}
	
	/**
	 * Metodo que lista los directores/as.
	 * @return Devuelve una lista de directores/as.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	@SuppressWarnings("unchecked")
	public List<Director> listarDirectores() throws LmdbException {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Director> listaDirectores;
		Query consulta;
		
		consulta = sesion.createQuery("SELECT p FROM Director p");
		listaDirectores = consulta.list();
		
		sesion.clear();
		
		if (listaDirectores.isEmpty()) {
			throw new LmdbException("No hay directores.");
		}
		
		return listaDirectores;
	}
}