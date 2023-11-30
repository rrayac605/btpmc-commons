package mx.gob.imss.cit.pmc.commons.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_CLASE")
public class ClaseDTO {

	@Setter
	@Getter
	private Integer cveIdClase;

	@Setter
	@Getter
	private String desClase;

}
