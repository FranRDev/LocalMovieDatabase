package com.monroy.lmdb.vista_controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.Principal;
import com.monroy.lmdb.persistencia.Actor;
import com.monroy.lmdb.persistencia.Director;
import com.monroy.lmdb.persistencia.Genero;
import com.monroy.lmdb.persistencia.Pais;
import com.monroy.lmdb.persistencia.Pelicula;
import com.monroy.lmdb.persistencia.PeliculaActor;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * Controlador de Escena Película.
 * 
 * @author Francisco Rodríguez García
 */
public class ModificarPeliculaControlador {
	
	//========================================================================================//
  	// ATRIBUTOS
  	//========================================================================================//
	@FXML
	private TextField campoTituloEspanha;
	@FXML
	private TextField campoTituloOriginal;
	@FXML
	private TextField campoAnho;
	@FXML
	private TextField campoDuracion;
	@FXML
	private ChoiceBox<Pais> paises;
	@FXML
	private Label nombreDirector;
	@FXML
	private ChoiceBox<Genero> generos;
	@FXML
	private Label reparto;
	private Stage escenarioDialogo;
	private Pelicula pelicula;
	private boolean okPulsado = false;
	private Alert alerta;
	private Date anho = null;
	private int duracion;
	private Director director = null;
	private boolean nuevo;
	private PeliculaActor peliculaActor;
	private List<Actor> actores;
	private Set<PeliculaActor> peliculaActores = null;
	private String cadenaActores;
	
	//========================================================================================//
  	// CONSTRUCTOR
  	//========================================================================================//
    /**
     * Constructor.
     * Es llamado antes del método initialize().
     */
	public ModificarPeliculaControlador() {
	}
	
	//========================================================================================//
  	// SETTERS
  	//========================================================================================//
	/**
	 * Set del escenario de diálogo.
	 * @param escenarioDialogo Escenario de diálogo.
	 */
	public void setEscenarioDialogo(Stage escenarioDialogo) {
		this.escenarioDialogo = escenarioDialogo;
	}

	/**
	 * Se de la película.
	 * @param pelicula Película.
	 */
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
		
		if (pelicula != null) {
			this.nuevo = false;
			this.director = pelicula.getDirector();
			
			SimpleDateFormat formatoAnho;
	    	formatoAnho = new SimpleDateFormat("yyyy");
			
			campoTituloEspanha.setText(pelicula.getTituloEspanha());
			campoTituloOriginal.setText(pelicula.getTituloOriginal());
			campoAnho.setText(formatoAnho.format(pelicula.getAnho()));
			campoDuracion.setText(Integer.toString(pelicula.getDuracion()));
			paises.getSelectionModel().select(pelicula.getPais());
			nombreDirector.setText(pelicula.getDirector().getNombre());
			generos.getSelectionModel().select(pelicula.getGenero());
			
			if (pelicula.cadenaActores().length() == 0) {
				reparto.setText("Sin asignar");
				
			} else {
				cadenaActores = pelicula.cadenaActores();
				reparto.setText(cadenaActores);
			}
			
		} else {
			this.nuevo = true;
		}
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
    	actores = new ArrayList<Actor>();
    	peliculaActores = new HashSet<>();
    	cadenaActores = "";
    	
