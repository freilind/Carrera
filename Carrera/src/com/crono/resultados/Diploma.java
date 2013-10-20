package com.crono.resultados;

import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

import com.crono.modelo.dto.AtletaDTO;
import com.crono.util.Constantes;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Diploma extends Pdf{
	
	private static final Logger logger = Logger.getLogger(Diploma.class);
	private PdfPTable header;

    @SuppressWarnings("unused")
    private PdfWriter writer;
    private static String titulo[]={"Nombre"," Apellido", "Categoria","Genero"};
    private static float ancho[]={250, 500, 160};
	private static float anchoRegistro[]={150, 150, 100, 100};
	private AtletaDTO a;
    private Document document;
	
	public Diploma(AtletaDTO a){
		super("diploma/"+a.getCedula()+".pdf", " ", titulo, anchoRegistro, ancho);
		this.a = a;
			
		try {
			document.open(); 
			document.add(crearHeader());
			//document.add(crearTitulo());
			document.add(crearCuerpo());
			JOptionPane.showMessageDialog(null, Constantes.RESULTADO, "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Constantes.EXCEPTION, "ERROR", JOptionPane.ERROR_MESSAGE);
            logger.debug(ex);
        }finally{
            document.close();
        }
	        
	       
	}//fin constructor
	    
	     
	    
    private PdfPTable crearCuerpo()throws Exception{
  
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
    	document.add(pr2);
		return header;
	   
    }//fin crear cuerpo
	    
}

