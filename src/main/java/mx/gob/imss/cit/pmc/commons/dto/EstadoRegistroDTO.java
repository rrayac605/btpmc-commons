package mx.gob.imss.cit.pmc.commons.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_ESTADO_REGISTRO")
public class EstadoRegistroDTO {

	@Setter
	@Getter
	private String cveIdEstadoRegistro;

	@Setter
	@Getter
	private String desEstadoRegistro;

	@Setter
	@Getter
	private Integer cveEstadoRegistro;

}
