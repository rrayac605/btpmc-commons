package mx.gob.imss.cit.pmc.commons.dto;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_CAUSA_EXTERNA")
public class CausaExternaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	private String cveIdCausaExterna;

	@Setter
	@Getter
	private String desCausaExterna;

}
