package com.monroy.lmdb.dao;

import org.hibernate.Session;
import com.monroy.lmdb.persistencia.HibernateUtil;

public class GenericDAO<T> {
	public void guardar(T entidad) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		sesion.save(entidad);
		sesion.getTransaction().commit();
	}
	
	public void borrar(T entidad) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		sesion.delete(entidad);
		sesion.getTransaction().commit();
	}
	
	public void actualizar(T entidad) {
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		sesion.beginTransaction();
		sesion.update(entidad);
		sesion.getTransaction().commit();
	}
}