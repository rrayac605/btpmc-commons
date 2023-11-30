package mx.gob.imss.cit.pmc.commons.dto;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "MCC_DIAS_INHABILES")
public class FechaInhabilDTO {

	@Getter
	@Setter
	private Date fecInhabil;

	@Getter
	@Setter
	private String desFecha;

}
