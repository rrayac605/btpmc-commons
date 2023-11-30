package mx.gob.imss.cit.pmc.commons.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_NATURALEZA")
public class NaturalezaDTO {

	@Setter
	@Getter
	private String cveIdNaturaleza;

	@Setter
	@Getter
	private String desNaturaleza;

}
