package com.crono.util;

import com.crono.controlador.controladorBD.util.LeerPropiedades;

public class Constantes {
	
	
	//push git@github.com:freilind/Carrera.git
	private String archivoRecursos;
	public static String RUTA_ICONOS, RUTA_BCK, EXCEPTION, EXCEPTION_BD, CAMPO_OBLIGATORIO, 
						 ERROR_ESCRITURA, CEDULA, CEDULA_REGISTRADA,CEDULA_NO_REGISTRADA,ACCESO_DENEGADO,
						 CONFIG_BD, LOOK_FEEL, NINGUN_RESULTADO, CODIGO_AUTORIZACION, 
						 CODIGO_ERROR, SELECCION_EVENTO, NUMERO_POSITIVO, NUMERO_NO_EXISTE, NUMERO_REGISTRADO,
						 ESQUEMA_BD, RESULTADO, PARTICIPANTE_REGISTRADO, ERROR_ENCRIPTANDO, PATROCINADOR,EVENTO_UNICO,
						 NO_HAY_EVENTO, PROGRAMA_EJECUTANDOSE;
	
	public static String E_NOMBRE, E_APELLIDO, E_TELEFONO, E_EMAIL, E_CEDULA, E_SEXO, E_YEARB, E_NUMERO, E_EVENTO, E_BUSCAR_DATOS;
	
	private String vocalesUnicode = "á=\u00E1" +
									"é=\u00E9" +
									"í=\u00ED" +
									"ó=\u00F3" +
									"ú=\u00FA" +
									"ñ=\u00F1" +
									"Ñ=\u00D1";
	
	public Constantes() {
		archivoRecursos = "configuration";
		LeerPropiedades.archivoRecurso = archivoRecursos;
		RUTA_ICONOS = "/resources/iconos/";
		RUTA_BCK = "/resources/";
		PATROCINADOR = LeerPropiedades.leeID("patrocinador");
		
		EXCEPTION = "Ha Ocurrido Un Error Inesperado."; 
		EXCEPTION_BD = "Ha Ocurrido Un Error Con La Base De Datos."; 
		CAMPO_OBLIGATORIO = "Todos Los Campos Son Obligatorios \nY Deben Ser Mayor a 2 Caracteres";
		ERROR_ESCRITURA = "Ha Ocurrido Un Error Escribiendo el Fichero Log";
		CEDULA = "La C\u00E9dula Tiene Que Ser Un N\u00FAmero Positivo";
		CEDULA_REGISTRADA = "Esta C\u00E9dula Ya Est\u00E1 Registrada";
		CEDULA_NO_REGISTRADA = "Esta C\u00E9dula No Est\u00E1 Registrada";
		ACCESO_DENEGADO = "Acceso No V\u00E1lido";
		CONFIG_BD = "No Se Pudo Conectar Con La Base De Datos.\n" +
				"Revise Que La URL, Usuario, Password y Driver De La Base De Datos\n" +
				"Est\u00E9n Bien Configurados.";
		LOOK_FEEL = "No Est\u00E1 Disponible El \"Look And Feel\" Nimbus En El Equipo";
		NINGUN_RESULTADO = "No Se Obtuvo Ning\u00FAn Resultado De La Base De Datos";
		CODIGO_AUTORIZACION = "-987";
		CODIGO_ERROR = "C\u00F3digo De Autorizaci\u00F3n Inv\u00E1lido";
		SELECCION_EVENTO = "Debe Seleccionar Un Evento";
		NUMERO_POSITIVO = "Ingrese Un N\u00FAmero Positivo.\n";
		NUMERO_NO_EXISTE = "N\u00FAmero No Existe.";
		NUMERO_REGISTRADO = "N\u00FAmero Ya Est\u00E1 Registrado.\n";
		ESQUEMA_BD = "Se Ha Generado El Esquema De La Base De Datos\nCorrectamente.";
		RESULTADO = "Documento Generado Correctamente.\n";
		PARTICIPANTE_REGISTRADO = "Participante Ya Est\u00E1 Registrado.\n";
		ERROR_ENCRIPTANDO = "No Se Pudo Encriptar el Password";
		EVENTO_UNICO = "Solo puede Haber Un Evento Habilitado.\nDeshabilite el Evento Actual.";
		NO_HAY_EVENTO = "No Hay Un Evento Habilitado Para Registrar Tiempos.";
		PROGRAMA_EJECUTANDOSE= "El Programa Ya Se Esta Ejecutando.";
		
		E_NOMBRE = "El Nombre Debe Tener Entre 2 y 40 Caracteres.\n";
		E_APELLIDO = "El Apellido Debe Tener Entre 2 y 40 Caracteres.\n";
		E_CEDULA = "La C\u00E9dula Debe tener Entre 6 y 10 Caracteres.\n";
		E_TELEFONO = "El Tel\u00E9fono debe Tener Entre 7 y 15 Caracteres.\n";
		E_EMAIL = "El Email Debe Tener Entre 9 y 50 Caracteres\n";
		E_SEXO = "Debe Seleccionar un Sexo.\n";
		E_YEARB = "Debe Seleccionar un A\u00F1o de Nacimiento.\n";
		E_NUMERO = "Debe Seleccionar Otro N\u00FAmero.\n";
		E_EVENTO = "Debe Seleccionar Un Evento.\n";
		E_BUSCAR_DATOS = "Debe Buscar Datos De La Persona.\n";
		
	}
		

}
