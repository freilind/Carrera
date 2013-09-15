package com.crono.resultados;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.crono.controlador.controladorBD.bd.ControladorBD;
import com.crono.dao.CronoDAO;
import com.crono.modelo.dto.ResultadoDTO;
import com.crono.util.Constantes;


public class Categorias extends PdfPageEventHelper {
	
	private static final Logger logger = Logger.getLogger(Categorias.class);
	private PdfPTable tabla, header;
    private PdfPCell cell;
    @SuppressWarnings("unused")
    private PdfWriter writer;
    private int numCat;
    private String titulo[]={" # ","Nombre"," Apellido","Numero", "Tiempo"}, 
    		categorias[]={"16 - 19", "20 -24", "25 - 29", "30 -34", "35 - 39", "40 - 44", "45 - 49", "50 - 55", "55 +", "Discapacitados"},
    		sexo[]={"Masculino", "Femenino"};
    private float ancho[]={250, 500, 160}, anchoRegistro[]={40, 200,200, 120, 180};
    
    
    public Categorias(){
		
		Document document=new Document(PageSize.A4, 5, 5, 5, 5);
		try {
		    writer = PdfWriter.getInstance(document,new FileOutputStream("ResultadosCategorias.pdf"));
		    
		} catch (FileNotFoundException fnfe) {
		    JOptionPane.showMessageDialog(null, Constantes.EXCEPTION,"ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(fnfe);
		} catch (DocumentException de) {
		    JOptionPane.showMessageDialog(null, Constantes.EXCEPTION,"ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(de);
		}
		
	        try {
	           document.open();
	           numCat = 0;
	           numCat = ControladorBD.contadorTabla("categorias") - 1;
	           logger.info(numCat);
	        		   
	           for (int cat = 0; cat < numCat; cat++) {
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
	    	cell = new PdfPCell (new Paragraph (Constantes.PATROCINADOR+"\n"+CronoDAO.getEventos().get(0)+"\n"+new SimpleDateFormat("dd 'de' MMMM 'de' yyyy ' ' h:mm a", new Locale("es","ve")).format(new Date()).toUpperCase()));
	    	cell.setColspan (3);
	    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
	    	cell.setPadding (5.0f);
	    	header.addCell (cell);
	    	ch.add(header);
		return ch;
    }//fin de crear header
    
    
    private PdfPTable crearTitulos(int cat, int sex) throws Exception{
	
		tabla=new PdfPTable(anchoRegistro);
    	cell = new PdfPCell (new Paragraph ("Resultados Categoria: "+ categorias[cat]+"  "+sexo[sex]));
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
    	List<ResultadoDTO> result = CronoDAO.getResultadoCategorias(cat, sex);
    	
	    int pos=1;
	    for(Iterator<ResultadoDTO> iterator = result.iterator(); iterator.hasNext();) {
	    	ResultadoDTO resultadoDTO = iterator.next();
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
