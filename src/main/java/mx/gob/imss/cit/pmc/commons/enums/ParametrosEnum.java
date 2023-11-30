package mx.gob.imss.cit.pmc.commons.enums;

import lombok.Getter;
import lombok.Setter;

public enum ParametrosEnum {

	RUTA_ORIGEN("ruta.origen"), RUTA_DESTINO("ruta.destino"), RUTA_VALIDACION_NSSA("ruta.validacion.nssa"),
	RUTA_VALIDACION_SUI55("ruta.validacion.sui55"), RUTA_VALIDACION_SISAT("ruta.validacion.sisat"),
	RUTA_VALIDACION_NSSA_OTRAS("ruta.validacion.nssa"), RUTA_VALIDACION_SUI55_OTRAS("ruta.validacion.sui55"),
	RUTA_VALIDACION_SISAT_OTRAS("ruta.validacion.sisat"), URL_PATRON("url.patron"), URL_ASEGURADO("url.asegurado"),
	URL_DELEGACION("url.delegacion"), URL_SUBDELEGACION("url.subdelegacion"), URL_UMF("url.umf"),
	SFTP_HOST("sftp.host"), SFTP_USER("sftp.user"), SFTP_PASSWORD("sftp.password"), SFTP_PORT("sftp.port"),
	SFTP("sftp"), RUTA_ORIGEN_SFTP("ruta.origen.sftp");

	@Getter
	@Setter
	private String identificador;

	private ParametrosEnum(String identificador) {
		this.identificador = identificador;
	}

}
