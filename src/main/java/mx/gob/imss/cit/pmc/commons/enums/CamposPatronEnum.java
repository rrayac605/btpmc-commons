package mx.gob.imss.cit.pmc.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum CamposPatronEnum {

	RP("patronDTO.refRegistroPatronal");

	@Setter
	@Getter
	private String nombreCampo;

	private CamposPatronEnum(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

}
