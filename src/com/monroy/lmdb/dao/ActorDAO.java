package com.monroy.lmdb.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.persistencia.Actor;
import com.monroy.lmdb.persistencia.HibernateUtil;

public class ActorDAO extends GenericDAO<Actor> {

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