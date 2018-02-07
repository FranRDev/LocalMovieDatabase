package com.monroy.lmdb.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.persistencia.Director;
import com.monroy.lmdb.persistencia.HibernateUtil;

public class DirectorDAO extends GenericDAO<Director> {
	
	public Director localizar(int id) throws LmdbException {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		sesion.beginTransaction();
		Director director = (Director) sesion.get(Director.class, id);
		
		if (director == null) {
			sesion.getTransaction().rollback();
			throw new LmdbException("No existe el director.");
		}
		
		sesion.getTransaction().commit();

		return director;
	}
	
	@SuppressWarnings("unchecked")
	public List<Director> listarDirectores() throws LmdbException {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Director> listaDirectores;
		Query consulta;
		
		consulta = sesion.createQuery("SELECT p FROM Director p");
		listaDirectores = consulta.list();
		
		if (listaDirectores.isEmpty()) {
			throw new LmdbException("No hay directores.");
		}
		
		return listaDirectores;
	}
}