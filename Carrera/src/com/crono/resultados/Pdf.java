package com.crono.resultados;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

import com.crono.dao.CronoDAO;
import com.crono.util.Constantes;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class Pdf extends PdfPageEventHelper{
	
	private static final Logger logger = Logger.getLogger(Pdf.class);
	protected PdfPTable tabla, header;
	protected PdfPCell cell;
    protected PdfWriter writer;
    protected Document document;
    protected String titulo;
    private String columna[];
    private float ancho[], anchoRegistro[];
    
	public Pdf(String tituloPdf, String titulo, String [] columna, float anchoRegistro[], float ancho[]){
		
		this.titulo = titulo;
		this.columna = columna;
		this.anchoRegistro = anchoRegistro;
		this.ancho = ancho;
		
		document = new Document(PageSize.A4, 5, 5, 5, 5);
		try {
		    writer = PdfWriter.getInstance(document,new FileOutputStream(tituloPdf));
		    
		} catch (FileNotFoundException fnfe) {
		    JOptionPane.showMessageDialog(null, Constantes.EXCEPTION, "ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(fnfe);
		} catch (DocumentException de) {
		    JOptionPane.showMessageDialog(null, Constantes.EXCEPTION,"ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(de);
		}//fin try	
		
	}//fin constructor 
	
	
	protected PdfPTable crearHeader()throws Exception{
		
		header=new PdfPTable(ancho);
	    	cell = new PdfPCell (new Paragraph (Constantes.PATROCINADOR+"\n"+CronoDAO.getEvento()+"\n"+new SimpleDateFormat("dd 'de' MMMM 'de' yyyy ' ' h:mm a", new Locale("es","ve")).format(new Date()).toUpperCase()));
	    	cell.setColspan (ancho.length);
	    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
	    	cell.setPadding (5.0f);
	    	header.addCell (cell);
		return header;
    }//fin de crear header
	
	
	protected PdfPTable crearTitulo() throws Exception{
		
		tabla=new PdfPTable(anchoRegistro);
			cell = new PdfPCell (new Paragraph (titulo));
		    cell.setColspan (anchoRegistro.length);
		    cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    cell.setBackgroundColor (BaseColor.LIGHT_GRAY);
		    cell.setPadding (5.0f);
		tabla.addCell (cell);
		    
	    for (String tit:columna){
	    	cell = new PdfPCell (new Paragraph (tit));
    	    cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    	    cell.setBackgroundColor (BaseColor.LIGHT_GRAY);
    	    cell.setPadding (5.0f);
    	    tabla.addCell (cell);
    	}//fin del for
	    	
	    return tabla;    	
	}//fin crear titulos
	
	
	

}
