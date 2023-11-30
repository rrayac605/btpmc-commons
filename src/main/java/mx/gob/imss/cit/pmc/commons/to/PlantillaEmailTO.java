package mx.gob.imss.cit.pmc.commons.to;

import lombok.Getter;
import lombok.Setter;

public class PlantillaEmailTO {

	@Getter
	@Setter
	private String mailFrom;
	@Getter
	@Setter
	private String[] mailTo;
	@Getter
	@Setter
	private String nombreCompleto;
	@Getter
	@Setter
	private String[] mailCc;
	@Getter
	@Setter
	private String fechaEnvio;
	@Getter
	@Setter
	private String horaEnvio;
	@Getter
	@Setter
	private String sistemaOrigen;
	@Getter
	@Setter
	private String diasEnvio;
	@Getter
	@Setter
	private String urlConsultar;
	@Getter
	@Setter
	private String subject;
	@Getter
	@Setter
	private String descError;

}