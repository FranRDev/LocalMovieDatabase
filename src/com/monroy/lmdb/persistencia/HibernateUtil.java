package com.monroy.lmdb.persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Clase con utilidades de Hibernate.
 * @author Francisco Rodríguez García
 */
public class HibernateUtil {
	//========================================================================================//
	// VARIABLES
	//========================================================================================//
    private static SessionFactory factoriaDeSesion;

	//========================================================================================//
	// MÉTODOS
	//========================================================================================//
    /**
     * Metodo que construye la factoria de sesion.
     */
    public static synchronized void buildSessionFactory() {
        Configuration configuracion = new Configuration();
        configuracion.configure();
        configuracion.setProperty("hibernate.current_session_context_class", "thread");

        ServiceRegistry servicioDeRegistro = new ServiceRegistryBuilder().applySettings(configuracion.getProperties()).buildServiceRegistry();
        factoriaDeSesion = configuracion.buildSessionFactory(servicioDeRegistro);
    }

    /**
     * Metodo que abre la sesion y la vincula a un hilo.
     */
    public static void openSessionAndBindToThread() {
        Session sesion = factoriaDeSesion.openSession();
        ThreadLocalSessionContext.bind(sesion);
    }
    
    /**
     * Get de la factoria de sesion.
     * @return Devuelve la factoria de sesion.
     */
    public static SessionFactory getSessionFactory() {
        if (factoriaDeSesion == null)  {
            buildSessionFactory();
        }
        
        return factoriaDeSesion;
    }

    /**
     * Metodo que cierra la sesion y la desvincula del hilo.
     */
    public static void closeSessionAndUnbindFromThread() {
        Session sesion = ThreadLocalSessionContext.unbind(factoriaDeSesion);
        if (sesion != null) {
            sesion.close();
        }
    }

    /**
     * Metodo que cierra la factoria de sesion.
     */
    public static void closeSessionFactory() {
        if ((factoriaDeSesion!=null) && (factoriaDeSesion.isClosed()==false)) {
            factoriaDeSesion.close();
        }
    }
}