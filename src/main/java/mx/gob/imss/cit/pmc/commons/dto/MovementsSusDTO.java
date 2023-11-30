package mx.gob.imss.cit.pmc.commons.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

public class MovementsSusDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Nss de registros susceptibles */
	@Getter
	@Setter
	@Field("_id")
	private String id;

	/** Lista de movimientos susceptibles */
	@Getter
	@Setter
	private List<DetalleRegistroDTO> movementList;


}
