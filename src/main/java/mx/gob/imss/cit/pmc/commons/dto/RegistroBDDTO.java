package mx.gob.imss.cit.pmc.commons.dto;

import lombok.Getter;
import lombok.Setter;

public class RegistroBDDTO extends PatronDTO {

	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	private String cveDelegacionPatron;

	@Setter
	@Getter
	private boolean nssExisteBDTU;

	@Setter
	@Getter
	private UMFDTO umfAdscripcionDTO;

	@Setter
	@Getter
	private DelegacionDTO delegacionAdscripcionDTO;

	@Setter
	@Getter
	private SubDelegacionDTO subDelegacionAdscripcionDTO;

	@Setter
	@Getter
	private DelegacionDTO delegacionAtencionDTO;

	@Setter
	@Getter
	private SubDelegacionDTO subDelegacionAtencionDTO;

	@Setter
	@Getter
	private UMFDTO umfExpedicionDTO;

	@Setter
	@Getter
	private UMFDTO umfPagadoraDTO;

	@Setter
	@Getter
	private TipoRiesgoDTO tipoRiesgoDTO;

	@Setter
	@Getter
	private ConsecuenciaDTO consecuenciaDTO;

	@Setter
	@Getter
	private LaudoDTO laudoDTO;
	
	@Setter
	@Getter
	private TipoIncapacidadDTO tipoIncapacidadDTO;

}
