package mx.gob.imss.cit.pmc.commons.dto;

import java.util.Date;

import com.mongodb.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

public class FechasAuditoriaDTO {

	@Setter
	@Getter
	@Nullable
	private Date fecAlta;
	@Setter
	@Getter
	@Nullable
	private Date fecBaja;
	@Setter
	@Getter
	@Nullable
	private Date fecActualizacion;

}
