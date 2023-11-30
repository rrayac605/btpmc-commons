package mx.gob.imss.cit.pmc.commons.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_ACTO_INSEGURO")
public class ActoInseguroDTO {

	@Setter
	@Getter
	private int cveIdActoInseguro;

	@Setter
	@Getter
	private String desDescripcion;

}
