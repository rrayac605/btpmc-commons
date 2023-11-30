package mx.gob.imss.cit.pmc.commons.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_TIPO_RIESGO")
public class TipoRiesgoDTO {
	
	@Setter
	@Getter
	private int cveIdTipoRegistro;
	
	@Setter
	@Getter
	private String desDescripcion;

}
