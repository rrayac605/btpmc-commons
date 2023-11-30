package mx.gob.imss.cit.pmc.commons.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_CODIGO_DIAGNOSTICO")
public class CodigoDiagnosticoDTO {

	@Setter
	@Getter
	private String cveCieGenerico;

	@Setter
	@Getter
	private String desCodigoDiagnostico;

}
