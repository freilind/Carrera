package com.crono.resultados;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.crono.dao.CronoDAO;
import com.crono.modelo.dto.ResultadoDTO;
import com.crono.util.Constantes;


public class Categorias extends Pdf {
	
	private static final Logger logger = Logger.getLogger(Categorias.class); 
    private static String titulo[]={" # ","Nombres"," Apellidos"," N\u00FAmero", " Tiempo"};		
    private static float ancho[]={250, 500, 160};
    private static float anchoRegistro[]={40, 200,200, 120, 180};
    private List<String> categorias;
    private String sexo[]={"Masculino", "Femenino"};
    
    public Categorias(){
		
		super("pdf/ResultadoCategorias.pdf", "Resultado Categorias", titulo, anchoRegistro, ancho);
		
	        try {
	           document.open();      
	           categorias = CronoDAO.getCategorias();
	           logger.info(categorias);
	           
   
	           for (int cat = 0; cat < categorias.size(); cat++) {
	               for(int sex = 0; sex < sexo.length; sex++) {
						document.add((crearHeader(cat)));
						document.add(crearTitulos(cat, sex));
						document.add(crearCuerpo(cat, sex));
	               }//fin if
	           }//fin if
	           JOptionPane.showMessageDialog(null, Constantes.RESULTADO, "Aviso", JOptionPane.INFORMATION_MESSAGE);	
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, Constantes.EXCEPTION, "ERROR", JOptionPane.ERROR_MESSAGE);
	            logger.debug(ex);
	        }finally{
	            document.close();
	        }
       
    	}//fin constructor
    
    
    private Chapter crearHeader(int cat) throws Exception{
		Chapter ch=new Chapter(cat);
			header=new PdfPTable(ancho);
	    	cell = new PdfPCell (new Paragraph (Constantes.PATROCINADOR+"\n"+CronoDAO.getEvento()+"\n"+new SimpleDateFormat("dd 'de' MMMM 'de' yyyy ' ' h:mm a", new Locale("es","ve")).format(new Date()).toUpperCase()));
	    	cell.setColspan (3);
	    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
	    	cell.setPadding (5.0f);
	    	header.addCell (cell);
	    	ch.add(header);
		return ch;
    }//fin de crear header
    
    
    private PdfPTable crearTitulos(int cat, int sex) throws Exception{
	
		tabla=new PdfPTable(anchoRegistro);
    	cell = new PdfPCell (new Paragraph ("Resultados Categoria: "+ categorias.get(cat)+"  "+sexo[sex]));
    	cell.setColspan (5);
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBackgroundColor (BaseColor.LIGHT_GRAY);
    	cell.setPadding (5.0f);
    	tabla.addCell (cell);
    	
    	for (String tit:titulo){
    	    cell = new PdfPCell (new Paragraph (tit));
    	    cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	    cell.setBackgroundColor (BaseColor.LIGHT_GRAY);
    	    cell.setPadding (5.0f);
    	    tabla.addCell (cell);
    	}//fin del for
    	
    	return tabla;    	
    }//fin crear titulos
    
    
    
    private PdfPTable crearCuerpo(int cat, int sex) throws Exception{
		
    	tabla=new PdfPTable(anchoRegistro);	   	
    	List<ResultadoDTO> result = CronoDAO.getResultadoCategorias(cat+1, sex+1);
    	
	    int pos=1;
	    for(ResultadoDTO resultadoDTO : result) {
	    	logger.info(resultadoDTO);
	    	if(resultadoDTO == null) continue;
	    	
	    	tabla.addCell(new Phrase(" "+pos));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getNombres()));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getApellidos()));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getNumero()));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getTiempo()));  			 
		    pos++;
	    }
		
    	return tabla;
    }//fin crear cuerpo

}
