package com.crono.modelo.administrar.usuario;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import org.apache.log4j.Logger;

import com.crono.dao.CronoDAO;
import com.crono.modelo.dto.AtletaDTO;
import com.crono.util.Fonts;
import com.crono.util.Panel;
import com.crono.vista.FrameTabla;

@SuppressWarnings("serial")
public class ListarAtletas extends DefaultTableModel {
    
	private static final Logger logger = Logger.getLogger(ListarAtletas.class);
	private int numFilas;
	private JTable tablaListar;
	private Panel ctpListar;
	private int [] anchoColumnas={80, 175, 180, 80, 80, 120, 150, 65};
    private String [] nombreColumnas={"C\u00E9dula","Nombres","Apellidos","Sexo", "A\u00F1o Nac.", "Tel\u00E9fono", "Email", "Discap."};

    public ListarAtletas() {   	
    	numFilas = CronoDAO.numeroFilas("atletas");
    	logger.info(numFilas);
		listarAtletas();
		getAtletas();
    }//fin del constructor
    
    public ListarAtletas(String tabla, String campo, int comparador) {   	
    	numFilas = CronoDAO.numeroFilas(tabla, campo, comparador);
    	logger.info(numFilas);
		listarAtletas();
		getInscritos();
    }//fin del constructor
    
    
    private void listarAtletas(){
    	this.setColumnIdentifiers(this.nombreColumnas);
		this.setRowCount(numFilas);
		
		for (int fila = 0; fila < numFilas; fila++)
		    for (int columna = 0; columna < this.nombreColumnas.length; columna++)
		    	this.isCellEditable(fila, columna);
		
		for(int columna = 0; columna < this.nombreColumnas.length; columna++)
		    this.getColumnClass(columna);
		
		tablaListar = new JTable(this);
		tablaListar.setFont(Fonts.FONT_LISTA);
		
		setAnchoColumna();
	
		ctpListar = new Panel();
		ctpListar.setBorder(new EmptyBorder(1, 1, 1, 1));
		tablaListar.setPreferredScrollableViewportSize(new Dimension(780,400));
		ctpListar.add(new JScrollPane(tablaListar));
		
		new FrameTabla(ctpListar);
    }
    
   
    private void getAtletas(){
    	
    	int fila = 0;
    	List<AtletaDTO> atletas = CronoDAO.atletas();
    	
    	if(atletas != null) {
    		
    		for(Iterator<AtletaDTO> iterator = atletas.iterator(); iterator.hasNext();) {
    			AtletaDTO atletaDTO = iterator.next();
    			logger.info(atletaDTO);
    			if(atletaDTO == null) continue;
    			
    			this.setValueAt(atletaDTO.getCedula(), fila, 0);
				this.setValueAt(atletaDTO.getNombres(), fila, 1);
				this.setValueAt(atletaDTO.getApellidos(), fila, 2);
				this.setValueAt(atletaDTO.getAux(), fila, 3);
				this.setValueAt(atletaDTO.getFechaNacimiento(), fila, 4);
				this.setValueAt(atletaDTO.getTelefono(), fila, 5);
				this.setValueAt(atletaDTO.getEmail(), fila, 6);
				if(atletaDTO.getDiscapacitado() == 0) {
					this.setValueAt("No", fila, 7);
				}else {
					this.setValueAt("Si", fila, 7);
				}
				fila++;
    		}
    	}	
    }//fin getAtletas
    
    
    private void getInscritos(){
    	
    	int fila = 0;
    	List<AtletaDTO> atletas = CronoDAO.inscritos();
    	
    	if(atletas != null) {
    		
    		for(Iterator<AtletaDTO> iterator = atletas.iterator(); iterator.hasNext();) {
    			AtletaDTO atletaDTO = iterator.next();
    			logger.info(atletaDTO);
    			if(atletaDTO == null) continue;
    			
    			this.setValueAt(atletaDTO.getCedula(), fila, 0);
				this.setValueAt(atletaDTO.getNombres(), fila, 1);
				this.setValueAt(atletaDTO.getApellidos(), fila, 2);
				this.setValueAt(atletaDTO.getAux(), fila, 3);
				this.setValueAt(atletaDTO.getFechaNacimiento(), fila, 4);
				this.setValueAt(atletaDTO.getTelefono(), fila, 5);
				this.setValueAt(atletaDTO.getEmail(), fila, 6);
				if(atletaDTO.getDiscapacitado() == 0) {
					this.setValueAt("No", fila, 7);
				}else {
					this.setValueAt("Si", fila, 7);
				}
				fila++;
    		}
    	}	
    }//fin getAtletas
    
    
    private void setAnchoColumna(){
	  	 for (int i = 0; i < anchoColumnas.length; i++) {
	  	     TableColumn column = tablaListar.getColumnModel().getColumn(i);
	  	     switch(i){
	  	     	case 0:
	  	     	    column.setPreferredWidth(anchoColumnas[0]);
	  	     	    break;
	  	     	case 1:
	  	     	    column.setPreferredWidth(anchoColumnas[1]);
	  	     	    break;
	  	     	case 2:
	  	     	    column.setPreferredWidth(anchoColumnas[2]);
	  	     	    break;
	  	     	case 3:
	  	     	    column.setPreferredWidth(anchoColumnas[3]);
	  	     	    break;
	  	     	case 4:
	  	     	    column.setPreferredWidth(anchoColumnas[4]);
	  	     	    break;
	  	     	case 5:
	  	     	    column.setPreferredWidth(anchoColumnas[5]);
	  	     	    break;
	  	     	case 6:
	  	     	    column.setPreferredWidth(anchoColumnas[6]);
	  	     	    break;
	  	     	case 7:
	  	     	    column.setPreferredWidth(anchoColumnas[7]);
	  	     	    break;
	  	     	
	  	     }//findel switch
	  	 }
     }//fin set ancho columna
    
    
    public boolean isCellEditable(int fila, int columna) {
    	return false;
    }
    
    
    
    /**
     * Metodos sobreescritos de la clase DefaultTableModel
     * envia a la tabla el tipo de clase de cada celda 
     * @param int fila, int columna
     * @return boolean
     */
    @SuppressWarnings({ "rawtypes" })
    public Class getColumnClass() {
	   return String.class;
    }

}
