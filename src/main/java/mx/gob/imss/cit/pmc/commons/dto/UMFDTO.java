package mx.gob.imss.cit.pmc.commons.dto;

import lombok.Getter;
import lombok.Setter;

public class UMFDTO {
	
	@Setter
	@Getter
	private int idUMF;
	
	@Setter
	@Getter
	private String descripcion;
	
	@Setter
	@Getter
	private String nombreCorto;
	
	@Setter
	@Getter
	private SubDelegacionDTO subdelegacion;

}
