package com.monroy.lmdb.vista_controlador;

import com.monroy.lmdb.Principal;
import com.monroy.lmdb.persistencia.Pelicula;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controlador de Escena Película.
 * 
 * @author Francisco Rodríguez García
 */
public class EscenaPeliculaControlador {
	
	//========================================================================================//
  	// ATRIBUTOS
  	//========================================================================================//
	@FXML
    private TableView<Pelicula> tablaPeliculas;
	
    @FXML
    private TableColumn<Pelicula, Integer> columnaId;
    
    @FXML
    private TableColumn<Pelicula, String> columnaNombreEspanha;
    
    @FXML
    private TableColumn<Pelicula, Integer> columnaAnho;
    
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

	//========================================================================================//
  	// CONSTRUCTOR
  	//========================================================================================//
    /**
     * Constructor.
     * Es llamado antes del método initialize().
     */
    public EscenaPeliculaControlador() {}

    /**
     * Inicializa la clase controlador.
     * Se llama automáticamente después de que se haya cargado el archivo FXML.
     */
    @FXML
    private void initialize() {
        // Se inicializa la tabla de películas con dos columnas.
        //columnaId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        columnaNombreEspanha.setCellValueFactory(cellData -> cellData.getValue().getTituloEspanhaProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param principal
     */
    public void setMainApp(Principal principal) {
        this.principal = principal;

        // Add observable list data to the table
        tablaPeliculas.setItems(principal.obtenerDatosPelicula());
    }
}
