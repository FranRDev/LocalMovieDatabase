package com.monroy.lmdb.dao;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hibernate.Session;
import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.persistencia.HibernateUtil;

/**
 * Clase que gestiona las transacciones genericas.
 * @author Francisco Rodríguez García
 * @param <T> Clase de la entidad.
 */
public class GenericDAO<T> {
	// VARIABLES
	private Session sesion;
	private StringBuilder mensaje;
	
	// MÉTODOS
	/**
	 * Metodo que guarda una entidad.
	 * @param entidad Entidad a guardar.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	public void guardar(T entidad) throws LmdbException {
		try {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();
			sesion.save(entidad);
			sesion.getTransaction().commit();
			
		} catch (ConstraintViolationException cve) {
			sesion.getTransaction().rollback();
			mensaje = new StringBuilder();
			
			mensaje.append("Error. No se ha podido dar de alta:");
			
			for (ConstraintViolation<?> constraintViolation : cve.getConstraintViolations()) {
				mensaje.append("\n\tCampo '" + constraintViolation.getPropertyPath() + "': " + constraintViolation.getMessage() + ".");
			}
			
			throw new LmdbException(mensaje.toString());
		}
	}
	
	/**
	 * Metodo que borra una entidad.
	 * @param entidad Entidad a borrar.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	public void borrar(T entidad) throws LmdbException {
		try {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();
			sesion.delete(entidad);
			sesion.getTransaction().commit();
			
		} catch (ConstraintViolationException cve) {
			sesion.getTransaction().rollback();
			mensaje = new StringBuilder();
			
			mensaje.append("Error. No se ha podido dar de baja:");
			
			for (ConstraintViolation<?> constraintViolation : cve.getConstraintViolations()) {
				mensaje.append("\n\tCampo '" + constraintViolation.getPropertyPath() + "': " + constraintViolation.getMessage() + ".");
			}
			
			throw new LmdbException(mensaje.toString());
		}
	}
	
	/**
	 * Metodo que actualiza una entidad.
	 * @param entidad Entidad a actualizar.
	 * @throws LmdbException Excepcion de LocalMovieDatabase.
	 */
	public void actualizar(T entidad) throws LmdbException {
		try {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();
			sesion.update(entidad);
			sesion.getTransaction().commit();
			
		} catch (ConstraintViolationException cve) {
			sesion.getTransaction().rollback();
			mensaje = new StringBuilder();
			
			mensaje.append("Error. No se ha podido actualizar:");
			
			for (ConstraintViolation<?> constraintViolation : cve.getConstraintViolations()) {
				mensaje.append("\n\tCampo '" + constraintViolation.getPropertyPath() + "': " + constraintViolation.getMessage() + ".");
			}
			
			throw new LmdbException(mensaje.toString());
		}
	}
}