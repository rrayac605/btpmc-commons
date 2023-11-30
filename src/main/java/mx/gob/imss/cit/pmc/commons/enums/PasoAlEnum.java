package mx.gob.imss.cit.pmc.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum PasoAlEnum {

	PASO_AL("PASO AL");

	@Getter
	@Setter
	private String descripcion;

	PasoAlEnum(String descripcion) {
		this.descripcion = descripcion;

	}

}
