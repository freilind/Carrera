package com.crono.resultados;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

import com.crono.modelo.dto.AtletaDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class Diploma extends PdfPageEventHelper{
	
	private static final Logger logger = Logger.getLogger(Diploma.class);
	private PdfPTable tabla, header;
    private PdfPCell cell;
    @SuppressWarnings("unused")
    private PdfWriter writer;
    private String titulo[]={"Nombre"," Apellido","Numero", "Categoria","Genero"};
    private float ancho[]={250, 500, 160}, anchoRegistro[]={150, 50, 250};
	private AtletaDTO a;
    private Document document;
	
	public Diploma(AtletaDTO a){
		
		this.a = a;
		
		document=new Document(PageSize.A4, 15, 15, 15, 15);
		try {
		    writer = PdfWriter.getInstance(document,new FileOutputStream("pdf/"+a.getCedula()+".pdf"));
		    
		} catch (FileNotFoundException fnfe) {
		    JOptionPane.showMessageDialog(null, "Ha Ocurrido un Error Inesperado.","ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(fnfe);
		} catch (DocumentException de) {
		    JOptionPane.showMessageDialog(null, "Ha Ocurrido un Error Inesperado.","ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(de);
		}
		
        try {
           document.open(); 
          // document.add((crearHeader()));
          // crearCuerpo();
          // JOptionPane.showMessageDialog(null, "Documento Generado Correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ha Ocurrido un Error Inesperado.","ERROR", JOptionPane.ERROR_MESSAGE);
            logger.debug(ex);
        }finally{
            document.close();
        }
	        
	       
	}//fin constructor
	    
	    private PdfPTable crearHeader()throws Exception{
			
			header=new PdfPTable(ancho);
		    	cell = new PdfPCell (new Paragraph ("TriSoul"+"\nCarrera 10K y Caminata 5k\n"+new SimpleDateFormat("dd 'de' MMMM 'de' yyyy ' ' h:mm a", new Locale("es","ve")).format(new Date()).toUpperCase()));
		    	cell.setColspan (3);
		    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
		    	cell.setPadding (5.0f);
		    	header.addCell (cell);
			return header;
	    }//fin de crear header
	     
	    
	    private void crearCuerpo()throws Exception{
	    	
 	
		    Font miFuente = new Font();
		   	miFuente.setFamily(BaseFont.HELVETICA);
		   	miFuente.setSize(20f);
		   	miFuente.setColor(BaseColor.BLACK);
		   	
		   	
		   	Paragraph pr1 = new Paragraph();
		   	pr1.setFont(miFuente);
		   	
		   	Paragraph pr2 = new Paragraph();
		   	pr2.setFont(miFuente);
		   	
	 
	    	pr1.add("Participante: "+" "+ a.getNombres().toUpperCase()+" "+a.getApellidos().toUpperCase() );
	    	document.add(pr1);
    	    
	    	//pr2.add("Tiempo: "+" "+a.getTiempo());
	    	//document.add(pr2);
    	    
	    	
	    }//fin crear cuerpo
	    
	   
	
}

