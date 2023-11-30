package mx.gob.imss.cit.pmc.commons.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class CabeceraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5652348313917467420L;

	/**
	 * Número de registros Numérico 7
	 */
	@Setter
	@Getter
	private Long numRegistros;

	/**
	 * Fecha de proceso Numérico 8
	 */
	@Setter
	@Getter
	private Date fecProceso;

	/**
	 * Identificador Archivo Alfanumérico 3
	 */
	@Setter
	@Getter
	private String cveArchivo;

	@Override
	public String toString() {
		return "CabeceraDTO [numRegistros=" + numRegistros + ", fecProceso=" + fecProceso + ", cveArchivo=" + cveArchivo
				+ "]";
	}

}
