package mx.gob.imss.cit.pmc.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum EstadoRegistroEnum {

	OTRAS(-1, "Otras delegaciones"), CORRECTO(1, "Correcto"), ERRONEO(2, "Err\u00F3neo"), DUPLICADO(3, "Duplicado"), SUSCEPTIBLE(4, "Susceptibles de ajuste"),
	CORRECTO_OTRAS(5, "Correctos otras delegaciones"), ERRONEO_OTRAS(6, "Err\u00F3neos otras delegaciones"),
	DUPLICADO_OTRAS(7, "Duplicados otras delegaciones"), SUSCEPTIBLE_OTRAS(8, "Susceptibles de ajuste otras delegaciones"), 
	PASO_AL(9, "Paso Al");

	@Setter
	@Getter
	private int cveEstadoRegistro;

	@Setter
	@Getter
	private String desDescripcion;

	private EstadoRegistroEnum(int cveEstadoRegistro, String desDescripcion) {
		this.cveEstadoRegistro = cveEstadoRegistro;
		this.desDescripcion = desDescripcion;
	}

}