    	cargarPaises();
    	cargarGeneros();
    }
	   
	//========================================================================================//
  	// MANEJADORES
  	//========================================================================================//
    /**
     * Maneja el botón OK.
     */
    @FXML
    private void manejadorBotonOk() {
    	if (comprobarCambios()) {
			try {
				if (nuevo) {
					pelicula = new Pelicula();
					pelicula.setTituloEspanha(campoTituloEspanha.getText());
		    		pelicula.setTituloOriginal(campoTituloOriginal.getText());
		    		pelicula.setAnho(anho);
		    		pelicula.setDuracion(duracion);
		    		pelicula.setPais(paises.getValue());
		    		pelicula.setDirector(director);
		    		pelicula.setGenero(generos.getValue());
		    		
		    		for (Actor actor : actores) {
		    			Principal.configurarSesion();
		    			
		    			try {
		    				Principal.actorDao.localizar(actor.getId());
		    				
		    			} catch (LmdbException e) {
		    				Principal.actorDao.guardar(actor);
						}
		    			
		    			Principal.cerrarSesion();
		    			
						peliculaActor = new PeliculaActor(actor, pelicula);
						peliculaActores.add(peliculaActor);
					}
		    		
		    		pelicula.setActores(peliculaActores);
					
		    		Principal.configurarSesion();
					Principal.peliculaDao.guardar(pelicula);
					Principal.anhadirPeliculaALista(pelicula);
					Principal.cerrarSesion();
					
				} else {
					pelicula.setTituloEspanha(campoTituloEspanha.getText());
		    		pelicula.setTituloOriginal(campoTituloOriginal.getText());
		    		pelicula.setAnho(anho);
		    		pelicula.setDuracion(duracion);
		    		pelicula.setPais(paises.getValue());
		    		pelicula.setDirector(director);
		    		pelicula.setGenero(generos.getValue());
					
		    		Principal.configurarSesion();
					Principal.peliculaDao.actualizar(pelicula);
					Principal.cerrarSesion();
				}
				
			} catch (LmdbException e) {
				alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("LocalMovieDatabase");
				alerta.setHeaderText("Error");
				alerta.setContentText(e.getMessage());
				alerta.showAndWait();
			}
			
    		escenarioDialogo.close();
    	}
    }
    
    /**
     * Maneja el botón de cancelar.
     */
    @FXML
    private void manejadorBotonCancelar() {
    	escenarioDialogo.close();
    }
    
    /**
     * Maneja el botón de seleccionar director.
     */
    @FXML
    private void manejadorBotonSeleccionarDirector() {
    	ChoiceDialog<String> dialogoSeleccionar;
    	Optional<String> resultado;
    	List<Director> directores;
    	List<String> nombresDirectores;
    	
    	try {
    		Principal.configurarSesion();
			directores = Principal.directorDao.listarDirectores();
			Principal.cerrarSesion();
			
			nombresDirectores = new ArrayList<>();
	    	
	    	for (Director director : directores) {
	    		nombresDirectores.add(director.getNombre());
			}

	    	dialogoSeleccionar = new ChoiceDialog<>("", nombresDirectores);
	    	dialogoSeleccionar.setTitle("LocalMovieDatabase");
	    	dialogoSeleccionar.setHeaderText("Selecciona un director");

	    	resultado = dialogoSeleccionar.showAndWait();
	    	
	    	if (resultado.isPresent()){
	    		for (Director director : directores) {
		    		if (director.getNombre().equals(resultado.get())) {
						this.director = director;
						nombreDirector.setText(director.getNombre());
					}
				}
	    	}
	    	
		} catch (LmdbException e) {
			alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("LocalMovieDatabase");
			alerta.setHeaderText("Espera un momento");
			alerta.setContentText("Aún no hay directores/as creados.");
			alerta.showAndWait();
		}
    }
    
    /**
     * Maneja el botón de nuevo director.
     */
    @FXML
    private void manejadorBotonNuevoDirector() {
    	TextInputDialog dialogoEntrada;
    	Optional<String> resultado;
    	Director director;
    	String nombre;
    	
    	dialogoEntrada = new TextInputDialog();
    	dialogoEntrada.setTitle("LocalMovieDatabase");
    	dialogoEntrada.setHeaderText("Nuevo director/a");
    	dialogoEntrada.setContentText("Nombre:");
    	
    	resultado = dialogoEntrada.showAndWait();
    	
    	if (resultado.isPresent()){
    		nombre = resultado.get();
    		director = new Director(nombre);
    		
    		try {
    			Principal.configurarSesion();
				Principal.directorDao.guardar(director);
				this.director = director;
				nombreDirector.setText(director.getNombre());
				Principal.cerrarSesion();
				
			} catch (LmdbException e) {
				alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("LocalMovieDatabase");
				alerta.setHeaderText("Error al crear el director/a");
				alerta.setContentText(e.getMessage());
				alerta.showAndWait();
			}
    	}
    }
    
    /**
     * Maneja el botón de nuevo actor.
     */
    @FXML
    private void manejadorBotonNuevoActor() {
    	TextInputDialog dialogoEntrada;
    	Optional<String> resultado;
    	Actor actor;
    	String nombre;
    	
    	dialogoEntrada = new TextInputDialog();
    	dialogoEntrada.setTitle("LocalMovieDatabase");
    	dialogoEntrada.setHeaderText("Nuevo actor/actriz");
    	dialogoEntrada.setContentText("Nombre:");
    	
    	resultado = dialogoEntrada.showAndWait();
    	
    	if (resultado.isPresent()){
    		nombre = resultado.get();
    		actor = new Actor(nombre);
    		
    		try {
				if (!nuevo) {
					Principal.configurarSesion();
					Principal.actorDao.guardar(actor);
					peliculaActor = new PeliculaActor(actor, pelicula);
					Principal.genericDao.guardar(peliculaActor);
					Principal.cerrarSesion();
					
				} else {
					actores.add(actor);
				}
				
				if (cadenaActores.length() == 0) {
					cadenaActores += actor.getNombre();
					
				} else {
					cadenaActores += ", " + actor.getNombre();
				}
				
				reparto.setText(cadenaActores);
				
			} catch (LmdbException e) {
				alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("LocalMovieDatabase");
				alerta.setHeaderText("Error al crear el actor/actriz");
				alerta.setContentText(e.getMessage());
				alerta.showAndWait();
			}
    	}
    }
    
    /**
     * Maneja el botón de seleccionar actor.
     */
    @FXML
    private void manejadorBotonSeleccionarActor() {
    	ChoiceDialog<String> dialogoSeleccionar;
    	Optional<String> resultado;
    	List<Actor> actores;
    	List<String> nombresActores;
    	
    	try {
    		Principal.configurarSesion();
			actores = Principal.actorDao.listarActores();
			Principal.cerrarSesion();
			
			nombresActores = new ArrayList<>();
	    	
	    	for (Actor actor : actores) {
	    		nombresActores.add(actor.getNombre());
			}

	    	dialogoSeleccionar = new ChoiceDialog<>("", nombresActores);
	    	dialogoSeleccionar.setTitle("LocalMovieDatabase");
	    	dialogoSeleccionar.setHeaderText("Selecciona un actor/actriz");

	    	resultado = dialogoSeleccionar.showAndWait();
	    	
	    	if (resultado.isPresent()) {
	    		for (Actor actor : actores) {
		    		if (actor.getNombre().equals(resultado.get())) {
						
						if (!nuevo) {
							Principal.configurarSesion();
							peliculaActor = new PeliculaActor(actor, pelicula);
							Principal.genericDao.guardar(peliculaActor);
							Principal.cerrarSesion();
							
						} else {
							this.actores.add(actor);
						}
						
						if (cadenaActores.length() == 0) {
							cadenaActores += actor.getNombre();
							
						} else {
							cadenaActores += ", " + actor.getNombre();
						}
						
						reparto.setText(cadenaActores);
					}
				}
	    	}
	    	
		} catch (LmdbException e) {
			alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("LocalMovieDatabase");
			alerta.setHeaderText("Espera un momento");
			alerta.setContentText("Aún no hay actores/actrices creados.");
			alerta.showAndWait();
		}
    }
    
	//========================================================================================//
  	// MÉTODOS
  	//========================================================================================//
    /**
     * Comprueba si el botón OK ha sido pulsado.
     * @return Devuelve si el botón OK ha sido pulsado.
     */
    public boolean okPulsado() {
        return okPulsado;
    }
    
    /**
     * Carga el enumerado de países.
     */
    private void cargarPaises() {
    	Pais[] paises = Pais.values();
    	
    	for (Pais pais : paises) {
    		this.paises.getItems().add(pais);
    	}
    	
    	this.paises.getSelectionModel().select(Pais.ESTADOS_UNIDOS);
    }
    
    /**
     * Carga el enumerado de géneros.
     */
    private void cargarGeneros() {
    	Genero[] generos = Genero.values();
    	
    	for (Genero genero : generos) {
			this.generos.getItems().add(genero);
		}
    	
    	this.generos.getSelectionModel().select(Genero.ACCION);
    }
    
    /**
     * Comprueba los cambios.
     * @return Devuelve si están correctos.
     */
    private boolean comprobarCambios() {
    	String mensajeError = "";
		SimpleDateFormat formatoAnho;
		String textoAnho;
    	
    	if (campoTituloEspanha.getText() == null || campoTituloEspanha.getText().length() == 0) {
			mensajeError += "Introduce el título en España.\n";
		}
    	
    	if (campoTituloOriginal.getText() == null || campoTituloOriginal.getText().length() == 0) {
			mensajeError += "Introduce el título original.\n";
		}
    	
    	if (campoAnho.getText() == null || campoAnho.getText().length() == 0) {
    		mensajeError += "Introduce el año.\n";
    		
		} else {
			try {
				textoAnho = campoAnho.getText();
				formatoAnho = new SimpleDateFormat("yyyy");
				anho = formatoAnho.parse(textoAnho);
				
			} catch (Exception e) {
				mensajeError += "Introduce un año válido.\n";
			}
		}
    	
    	if (campoDuracion.getText() == null || campoDuracion.getText().length() == 0) {
    		mensajeError += "Introduce la duración.\n";
    		
		} else {
			try {
				duracion = Integer.parseInt(campoDuracion.getText());
				
			} catch (NumberFormatException e) {
				mensajeError += "Introduce una duración válida.\n";
			}
		}
    	
    	if (director == null) {
    		mensajeError += "Añade un director.\n";
		}
    	
    	if (mensajeError.length() != 0) {
    		alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("LocalMovieDatabase");
			alerta.setHeaderText("Error al crear la película");
			alerta.setContentText(mensajeError);
			alerta.showAndWait();
			return false;
			
		} else {
			return true;
		}
    }
}