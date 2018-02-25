package com.monroy.lmdb.vista_controlador;

import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.Principal;
import com.monroy.lmdb.reportes.Reporte;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import net.sf.jasperreports.engine.JRException;

/**
 * Controlador de Raiz.
 * 
 * @author Francisco Rodríguez García
 */
public class RaizControlador {
	
	//========================================================================================//
  	// ATRIBUTOS
  	//========================================================================================//
	private Alert alerta;
	
	//========================================================================================//
  	// CONSTRUCTOR
  	//========================================================================================//
	public RaizControlador() {
	}

	//========================================================================================//
  	// INICIALIZADOR
  	//========================================================================================//
    /**
     * Inicializa la clase controlador.
     * Se llama automáticamente después de que se haya cargado el archivo FXML.
     */
    @FXML
    private void initialize() {
    }
    
	//========================================================================================//
  	// MANEJADORES
  	//========================================================================================//
    /**
     * Maneja la opción de reportes.
     */
    @FXML
    private void manejadorReportes() {
    	Reporte reporte = new Reporte();
    	
    	try {
			reporte.crearReporte();
			
		} catch (JRException e) {
			alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("LocalMovieDatabase");
			alerta.setHeaderText("Error al crear el reporte.");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
			
		} catch (LmdbException e) {
			alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("LocalMovieDatabase");
			alerta.setHeaderText("Error al crear el reporte.");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
    }
    
    /**
     * Maneja la opción de refrescar.
     */
    @FXML
    private void manejadorRefrescar() {
    	Principal.configurarSesion();
    	Principal.datosPeliculas.removeAll(Principal.datosPeliculas);
    	Principal.cargarDatosPeliculas();
    	Principal.cerrarSesion();
    }
    
    /**
     * Maneja la opción de salir.
     */
    @FXML
    private void manejadorSalir() {
    	System.exit(0);
    }
}