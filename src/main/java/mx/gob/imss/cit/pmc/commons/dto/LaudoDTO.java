package mx.gob.imss.cit.pmc.commons.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_LAUDO")
public class LaudoDTO {
	
	@Setter
	@Getter
	private int cveIdLaudo;
	
	@Setter
	@Getter
	private String desLaudo;

}
