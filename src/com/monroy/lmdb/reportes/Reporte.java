package com.monroy.lmdb.reportes;

import java.util.List;

import javax.swing.JFrame;

import com.monroy.lmdb.LmdbException;
import com.monroy.lmdb.Principal;
import com.monroy.lmdb.persistencia.Pelicula;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.swing.JRViewer;

/**
 * Crea un informe de reporte.
 * 
 * @author Francisco Rodríguez García
 */
public class Reporte extends JFrame {
	private static final long serialVersionUID = 1L;

	//========================================================================================//
	// MÉTODOS
	//========================================================================================//
	/**
	 * Crea un reporte.
	 * @throws JRException
	 * @throws LmdbException 
	 */
	public void crearReporte() throws JRException, LmdbException {
		String rutaPlantillaReporte;
		List<Pelicula> listaRecetasConsulta;
		JasperReport reporteJasper;
		JasperPrint pintarJasper;
		JRViewer visionador;
		JRPdfExporter exportador;
		
		// Se configura e inicia sesión en Hibernate.
		Principal.configurarSesion();
		
		// Se guarda la localización del fichero JRXML que contiene la estructura del informe.
		rutaPlantillaReporte = "src/com/monroy/lmdb/reportes/filmografia.jrxml";
		
		// Se carga la lista de películas.
		listaRecetasConsulta = Principal.peliculaDao.listarPeliculas();
		
		// Se crea un objeto JasperReport con la ruta de la plantilla.
		reporteJasper = JasperCompileManager.compileReport(rutaPlantillaReporte);
		
		// Se crea un objeto JasperPrint con el JasperReport.
		pintarJasper = JasperFillManager.fillReport(reporteJasper, null, new JRBeanCollectionDataSource(listaRecetasConsulta));

		// Se crea la vista del reporte con el JasperPrint.
		visionador = new JRViewer(pintarJasper);

		// Se exporta a PDF.
		exportador = new JRPdfExporter();
		exportador.setExporterInput(new SimpleExporterInput(pintarJasper));
		exportador.setExporterOutput(new SimpleOutputStreamExporterOutput("filmografia.pdf"));
		exportador.exportReport();

		// Se le da visibilidad.
		visionador.setOpaque(true);
		visionador.setVisible(true);

		// Se añade al JFrame.
		this.add(visionador);
		this.setSize(700, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Se cierra la sesión de Hibernate.
		Principal.cerrarSesion();
	}
}