package mx.gob.imss.cit.pmc.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum CicloEnum {

	MES_INNICIO_CICLO(0, "Enero"), DIA_INICIO_CLICO(1, "Primero"), MES_FIN_CICLO(2, "Marzo"),
	DIA_FIN_CICLO(15, "Quince");

	@Getter
	@Setter
	private int clave;

	@Getter
	@Setter
	private String descripcion;

	private CicloEnum(int clave, String descripcion) {
		this.descripcion = descripcion;
		this.clave = clave;
	}

}
