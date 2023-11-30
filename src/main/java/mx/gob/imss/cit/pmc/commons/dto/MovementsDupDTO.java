package mx.gob.imss.cit.pmc.commons.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

public class MovementsDupDTO {

	/** Nss de registros susceptibles */
	@Getter
	@Setter
	@Field("_id")
	private Object id;

	/** Lista de movimientos susceptibles */
	@Getter
	@Setter
	private List<DetalleRegistroDTO> movementList;

}
