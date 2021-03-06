package com.crono.resultados;

import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.crono.dao.CronoDAO;
import com.crono.modelo.dto.ResultadoDTO;
import com.crono.util.Constantes;

public class General extends Pdf{
	
	private static final Logger logger = Logger.getLogger(General.class);
    private static String titulo[]={" # "," Nombres"," Apellidos"," Num"," Categor\u00EDa"," G\u00E9nero"," Tiempo"};
    private static float ancho[]={250, 500, 160};
	private static float anchoRegistro[]={40, 180,180, 90, 160, 130, 150};
	
	public General(){
		super("ResultadoGeneral.pdf", "Resultado General", titulo, anchoRegistro, ancho);
		
		try {
			document.open(); 
			document.add(crearHeader());
			document.add(crearTitulo());
			document.add(crearCuerpo());
			JOptionPane.showMessageDialog(null, Constantes.RESULTADO, "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Constantes.EXCEPTION, "ERROR", JOptionPane.ERROR_MESSAGE);
            logger.info(ex);
        }finally{
            document.close();
        }
	           
	}//fin constructor
	    
     
    private PdfPTable crearCuerpo()throws Exception{
    	
    	tabla=new PdfPTable(anchoRegistro);	   	
    	List<ResultadoDTO> result = CronoDAO.getResultadoGeneral();
    	
	    int pos=1;
	    for(ResultadoDTO resultadoDTO : result) {
	    	logger.info(resultadoDTO);
	    	if(resultadoDTO == null) continue;
	    	
	    	tabla.addCell(new Phrase(" "+pos));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getNombres()));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getApellidos()));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getNumero()));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getCategoria()));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getGenero()));
		    tabla.addCell(new Phrase(" "+resultadoDTO.getTiempo()));  
		   
		    pos++;
	    }

    	return tabla;
    }//fin crear cuerpo

}