package mx.gob.imss.cit.pmc.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum CasoRegistroEnum {

	EXTEMPORANEO(2, "Extempor\u00E1neo"), OPORTUNO(1, "Oportuno");
	
	@Setter
	@Getter
	private int id;

	@Setter
	@Getter
	private String descripcion;

	private CasoRegistroEnum(int id, String descripcion) {
		this.descripcion = descripcion;
		this.id = id;
	}

}
