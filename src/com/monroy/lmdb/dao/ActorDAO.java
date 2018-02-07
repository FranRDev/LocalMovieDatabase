package com.monroy.lmdb.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.persistencia.Actor;
import com.monroy.lmdb.persistencia.HibernateUtil;

/**
 * Clase que gestiona las transacciones de los actores/actrices.
 * @author Francisco Rodríguez García
 */
public class ActorDAO extends GenericDAO<Actor> {

	/**
	 * Metodo que localiza un actor/actriz mediante su ID.
	 * @param id ID para localizar al actor/actriz.
	 * @return Devuelve el actor/actriz localizado.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	public Actor localizar(int id) throws LmdbException {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		sesion.beginTransaction();
		Actor actor = (Actor) sesion.get(Actor.class, id);
		
		if (actor == null) {
			sesion.getTransaction().rollback();
			throw new LmdbException("No existe el actor.");
		}
		
		sesion.getTransaction().commit();

		return actor;
	}
	
	/**
	 * Metodo que lista los actores/actrices.
	 * @return Devuelve una lista de los actores/actrices.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	@SuppressWarnings("unchecked")
	public List<Actor> listarActores() throws LmdbException {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Actor> listaActores;
		Query consulta;
		
		consulta = sesion.createQuery("SELECT p FROM Actor p");
		listaActores = consulta.list();
		
		if (listaActores.isEmpty()) {
			throw new LmdbException("No hay actores.");
		}
		
		return listaActores;
	}
}