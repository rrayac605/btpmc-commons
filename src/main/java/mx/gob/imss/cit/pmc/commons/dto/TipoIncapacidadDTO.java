package mx.gob.imss.cit.pmc.commons.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_TIPO_INCAPACIDAD")
public class TipoIncapacidadDTO {
	
	@Setter
	@Getter
	private int cveTipoIncapacidad;
	
	@Setter
	@Getter
	private String desDescripcion;

}
