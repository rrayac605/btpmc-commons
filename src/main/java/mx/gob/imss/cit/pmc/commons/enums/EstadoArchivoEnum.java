package mx.gob.imss.cit.pmc.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum EstadoArchivoEnum {

	PROCESADO("2", "Procesado"), NO_PROCESADO("1", "No Procesado");

	@Setter
	@Getter
	private String cveEstadoArchivo;

	@Setter
	@Getter
	private String desEstadoArchivo;

	private EstadoArchivoEnum(String cveEstadoArchivo, String desEstadoArchivo) {
		this.cveEstadoArchivo = cveEstadoArchivo;
		this.desEstadoArchivo = desEstadoArchivo;
	}

}
