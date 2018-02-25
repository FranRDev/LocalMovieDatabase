package com.monroy.lmdb.vista_controlador;

import java.text.SimpleDateFormat;

import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.Principal;
import com.monroy.lmdb.persistencia.Pelicula;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

/**
 * Controlador de Pelicula.
 * 
 * @author Francisco Rodríguez García
 */
public class PeliculaControlador {
	
	//========================================================================================//
  	// ATRIBUTOS
  	//========================================================================================//
	@FXML
    private TableView<Pelicula> tablaPeliculas;
    @FXML
    private TableColumn<Pelicula, String> columnaId;
    @FXML
    private TableColumn<Pelicula, String> columnaNombreEspanha;
    @FXML
    private TableColumn<Pelicula, String> columnaAnho;
    @FXML
    private Label etiquetaId;
    @FXML
    private Label etiquetaTituloEspanha;
    @FXML
    private Label etiquetaTituloOriginal;
    @FXML
    private Label etiquetaAnho;
    @FXML
    private Label etiquetaDuracion;
    @FXML
    private Label etiquetaPais;
    @FXML
    private Label etiquetaDirector;
    @FXML
    private Label etiquetaGenero;
    @FXML
    private Label etiquetaReparto;
    private Principal principal;
	private static ObservableList<Pelicula> datosPeliculas = FXCollections.observableArrayList();
	private Alert alerta;

	//========================================================================================//
  	// CONSTRUCTOR
  	//========================================================================================//
    /**
     * Constructor.
     * Es llamado antes del método initialize().
     */
    public PeliculaControlador() {}
    
	//========================================================================================//
  	// SETTERS
  	//========================================================================================//
    /**
     * Set del principal. Añade los elementos en la tabla.
     *
     * @param pricipal Principal.
     */
    public void setPrincipal(Principal pricipal) {
        this.principal = pricipal;

        // Se añaden las personas de la lista a la tabla.
        tablaPeliculas.setItems(principal.obtenerDatosPeliculas());
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
    	cargarDatosPeliculas();
    	mostrarDetallesPeliculas();
    }
    
	//========================================================================================//
  	// MÉTODOS
  	//========================================================================================//
    /**
     * Carga los datos de las películas.
     */
    private void cargarDatosPeliculas() {
		// Se carga con datos la tabla.
		tablaPeliculas.setItems(datosPeliculas);
		 
		// Se cargan con datos las columnas de la tabla.
    	columnaId.setCellValueFactory(datoCelda -> datoCelda.getValue().getIdProperty());
        columnaNombreEspanha.setCellValueFactory(datoCelda -> datoCelda.getValue().getTituloEspanhaProperty());
        columnaAnho.setCellValueFactory(datoCelda -> datoCelda.getValue().getAnhoProperty());
	}
    
    /**
     * Muestra los detalles de las películas.
     */
    private void mostrarDetallesPeliculas() {
    	// Se ponen vacíos los detalles de la película.
        ponerDetallesPeliculas(null);

        // Se escuchan los cambios de selección en la tabla, y muestra los detalles de la persona seleccionada.
        tablaPeliculas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> ponerDetallesPeliculas(newValue));
    }
    
    /**
     * Pone los detalles de una película.
     * @param pelicula Película seleccionada.
     */
    private void ponerDetallesPeliculas(Pelicula pelicula) {
    	SimpleDateFormat formatoAnho;
    	
    	formatoAnho = new SimpleDateFormat("yyyy");
    	
    	if (pelicula != null) {
			etiquetaId.setText(Integer.toString(pelicula.getId()));
			etiquetaTituloEspanha.setText(pelicula.getTituloEspanha());
			etiquetaTituloOriginal.setText(pelicula.getTituloOriginal());
			etiquetaAnho.setText(formatoAnho.format(pelicula.getAnho()));
			etiquetaDuracion.setText(Integer.toString(pelicula.getDuracion()));
			etiquetaPais.setText(pelicula.getPais().toString().replace('_', ' '));
			etiquetaDirector.setText(pelicula.getDirector().getNombre());
			etiquetaGenero.setText(pelicula.getGenero().toString().replace('_', ' '));
			etiquetaReparto.setText(pelicula.cadenaActores());
			
		} else {
			etiquetaId.setText("");
			etiquetaTituloEspanha.setText("");
			etiquetaTituloOriginal.setText("");
			etiquetaAnho.setText("");
			etiquetaDuracion.setText("");
			etiquetaPais.setText("");
			etiquetaDirector.setText("");
			etiquetaGenero.setText("");
			etiquetaReparto.setText("");
		}
    }
    
	//========================================================================================//
  	// MANEJADORES
  	//========================================================================================//
    /**
     * Maneja el botón de borrar película.
     */
    @FXML
    private void manejadorBotonBorrarPelicula() {
    	Pelicula pelicula;
    	int indice;
    	
    	try {
    		// Se borra la película de la base de datos.
    		Principal.configurarSesion();
    		pelicula = tablaPeliculas.getSelectionModel().getSelectedItem();
			Principal.peliculaDao.borrar(pelicula);
			Principal.cerrarSesion();
			
			// Se borra la película de la tabla.
			indice = tablaPeliculas.getSelectionModel().getSelectedIndex();
	    	tablaPeliculas.getItems().remove(indice);
			
		} catch (LmdbException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Maneja el botón de editar película.
     */
    @FXML
    private void manejadorBotonEditarPelicula() {
    	Pelicula peliculaSeleccionada;
    	boolean okPulsado;
    	
    	peliculaSeleccionada = tablaPeliculas.getSelectionModel().getSelectedItem();
    	
    	if (peliculaSeleccionada != null) {
			okPulsado = principal.mostrarModificarPelicula(peliculaSeleccionada);
			
			if (okPulsado) {
				try {
					Principal.peliculaDao.actualizar(peliculaSeleccionada);
					ponerDetallesPeliculas(peliculaSeleccionada);
					
				} catch (LmdbException e) {}
			}
    		
		} else {
			alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("LocalMovieDatabase");
			alerta.setHeaderText("Espera un momento");
			alerta.setContentText("No has seleccionado ninguna película.");
			alerta.showAndWait();
		}
    }
    
    /**
     * Maneja el botón de añadir película.
     */
    @FXML
    private void manejadorBotonAnhadirPelicula() {
    	Pelicula peliculaNueva;
    	boolean okPulsado;
    	
    	peliculaNueva = null;
    	
    	okPulsado = principal.mostrarModificarPelicula(peliculaNueva);
    	
    	if (okPulsado) {
    		try {
				Principal.peliculaDao.guardar(peliculaNueva);
				principal.obtenerDatosPeliculas().add(peliculaNueva);
				
			} catch (LmdbException e) {}
		}
    }
}