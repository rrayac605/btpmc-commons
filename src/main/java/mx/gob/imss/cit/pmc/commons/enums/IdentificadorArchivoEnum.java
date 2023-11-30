package mx.gob.imss.cit.pmc.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum IdentificadorArchivoEnum {

	ARCHIVO_SISAT("ST3"), ARCHIVO_SUI55("ST5"), ARCHIVO_NSSA("RTT"), ARCHIVO_AJU("AJU"), ARCHIVO_COD("COD"), ARCHIVO_ROD("ROD");

	@Getter
	@Setter
	private String identificador;

	private IdentificadorArchivoEnum(String identificador) {
		this.identificador = identificador;
	}

}
