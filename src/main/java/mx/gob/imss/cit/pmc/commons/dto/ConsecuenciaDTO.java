package mx.gob.imss.cit.pmc.commons.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_CONSECUENCIA")
public class ConsecuenciaDTO {

	@Setter
	@Getter
	private int cveIdConsecuencia;

	@Setter
	@Getter
	private String desConsecuencia;

}
